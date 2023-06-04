package com.shop.medicineshop.response.customer;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
public record CustomerDTO(
        int id,
        String name,
        String email,
        String phoneNumber,
        List<Integer> addressId,
        int idAcc
) {
    @Override
    public int id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String email() {
        return email;
    }

    @Override
    public String phoneNumber() {
        return phoneNumber;
    }

    @Override
    public List<Integer> addressId() {
        return addressId;
    }

    @Override
    public int idAcc() {
        return idAcc;
    }
}
