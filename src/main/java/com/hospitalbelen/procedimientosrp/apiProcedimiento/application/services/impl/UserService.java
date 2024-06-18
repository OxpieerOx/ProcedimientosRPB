package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.LoginRequest;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.AuthResponse;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IUserService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.User;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Servicio;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IUserRepository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.security.Jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


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

    public Set<Servicio> getServicesForUser(String username) {
        User user = iUserRepository.findByUsername(username).orElseThrow();
        return user.getRoles().stream()
                .flatMap(role -> role.getServices().stream())
                .collect(Collectors.toSet());
    }
    public List<User> getUsersByRoleId(Integer roleId) {
        return iUserRepository.findByRoleId(roleId);
    }
}
