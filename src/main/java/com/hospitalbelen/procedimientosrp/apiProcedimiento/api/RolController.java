package com.hospitalbelen.procedimientosrp.apiProcedimiento.api;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IRolService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.handler.ResponseHandler;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.utils.constant.ProcedimientoConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProcedimientoConstant.API_BASE_PATH + "role")
@RequiredArgsConstructor
public class RolController {

    private final IRolService rolService;

    @GetMapping("/{username}")
    public ResponseEntity<Object> buscarSericiosporUsuario(@PathVariable String username) {
        return ResponseHandler.generateResponse(HttpStatus.OK, rolService.findRolesByUsername(username), true);
    }
}
