package com.shop.medicineshop.request;

import com.shop.medicineshop.model.address.Address;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StoreRequest {
    private Integer idAccount;
    private String name;
    private String email;
    private Address address;

}
