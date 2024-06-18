package com.hospitalbelen.procedimientosrp.application.services;

import com.hospitalbelen.procedimientosrp.application.DTO.request.LoginRequest;
import com.hospitalbelen.procedimientosrp.application.DTO.response.AuthResponse;
import org.springframework.stereotype.Repository;


public interface IUserService {

    public AuthResponse login(LoginRequest request);


}
