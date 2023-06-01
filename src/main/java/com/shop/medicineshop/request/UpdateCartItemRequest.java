package com.shop.medicineshop.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCartItemRequest {

    private Integer idItem;
    private Integer idUnit;
    private Float price;
    private Integer quantity;
}
