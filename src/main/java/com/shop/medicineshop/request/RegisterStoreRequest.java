package com.shop.medicineshop.request;

import com.shop.medicineshop.model.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterStoreRequest {
    private String password;
    private String name;
    private String email;
    private Address address;
}
