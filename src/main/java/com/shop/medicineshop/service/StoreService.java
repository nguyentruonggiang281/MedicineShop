package com.shop.medicineshop.service;

import com.shop.medicineshop.model.address.Address;
import com.shop.medicineshop.model.store.Store;
import com.shop.medicineshop.repository.address.AddressRepository;
import com.shop.medicineshop.request.StoreRequest;
import com.shop.medicineshop.response.store.StoreDTO;
import com.shop.medicineshop.response.store.StoreMapper;
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
    AddressRepository addressRepository;
    @Autowired
    DistanceCalculatorService  distanceCalculatorService;
    @Autowired
    StoreMapper storeMapper;

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
}