package com.shop.medicineshop.response.customer;

import com.shop.medicineshop.model.customer.Customer;
import com.shop.medicineshop.model.product.Product;
import com.shop.medicineshop.response.product.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerMapper {

    public CustomerDTO toDTO(Customer customer) {
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

    public Customer toEntity(CustomerDTO customerDTO) {
        return Customer.builder()
                .name(customerDTO.name())
                .email(customerDTO.email())
                .phoneNumber(customerDTO.phoneNumber())
                .build();
    }

    public List<CustomerDTO> mapToList(List<Customer> customers) {
        return customers.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
