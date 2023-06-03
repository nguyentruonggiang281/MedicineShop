package com.shop.medicineshop.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;
import com.shop.medicineshop.model.address.Address;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findById(Integer id);

}
