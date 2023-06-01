package com.shop.medicineshop.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartItemRequest {
    private Integer idUnit;
    private Float price;
    private Integer quantity;
}
