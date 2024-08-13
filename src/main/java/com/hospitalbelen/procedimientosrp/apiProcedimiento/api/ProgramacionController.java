package com.hospitalbelen.procedimientosrp.apiProcedimiento.api;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.LoginRequest;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.ProgramacionRangoRequest;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.ProgramacionRequest;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.ProgramacionResponse;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IProgramacionService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl.ProgramacionService;
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

    private final ProgramacionService programacionService;

        @PostMapping()
        public ResponseEntity<Object> createProgramacion(@Valid @RequestBody ProgramacionRequest request)
        {
            return ResponseHandler.generateResponse(HttpStatus.OK, iProgramacionService.crear(request),true);

        }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarporId(
            @PathVariable("id") Long id) {
        Optional<Programacion> resultado = iProgramacionService.getbyId(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, resultado,true);
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

    @PutMapping("/{id}")
    public ResponseEntity<Object> actualizarProgramacion(
            @PathVariable Long id,
            @Valid @RequestBody ProgramacionRequest request) {
        ProgramacionResponse response = iProgramacionService.actualizar(id, request);
        return ResponseHandler.generateResponse(HttpStatus.OK, response, true);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProgramacionById(@PathVariable Long id) {
        iProgramacionService.deleteById(id);
        return ResponseHandler.generateResponse(HttpStatus.OK, "Programacion eliminada correctamente", true);
    }


    @PostMapping("/fechas")
    public ResponseEntity<Object> buscarPorProcedimiento(@RequestBody ProgramacionRangoRequest request)  {
        return ResponseHandler.generateResponse(HttpStatus.OK, programacionService.crearProgramacionesEnRango(request),true);
    }
}
