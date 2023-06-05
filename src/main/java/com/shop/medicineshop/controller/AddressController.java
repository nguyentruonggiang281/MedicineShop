package com.shop.medicineshop.controller;

import com.shop.medicineshop.model.address.Address;
import com.shop.medicineshop.request.CustomerAddressRequest;
import com.shop.medicineshop.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @PostMapping()
    public ResponseEntity<?> createAddressCus(@RequestBody CustomerAddressRequest address) {
        if (addressService.addAddressCustomer(address)!=null){
            return ResponseEntity.ok().body(addressService.addAddressCustomer(address));}
        return ResponseEntity.badRequest().body("failed to create address");
    }

    @PostMapping("/{idStore}")//test add address thôi
    public ResponseEntity<?> createAddressStore(@RequestParam Integer idStore,
                                                @RequestBody Address address) {
        if (addressService.createAddress(address))
            return ResponseEntity.ok().body("success");
        return ResponseEntity.badRequest().body("failed to create address");
    }
}
