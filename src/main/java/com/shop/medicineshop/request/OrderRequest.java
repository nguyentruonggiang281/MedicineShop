package com.shop.medicineshop.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Integer idAccount;
    private Integer idAddress;
    private Integer idStore;
    private Float shippingFee;
    private List<Integer> idCartItems;

}
