package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.LoginRequest;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.AuthResponse;


public interface IUserService {

    public AuthResponse login(LoginRequest request);


}
