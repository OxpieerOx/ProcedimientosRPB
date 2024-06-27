package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.converter;


import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.ProgramacionRequest;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.ProgramacionResponse;
import org.springframework.stereotype.Component;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Programacion;

@Component
public class ProgramacionConverter {

    public ProgramacionConverter() {
    }

    public ProgramacionResponse toProgramacionResponseDTO(Programacion entity) {
        return new ProgramacionResponse(
                entity.getId(),
                entity.getFecha(),
                entity.getHoraInicio(),
                entity.getHoraFin(),
                entity.getTiempoPromedio(),
                entity.getFechaRegistro(),
                entity.getUsuarioCreador(),
                entity.getMedico().getId(),
                entity.getProcedimiento().getId()
        );
    }

    // Método opcional si necesitas convertir de DTO a entidad
    public Programacion toProgramacionEntity(ProgramacionResponse dto) {
        Programacion entity = new Programacion();
        entity.setId(dto.getId());
        entity.setFecha(dto.getFecha());
        entity.setHoraInicio(dto.getHoraInicio());
        entity.setHoraFin(dto.getHoraFin());
        entity.setTiempoPromedio(dto.getTiempoPromedio());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setUsuarioCreador(dto.getUsuarioCreador());
        return entity;
    }

    public Programacion toProgramacionEntity(ProgramacionRequest dto) {
        Programacion entity = new Programacion();
        entity.setFecha(dto.getFecha());
        entity.setHoraInicio(dto.getHoraInicio());
        entity.setHoraFin(dto.getHoraFin());
        entity.setTiempoPromedio(dto.getTiempoPromedio());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setUsuarioCreador(dto.getUsuarioCreador());
        return entity;
    }
}