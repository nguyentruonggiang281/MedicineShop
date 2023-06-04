package com.shop.medicineshop.request;

import com.shop.medicineshop.model.address.Address;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomerAddressRequest {
    private Integer idAccount;
    private Address address;
    private Boolean isDefault;
}
