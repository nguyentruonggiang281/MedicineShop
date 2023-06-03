package com.shop.medicineshop.service;

import com.shop.medicineshop.model.address.Address;
import com.shop.medicineshop.model.store.Store;
import com.shop.medicineshop.repository.address.AddressRepository;
import com.shop.medicineshop.request.StoreRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shop.medicineshop.repository.store.StoreRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    AddressRepository addressRepository;

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
}
