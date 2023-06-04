package com.shop.medicineshop.controller;

import com.shop.medicineshop.auth.AuthenticationRequest;
import com.shop.medicineshop.auth.AuthenticationResponse;
import com.shop.medicineshop.auth.AuthenticationService;
import com.shop.medicineshop.auth.RegisterCustomerRequest;
import com.shop.medicineshop.request.RegisterStoreRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.login(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, response.token())
                .body(response);
    }

    @PostMapping("/register-customer")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterCustomerRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }


    @PostMapping("/register-store")
    public ResponseEntity<AuthenticationResponse> registerStore(  @RequestBody RegisterStoreRequest request){
// To do
        return null;
    }

    @PostMapping("/register-admin")
    public ResponseEntity<AuthenticationResponse> registerStore(){
// To do
        return null;
    }
}
