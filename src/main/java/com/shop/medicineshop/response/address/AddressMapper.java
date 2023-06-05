package com.shop.medicineshop.response.address;

import com.shop.medicineshop.model.address.Address;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Builder
public class AddressMapper implements Function<Address,AddressDTO > {
    @Override
    public AddressDTO apply(Address address) {
        String addr = address.getStreet() + ", " + address.getWard() + ", " + address.getDistrict() + ", " + address.getProvince();
        return new AddressDTO(address.getId(),addr);
    }
}
