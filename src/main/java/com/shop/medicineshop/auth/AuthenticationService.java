package com.shop.medicineshop.auth;


import com.shop.medicineshop.jwt.JWTUtil;
import com.shop.medicineshop.model.account.Account;
import com.shop.medicineshop.model.account.Role;
import com.shop.medicineshop.model.account.Status;
import com.shop.medicineshop.model.cart.Cart;
import com.shop.medicineshop.model.customer.Customer;
import com.shop.medicineshop.model.store.Store;
import com.shop.medicineshop.repository.AccountRepository;
import com.shop.medicineshop.repository.CustomerRepository;
import com.shop.medicineshop.repository.address.AddressRepository;
import com.shop.medicineshop.repository.cart.CartRepository;
import com.shop.medicineshop.repository.store.StoreRepository;
import com.shop.medicineshop.request.RegisterStoreRequest;
import com.shop.medicineshop.response.account.AccountDTO;
import com.shop.medicineshop.response.account.AccountDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final AccountDTOMapper accountDTOMapper;
    private final JWTUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository repository;
    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public AuthenticationResponse register(RegisterCustomerRequest request) {
        var user = Account.builder()
                .userLogin(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .status(Status.ACTIVE)
                .build();
        repository.save(user);

        Customer customer = new Customer();
        customer.setAccount(user);
        customer.setPhoneNumber(request.getUsername());
        customer.setName(request.getName());
        customer.setEmail(null);

        Cart cart = new Cart();
        cart.setCustomer(customer);
        customer.setCart(cart);

        customerRepository.save(customer);
        cartRepository.save(cart);

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

    @Transactional
    public AuthenticationResponse register(RegisterStoreRequest request) {
        var storeAccount = repository.save(Account.builder()
                                                .userLogin(request.getUserLogin())
                                                .password(passwordEncoder.encode(request.getPassword()))
                                                .role(Role.STORE)
                                                .status(Status.ACTIVE)
                                                .build());

        Store store = new Store();
        store.setName(request.getName());
        store.setEmail(request.getEmail());
        store.setAddress(addressRepository.save(request.getAddress()));
        store.setAccount(storeAccount);

        storeRepository.save(store);

        String token = jwtUtil.issueToken(storeAccount.getUsername(), storeAccount.getRole().toString());

        return AuthenticationResponse.builder()
                .token(token)
                .accountDTO(accountDTOMapper.apply(storeAccount))
                .build();
    }

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
