package com.shop.medicineshop.response.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitDTO {
    private Integer unitId;
    private String name;
    private Integer rank;
    private float priceUnit;
    private int quantity;
    private Integer specifications;
}
