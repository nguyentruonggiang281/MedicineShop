package com.shop.medicineshop.response.customer;

import com.shop.medicineshop.model.customer.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        List<Integer> addressId = new ArrayList<>();
        customer.getCustomerAddresses().forEach(address -> addressId.add(address.getAddress().getId()));
        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .addressId(addressId)
                .idAcc(customer.getAccount().getId())
                .build();
    }

    public static Customer toEntity(CustomerDTO customerDTO) {
        return Customer.builder()
                .name(customerDTO.name())
                .email(customerDTO.email())
                .phoneNumber(customerDTO.phoneNumber())

                .build();
    }
}
