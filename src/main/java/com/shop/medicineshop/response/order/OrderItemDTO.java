package com.shop.medicineshop.response.order;


public record OrderItemDTO (
    Integer itemId,
    String productName,
    Integer quantity,
    Float price,
    String unit
) {

}
