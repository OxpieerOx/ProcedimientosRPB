package com.hospitalbelen.procedimientosrp.apiProcedimiento.api;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IServicioService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl.ServicioService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.handler.ResponseHandler;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.utils.constant.ProcedimientoConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ProcedimientoConstant.API_BASE_PATH + "servicio")
@RequiredArgsConstructor
public class ServicioController {

    @Autowired
    private IServicioService servicioService;
    @GetMapping()
    public ResponseEntity<Object> buscarAllServicios() {
        return ResponseHandler.generateResponse(HttpStatus.OK, servicioService.getAllServices(), true);
    }
    @GetMapping("/byUser/{id}")
    public ResponseEntity<Object> buscarSericiosporUsuario(@PathVariable String id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, servicioService.getServicesForUser(id), true);
    }

}
