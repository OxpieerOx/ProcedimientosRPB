package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.converter;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.MedicoRequestDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.MedicoResponseDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Medico;
import org.springframework.stereotype.Component;

@Component
public class MedicoConverter {

    public MedicoConverter() {
    }

    public MedicoResponseDTO toMedicoResponseDTO(Medico entity) {
        return new MedicoResponseDTO(
                entity.getId(),
                entity.getUserId(),
                entity.getNombre(),
                entity.getApellido(),
                entity.getTelefono(),
                entity.getEmail()
        );
    }

    public Medico toMedicoEntity(MedicoRequestDTO dto) {
        Medico entity = new Medico();
        entity.setUserId(dto.getUserId());
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setTelefono(dto.getTelefono());
        entity.setEmail(dto.getEmail());
        return entity;
    }
}
