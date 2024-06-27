package com.hospitalbelen.procedimientosrp.apiProcedimiento.api;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IUserService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl.UserService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.handler.ResponseHandler;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.utils.constant.ProcedimientoConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(ProcedimientoConstant.API_BASE_PATH + "usuario")
@RequiredArgsConstructor
public class UserController {

    private final IUserService iUserService;

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarSericiosporUsuario(@PathVariable String id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, userService.getServicesForUser(id), true);
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<Object>  getUsersByRoleId(@PathVariable Integer roleId) {
        return ResponseHandler.generateResponse(HttpStatus.OK, userService.getUsersByRoleId(roleId), true);
    }
}
