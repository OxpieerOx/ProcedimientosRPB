package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.converter;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.CitaRequestDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.CitaResponseDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Cita;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.EstadoCita;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CitaConverter {

    public CitaConverter() {
    }

    public CitaResponseDTO toCitaResponseDTO(Cita entity) {
        return new CitaResponseDTO(
                entity.getIdPaciente(),
                entity.getNroCuenta(),
                entity.getFecha(),
                entity.getHoraInicio(),
                entity.getHoraFin(),
                entity.getProgramacion().getId(),
                entity.getMedico().getId(),
                entity.getUsuarioCreador(),
                entity.isEsAdicional(),
                entity.getEstado() // Devolver EstadoCita directamente
        );
    }

    public Cita toCitaEntity(CitaRequestDTO dto) {
        Cita entity = new Cita();
        entity.setIdPaciente(dto.getIdPaciente());
        entity.setNroCuenta(dto.getNroCuenta());
        entity.setFecha(dto.getFecha());
        entity.setHoraInicio(dto.getHoraInicio());
        entity.setHoraFin(dto.getHoraFin());
        entity.setEstado(dto.getEstado()); // Asignar EstadoCita directamente
        entity.setUsuarioCreador(dto.getUsuarioCreador());
        entity.setFechaRegistro(LocalDateTime.now()); // Establecer la marca de tiempo actual o manejarla según los requisitos
        entity.setEsAdicional(dto.isEsAdicional());
        return entity;
    }
}
