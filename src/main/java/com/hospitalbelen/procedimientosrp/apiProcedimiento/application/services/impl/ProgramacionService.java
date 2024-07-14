package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;


import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.ProgramacionRequest;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.ProgramacionResponse;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IProgramacionService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.EstadoCita;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Medico;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Procedimiento;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Programacion;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.converter.ProgramacionConverter;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.ICitaRepository;
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

    private final ICitaRepository citaRepository;
    private final IMedicoRepository medicoRepository;
    private final IProcedimientoRepository procedimientoRepository;

    private final ProgramacionConverter programacionConverter;

    public ProgramacionService(IProgramacionRepository iProgramacionRepository, ICitaRepository citaRepository, IMedicoRepository medicoRepository, IProcedimientoRepository procedimientoRepository, ProgramacionConverter programacionConverter) {
        this.iProgramacionRepository = iProgramacionRepository;
        this.citaRepository = citaRepository;
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
    public Optional<Programacion> getbyId(Long idProgarmacion) {
        return iProgramacionRepository.findById(idProgarmacion);
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
    public void deleteById(Long id) {
         iProgramacionRepository.deleteById(id);
    }


    @Override
    public ProgramacionResponse actualizar(Long id, ProgramacionRequest entidad) {
        if (!iProgramacionRepository.existsById(id)) {
            throw new IllegalArgumentException("Programación no encontrada");
        }

        long count = iProgramacionRepository.countByFechaAndProcedimientoIdExcludingId(entidad.getFecha(), entidad.getIdProcedimiento(), id);
        if (count > 0) {
            throw new IllegalArgumentException("Ya existe una programación para ese procedimiento en la fecha indicada.");
        }

        long citasPagadasCount = citaRepository.countByProgramacionIdAndEstado(id, EstadoCita.PAGADO);
        if (citasPagadasCount > 0) {
            throw new IllegalArgumentException("No se puede actualizar la programación ya que existen citas pagadas asociadas.");
        }

        // Verificar si existen citas adicionales en esta programación
        long citasAdicionalesCount = citaRepository.countByProgramacionIdAndEsAdicionalTrue(id);
        if (citasAdicionalesCount > 0) {
            throw new IllegalArgumentException("No se puede actualizar la programación ya que existen citas adicionales asociadas.");
        }
        Medico medico = medicoRepository.findById(entidad.getIdMedico())
                .orElseThrow(() -> new IllegalArgumentException("Medico no encontrado"));

        Procedimiento procedimiento = procedimientoRepository.findById(entidad.getIdProcedimiento())
                .orElseThrow(() -> new IllegalArgumentException("Procedimiento no encontrado"));

        Programacion programacion = programacionConverter.toProgramacionEntity(entidad);
        programacion.setId(id);
        programacion.setMedico(medico);
        programacion.setProcedimiento(procedimiento);
        Programacion programacionEntity = iProgramacionRepository.save(programacion);
        return programacionConverter.toProgramacionResponseDTO(programacionEntity);
    }


    @Override
    public ProgramacionResponse updateProgramacion(Programacion programacion) {
        return null;
    }
}
