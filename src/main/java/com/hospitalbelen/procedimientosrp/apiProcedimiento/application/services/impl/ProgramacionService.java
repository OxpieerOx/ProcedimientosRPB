package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;


import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.ProgramacionRequest;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.ProgramacionResponse;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IProgramacionService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Programacion;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.converter.ProgramacionConverter;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IProgramacionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ProgramacionService implements IProgramacionService {

    private final IProgramacionRepository iProgramacionRepository;

    private final ProgramacionConverter programacionConverter;

    public ProgramacionService(IProgramacionRepository iProgramacionRepository, ProgramacionConverter programacionConverter) {
        this.iProgramacionRepository = iProgramacionRepository;
        this.programacionConverter = programacionConverter;
    }

    @Override
    public Optional<Programacion> buscarPorFecha(LocalDate fecha) {
        return iProgramacionRepository.findByfecha(fecha);
    }

    @Override
    public ProgramacionResponse crear(ProgramacionRequest entidad) {
        Programacion programacion = programacionConverter.toProgramacionEntity(entidad);
        Programacion programacionentity = iProgramacionRepository.save(programacion);
        return programacionConverter.toProgramacionResponseDTO(programacionentity);

    }
}
