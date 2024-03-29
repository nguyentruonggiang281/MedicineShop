package com.shop.medicineshop.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddToCartRequest {
    private Integer idAccount;
    private Integer idProduct;
    private Integer idUnit;
    private Float price;
    private Integer quantity;
}
