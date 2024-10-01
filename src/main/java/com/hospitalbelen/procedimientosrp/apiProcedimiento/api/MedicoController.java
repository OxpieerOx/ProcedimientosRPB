package com.hospitalbelen.procedimientosrp.apiProcedimiento.api;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.MedicoRequestDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IMedicoService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.handler.ResponseHandler;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.utils.constant.ProcedimientoConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ProcedimientoConstant.API_BASE_PATH + "medico")
@RequiredArgsConstructor
public class MedicoController {

    private final IMedicoService medicoService;

    @GetMapping("/findByUserId/{id}")
    public ResponseEntity<Object> buscarSericiosporUsuario(@PathVariable Integer id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, medicoService.findByUserId(id), true);
    }

    @GetMapping("/servicio/{id}")
    public ResponseEntity<Object> bucsarMedicoPorServicio(@PathVariable Long id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, medicoService.findByServicioId(id), true);
    }

    @GetMapping()
    public ResponseEntity<Object> listAllMedicos() {
        return ResponseHandler.generateResponse(HttpStatus.OK, medicoService.findAll(), true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> listByIdMedicos(@PathVariable Integer id) {
        return ResponseHandler.generateResponse(HttpStatus.OK, medicoService.findById(id), true);
    }

    @PostMapping()
    public ResponseEntity<Object> createMedico(@RequestBody MedicoRequestDTO medicoRequestDTO, @RequestParam List<Long> roleIds) {
        return ResponseHandler.generateResponse(HttpStatus.CREATED, medicoService.saveMedico(medicoRequestDTO, roleIds), true);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateMedico(@PathVariable Integer id, @RequestBody MedicoRequestDTO medicoRequestDTO, @RequestParam List<Long> roleIds) {
        return ResponseHandler.generateResponse(HttpStatus.OK, medicoService.updateMedico(medicoRequestDTO, id, roleIds), true);
    }
}
