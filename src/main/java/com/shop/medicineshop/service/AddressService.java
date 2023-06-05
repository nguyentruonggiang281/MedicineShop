package com.shop.medicineshop.service;

import com.shop.medicineshop.model.address.Address;
import com.shop.medicineshop.model.customer.Customer;
import com.shop.medicineshop.model.customer.CustomerAddress;
import com.shop.medicineshop.model.customer.CustomerAddressId;
import com.shop.medicineshop.repository.CustomerRepository;
import com.shop.medicineshop.repository.address.AddressRepository;
import com.shop.medicineshop.repository.address.CustomerAddressRepository;
import com.shop.medicineshop.request.CustomerAddressRequest;
import com.shop.medicineshop.response.address.AddressDTO;
import com.shop.medicineshop.response.address.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerAddressRepository customerAddressRepository;
@Autowired
private AddressMapper addressMapper;
    public boolean createAddress(Address address) {
        try {
            addressRepository.save(address);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Transactional
    public AddressDTO addAddressCustomer(CustomerAddressRequest cusAddress) {
        CustomerAddress customerAddress = new CustomerAddress();
        AddressDTO addressDTO = new AddressDTO();
        Optional<Customer> customer = customerRepository.findByAccount_Id(cusAddress.getIdAccount());
        if (customer.isPresent())
            customerAddress.setCustomer(customer.get());
        else
            return null;

        try {
            Address address = addressRepository.save(cusAddress.getAddress());
            CustomerAddressId id = new CustomerAddressId(customer.get().getId(), address.getId());
            customerAddress.setAddress(address);
            customerAddress.setDefault(cusAddress.getIsDefault());
            customerAddress.setId(id);
            customerAddressRepository.save(customerAddress);

            addressDTO = addressMapper.apply(address);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return addressDTO;
    }


}
