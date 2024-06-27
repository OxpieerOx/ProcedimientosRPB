package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;


import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.ProgramacionRequest;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.ProgramacionResponse;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IProgramacionService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Medico;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Procedimiento;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Programacion;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.converter.ProgramacionConverter;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IMedicoRepository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IProcedimientoRepository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IProgramacionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramacionService implements IProgramacionService {

    private final IProgramacionRepository iProgramacionRepository;

    private final IMedicoRepository medicoRepository;
    private final IProcedimientoRepository procedimientoRepository;

    private final ProgramacionConverter programacionConverter;

    public ProgramacionService(IProgramacionRepository iProgramacionRepository, IMedicoRepository medicoRepository, IProcedimientoRepository procedimientoRepository, ProgramacionConverter programacionConverter) {
        this.iProgramacionRepository = iProgramacionRepository;
        this.medicoRepository = medicoRepository;
        this.procedimientoRepository = procedimientoRepository;
        this.programacionConverter = programacionConverter;
    }


    @Override
    public  Optional<Programacion>  findByFechaAndIdProcedimiento(LocalDate fecha, Long idProcedimiento) {
        return iProgramacionRepository.findByFechaAndProcedimientoId(fecha,idProcedimiento);
    }

    @Override
    public List<Programacion> findByProcedimientoId(Long idProcedimiento) {
        return iProgramacionRepository.findByProcedimientoId(idProcedimiento);
    }

    @Override
    public ProgramacionResponse crear(ProgramacionRequest entidad) {
        if (iProgramacionRepository.countByFechaAndProcedimientoId(entidad.getFecha(), entidad.getIdProcedimiento()) > 0) {
            throw new IllegalArgumentException("Ya existe una programación para ese procedimiento en la fecha indicada.");
        }

        Medico medico = medicoRepository.findById(entidad.getIdMedico())
                .orElseThrow(() -> new IllegalArgumentException("Medico no encontrado"));

        Procedimiento procedimiento = procedimientoRepository.findById(entidad.getIdProcedimiento())
                .orElseThrow(() -> new IllegalArgumentException("Procedimiento no encontrado"));

        Programacion programacion = programacionConverter.toProgramacionEntity(entidad);
        programacion.setMedico(medico);
        programacion.setProcedimiento(procedimiento);
        Programacion programacionentity = iProgramacionRepository.save(programacion);
        return programacionConverter.toProgramacionResponseDTO(programacionentity);

    }

    @Override
    public ProgramacionResponse updateProgramacion(Programacion programacion) {
        return null;
    }
}
