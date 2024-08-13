package com.hospitalbelen.procedimientosrp.apiProcedimiento.api;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.CitaFinanciamientoDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.CitaMesDTO;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ProcedimientoConstant.API_BASE_PATH + "cita")
@RequiredArgsConstructor
public class CitaController {

    private final ICitaService citaService;

    private final CitaService citaServiceimp;

    @PostMapping()
    public ResponseEntity<Object> insertarCita(@Valid @RequestBody CitaRequestDTO request)
    {
        return ResponseHandler.generateResponse(HttpStatus.OK, citaService.createCita(request),true);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCita(@Valid @RequestBody CitaRequestDTO request, @PathVariable Integer id)
    {
        return ResponseHandler.generateResponse(HttpStatus.OK, citaService.updateCita(id,request),true);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarporidProgramacion(
            @PathVariable("id") Integer id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, citaService.getCitasByProgramacion(id),true);
    }

    @GetMapping("/citasPorMes")
        public ResponseEntity<Object> getCitasPorMes() {
        List<CitaMesDTO> citasPorMes = citaServiceimp.getCitasPorMes();
        return ResponseHandler.generateResponse(HttpStatus.OK, citasPorMes, true);
    }

    @GetMapping("/citasPorFinanciamiento")
    public ResponseEntity<Object> getCitasPorFinanciamiento() {
        List<CitaFinanciamientoDTO> citasPorFinanciamiento = citaServiceimp.getCitasPorFinanciamiento();
        return ResponseHandler.generateResponse(HttpStatus.OK, citasPorFinanciamiento, true);
    }
}
