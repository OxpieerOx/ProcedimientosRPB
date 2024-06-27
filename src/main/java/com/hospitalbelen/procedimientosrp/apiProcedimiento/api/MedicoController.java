package com.hospitalbelen.procedimientosrp.apiProcedimiento.api;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IMedicoService;
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
@RequestMapping(ProcedimientoConstant.API_BASE_PATH + "medico")
@RequiredArgsConstructor
public class MedicoController {

    private final IMedicoService medicoService;

    @GetMapping("/findByUserId/{id}")
    public ResponseEntity<Object> buscarSericiosporUsuario(@PathVariable Integer id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, medicoService.findByUserId(id), true);
    }

    @GetMapping()
    public ResponseEntity<Object> listAllMedicos() {
        return ResponseHandler.generateResponse(HttpStatus.OK, medicoService.findAll(), true);
    }
}
