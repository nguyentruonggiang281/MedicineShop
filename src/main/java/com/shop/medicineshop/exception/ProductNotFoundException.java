package com.shop.medicineshop.exception;
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Integer id){
        super("Could not found the user with id "+ id);
    }
}
