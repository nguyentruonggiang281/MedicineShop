package com.shop.medicineshop.repository.address;

import com.shop.medicineshop.model.customer.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer> {
}
