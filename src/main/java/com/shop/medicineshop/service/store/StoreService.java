package com.shop.medicineshop.service.store;

import com.shop.medicineshop.model.address.Address;
import com.shop.medicineshop.model.order.Order;
import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.model.store.Store;
import com.shop.medicineshop.model.store.StoreProduct;
import com.shop.medicineshop.model.store.StoreProductId;
import com.shop.medicineshop.repository.address.AddressRepository;
import com.shop.medicineshop.repository.order.OrderRepository;
import com.shop.medicineshop.repository.product.ProductRepository;
import com.shop.medicineshop.repository.store.StoreProductRepository;
import com.shop.medicineshop.request.StoreRequest;
import com.shop.medicineshop.response.order.OrderDTO;
import com.shop.medicineshop.response.order.OrderMapper;
import com.shop.medicineshop.response.product.ProductDTO;
import com.shop.medicineshop.response.product.ProductDTOMapper;
import com.shop.medicineshop.response.store.StoreDTO;
import com.shop.medicineshop.response.store.StoreMapper;
import com.shop.medicineshop.response.store.StoreProductDTO;
import com.shop.medicineshop.service.DistanceCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shop.medicineshop.repository.store.StoreRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DistanceCalculatorService distanceCalculatorService;
    @Autowired
    StoreMapper storeMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    StoreProductRepository storeProductRepository;
    @Autowired
    ProductDTOMapper productDTOMapper;

    @Transactional
    public boolean createStore(StoreRequest storeRQ) {
        Store store = new Store();
        try {
            store.setName(storeRQ.getName());
            store.setEmail(storeRQ.getEmail());
            store.setAddress(addressRepository.save(storeRQ.getAddress()));
            storeRepository.save(store);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<StoreDTO> getStoresNearest(Integer idAddress, int maxResults) {
        Optional<Address> address = addressRepository.findById(idAddress);
        List<StoreDTO> storeDTOs = new ArrayList<>();
        if(address.isPresent()){
            List<Store> stores = storeRepository.findByAddress_Province(address.get().getProvince());
            if(stores.size() > 0){
                for(Store st : stores){
                    double distance = distanceCalculatorService.calculateDistance(address.get().toString(), st.getAddress().toString());
                    if(distance >= 0){//address valid
                        storeDTOs.add(storeMapper.map(st, distance));
                    }
                    System.out.println(st.toString() + ", dist: " + distance);
                }
                if(!storeDTOs.isEmpty()){
                    storeDTOs.sort(Comparator.comparingDouble(StoreDTO::distance));
                    if(storeDTOs.size() > maxResults)
                        return storeDTOs.subList(0, maxResults);
                    else return storeDTOs;
                }
            }
        }
        return null;
    }

    public List<OrderDTO> getAllOrderByStore(Integer idStore) {
        List<OrderDTO> orderDTOs = new ArrayList<>();
        List<Order> orders = orderRepository.findAllByStoreId(idStore);
        if(orders.size() > 0){
            for(Order order : orders)
                orderDTOs.add(orderMapper.apply(order));
            return orderDTOs;
        }
        return null;
    }

    @Transactional
    public StoreProductDTO addOrUpdateProduct(int idStore, int idProduct, int quantity) {
        Optional<StoreProduct> option = storeProductRepository.findByProductIdAndStoreId(idProduct, idStore);
        if(option.isPresent()){
            StoreProduct storeProduct = option.get();
            storeProduct.setQuantity(option.get().getQuantity() + quantity);
            storeProductRepository.save(storeProduct);
            return StoreProductDTO.builder().storeId(idStore).productId(idProduct).quantity(storeProduct.getQuantity()).build();
        }else {
            StoreProduct storeProduct = new StoreProduct();
            StoreProductId id = new StoreProductId(idStore, idProduct);
            storeProduct.setId(id);
            storeProduct.setStore(storeRepository.findById(idStore).get());
            storeProduct.setProduct(productRepository.findById(idProduct).get());
            storeProduct.setQuantity(quantity);
            storeProductRepository.save(storeProduct);
            return StoreProductDTO.builder().storeId(idStore).productId(idProduct).quantity(storeProduct.getQuantity()).build();
        }
    }

    public List<ProductDTO> getAllStoreProduct(int idStore) {
        List<ProductDTO> result = new ArrayList<>();
        List<StoreProduct> storeProducts = storeProductRepository.findAllByStore_Id(idStore);
        for(StoreProduct sp : storeProducts){
            result.add(productDTOMapper.map(sp.getProduct(), sp.getQuantity()));
        }
        return result;
    }

    public ProductDTO getStoreProductById(int idStore, int idProduct) {
        Optional<StoreProduct> option = storeProductRepository.findByProductIdAndStoreId(idProduct, idStore);
        return option.map(storeProduct -> productDTOMapper.map(storeProduct.getProduct(), storeProduct.getQuantity())).orElse(null);
    }
}
