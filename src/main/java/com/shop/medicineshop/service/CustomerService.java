package com.shop.medicineshop.service;

import com.shop.medicineshop.model.customer.Customer;
import com.shop.medicineshop.model.order.Order;
import com.shop.medicineshop.repository.CustomerRepository;
import com.shop.medicineshop.repository.order.OrderRepository;
import com.shop.medicineshop.response.customer.CustomerDTO;
import com.shop.medicineshop.response.customer.CustomerMapper;
import com.shop.medicineshop.response.order.OrderDTO;
import com.shop.medicineshop.response.order.OrderMapper;
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
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;

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

    public List<OrderDTO> getAllOrderByCustomer(Integer idAccount) {
        Optional<Customer> customer = customerRepository.findByAccount_Id(idAccount);
        return orderMapper.mapToDTO(orderRepository.findByCustomer_Account_Id(idAccount));
    }
}
