package com.hospitalbelen.procedimientosrp.apiProcedimiento.api;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.LoginRequest;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.ProgramacionRequest;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.ProgramacionResponse;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IProgramacionService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Programacion;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.handler.ResponseHandler;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IProgramacionRepository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.utils.constant.ProcedimientoConstant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(ProcedimientoConstant.API_BASE_PATH + "programacion")
@RequiredArgsConstructor
public class ProgramacionController {

    private final IProgramacionService iProgramacionService;

    @PostMapping()
    public ResponseEntity<Object> createProgramacion(@Valid @RequestBody ProgramacionRequest request)
    {
        return ResponseHandler.generateResponse(HttpStatus.OK, iProgramacionService.crear(request),true);

    }
    @GetMapping("/{fecha}/{idProcedimiento}")
    public ResponseEntity<Object> buscarPorFechaYProcedimiento(
            @PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @PathVariable("idProcedimiento") Long idProcedimiento) {
        Optional<Programacion> resultado = iProgramacionService.findByFechaAndIdProcedimiento(fecha, idProcedimiento);
        return ResponseHandler.generateResponse(HttpStatus.OK, resultado, resultado.isPresent());
    }

    @GetMapping("/procedimiento/{idProcedimiento}")
    public ResponseEntity<Object> buscarPorProcedimiento(
            @PathVariable("idProcedimiento") Long idProcedimiento) {
        return ResponseHandler.generateResponse(HttpStatus.OK, iProgramacionService.findByProcedimientoId( idProcedimiento),true);
    }
}
