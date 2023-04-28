package com.shop.medicineshop.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

//  @JsonProperty("access_token")
//  private String accessToken;
//  @JsonProperty("refresh_token")
//  private String refreshToken;

    //  private String firstname;
//  private String lastname;
//  private String email;
//  private String password;
    private String token;
}
