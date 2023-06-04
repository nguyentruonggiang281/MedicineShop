package com.shop.medicineshop.service;

import com.shop.medicineshop.model.customer.Customer;
import com.shop.medicineshop.repository.CustomerRepository;
import com.shop.medicineshop.response.customer.CustomerDTO;
import com.shop.medicineshop.response.customer.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

@Autowired
private CustomerMapper customerMapper;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public CustomerDTO getInfoCustomer(Integer idAccount) {
        Optional<Customer> customer = customerRepository.findByAccount_Id(idAccount);
        return customer.map(value -> customerMapper.toDTO(value)).orElse(null);
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerMapper.mapToList(customerRepository.findAll());
    }
}
