package com.hospitalbelen.procedimientosrp.apiProcedimiento.api;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IProcedimientoService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Procedimiento;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Programacion;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.handler.ResponseHandler;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.utils.constant.ProcedimientoConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(ProcedimientoConstant.API_BASE_PATH + "procedimiento")
@RequiredArgsConstructor
public class ProcedimientoController {

    private final IProcedimientoService procedimientoService;

    @GetMapping("/servicio/{id}")
    public ResponseEntity<Object> buscarSericiosporUsuario(@PathVariable Long id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, procedimientoService.obtenerProcedimientosPorServicio(id), true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, procedimientoService.obtenerProcedimientoPorId(id), true);
    }
}
