package com.shop.medicineshop.repository;

import com.shop.medicineshop.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUserLogin(String userName);

    Account findOneById(long id);

//    Account findOneByUserName(String userName);
}
