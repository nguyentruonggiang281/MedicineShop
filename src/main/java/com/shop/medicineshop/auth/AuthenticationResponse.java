package com.shop.medicineshop.auth;

import com.shop.medicineshop.response.account.AccountDTO;
import lombok.Builder;


@Builder
public record AuthenticationResponse (
        String token,
        AccountDTO accountDTO){
}
