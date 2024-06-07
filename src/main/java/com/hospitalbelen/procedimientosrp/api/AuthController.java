package com.hospitalbelen.procedimientosrp.api;

import com.hospitalbelen.procedimientosrp.application.DTO.request.LoginRequest;
import com.hospitalbelen.procedimientosrp.application.DTO.response.AuthResponse;
import com.hospitalbelen.procedimientosrp.application.services.IUserService;
import com.hospitalbelen.procedimientosrp.infraestructura.handler.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService iUserService;

    @PostMapping(value = "login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest request)
    {
        return ResponseHandler.generateResponse(HttpStatus.OK, iUserService.login(request),true);

    }

}
