package com.shop.medicineshop.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
