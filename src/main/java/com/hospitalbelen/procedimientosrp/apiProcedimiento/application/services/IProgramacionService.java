package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.ProgramacionRequest;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.ProgramacionResponse;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Programacion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IProgramacionService {
    Optional<Programacion>  findByFechaAndIdProcedimiento(LocalDate fecha, Long idProcedimiento);

    List<Programacion> findByProcedimientoId(Long idProcedimiento);

    ProgramacionResponse crear(ProgramacionRequest entidad);

    ProgramacionResponse updateProgramacion(Programacion programacion);
}
