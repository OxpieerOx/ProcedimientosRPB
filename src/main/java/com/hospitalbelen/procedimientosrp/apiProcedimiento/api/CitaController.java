package com.hospitalbelen.procedimientosrp.apiProcedimiento.api;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.CitaRequestDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.ProgramacionRequest;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.ICitaService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IMedicoService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl.CitaService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Programacion;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.handler.ResponseHandler;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.utils.constant.ProcedimientoConstant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(ProcedimientoConstant.API_BASE_PATH + "cita")
@RequiredArgsConstructor
public class CitaController {

    private final ICitaService citaService;

    @PostMapping()
    public ResponseEntity<Object> insertarCita(@Valid @RequestBody CitaRequestDTO request)
    {
        return ResponseHandler.generateResponse(HttpStatus.OK, citaService.createCita(request),true);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarporidProgramacion(
            @PathVariable("id") Integer id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, citaService.getCitasByProgramacion(id),true);
    }

}
