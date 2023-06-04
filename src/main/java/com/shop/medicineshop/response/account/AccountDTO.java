package com.shop.medicineshop.response.account;


import com.shop.medicineshop.model.account.Status;

import java.util.List;

public record AccountDTO(
        Integer id,
        String userName,
        Status status,
        List<String> role

){

}
