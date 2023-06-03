package com.shop.medicineshop.response.product;

public record UnitDTO(
//        Integer unitId,
        String name,
        Integer rank,
        Integer specifications,
        Double price,
        Integer quantity
) {
    public UnitDTO(String name, Integer rank, Integer specifications) {
        this(name, rank, specifications, null, null);
    }

    public UnitDTO(String name, Integer rank, Integer specifications, Double price, Integer quantity) {
        this.name = name;
        this.rank = rank;
        this.specifications = specifications;
        this.price = price;
        this.quantity = quantity;
    }
}
