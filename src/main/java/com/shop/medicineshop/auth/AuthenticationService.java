package com.shop.medicineshop.auth;


import com.shop.medicineshop.jwt.JWTUtil;
import com.shop.medicineshop.model.account.Account;
import com.shop.medicineshop.model.account.Role;
import com.shop.medicineshop.model.account.Status;
import com.shop.medicineshop.repository.AccountRepository;
import com.shop.medicineshop.response.account.AccountDTO;
import com.shop.medicineshop.response.account.AccountDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final AccountDTOMapper accountDTOMapper;
    private final JWTUtil jwtUtil;
private final PasswordEncoder passwordEncoder;
private final AccountRepository repository;
//private  final J
    public AuthenticationResponse register(RegisterCustomerRequest request) {
        var user = Account.builder()
//                .firstname(request.getFirstname())
//                .lastname(request.getLastname())
//                .email(request.getEmail())
//                .name(request.getName())
                .userLogin(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .status(Status.ACTIVE)
                .build();
        repository.save(user);
//    var savedUser = repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//    var refreshToken = jwtService.generateRefreshToken(user);
        String token = jwtUtil.issueToken(user.getUsername(), user.getRole().toString());

        return AuthenticationResponse.builder()
                .token(token)
                .accountDTO(accountDTOMapper.apply(user))
                .build();
    }

//    public AuthenticationService(AuthenticationManager authenticationManager,
//                                 UserDTOMapper userDTOMapper,
//                                 JWTUtil jwtUtil) {
//        this.authenticationManager = authenticationManager;
//        this.userDTOMapper = userDTOMapper;
//        this.jwtUtil = jwtUtil;
//    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        Account principal = (Account) authentication.getPrincipal();
        AccountDTO accountDTO = accountDTOMapper.apply(principal);
        String token = jwtUtil.issueToken(accountDTO.userName(), accountDTO.role());
        return new AuthenticationResponse(token, accountDTO);
    }

}
