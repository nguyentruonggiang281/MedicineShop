package com.shop.medicineshop.repository;

import com.shop.medicineshop.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select c from Customer c where c.account.id = ?1")
    Optional<Customer> findByAccount_Id(Integer id);

}

