package com.hospitalbelen.procedimientosrp.application.services.impl;

import com.hospitalbelen.procedimientosrp.application.DTO.request.LoginRequest;
import com.hospitalbelen.procedimientosrp.application.DTO.response.AuthResponse;
import com.hospitalbelen.procedimientosrp.application.services.IUserService;
import com.hospitalbelen.procedimientosrp.infraestructura.repository.IUserRepository;
import com.hospitalbelen.procedimientosrp.infraestructura.security.Jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService {

    private final IUserRepository iUserRepository;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserService(IUserRepository iUserRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.iUserRepository = iUserRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=iUserRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }
}
