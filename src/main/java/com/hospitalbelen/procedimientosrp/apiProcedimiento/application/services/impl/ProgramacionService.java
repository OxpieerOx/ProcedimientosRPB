package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;


import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.ProgramacionRangoRequest;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.ProgramacionRequest;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.ProgramacionResponse;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IProgramacionService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.*;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.converter.ProgramacionConverter;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.ICitaRepository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IMedicoRepository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IProcedimientoRepository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IProgramacionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        return iProgramacionRepository.findByProcedimientoIdOrderByFecha(idProcedimiento);
    }

    @Override
    public Optional<Programacion> getbyId(Long idProgarmacion) {
        return iProgramacionRepository.findById(idProgarmacion);
    }

    @Override
    public ProgramacionResponse crear(ProgramacionRequest entidad) {
        LocalDate fecha = entidad.getFecha();

        // Imprimir la fecha en consola
        System.out.println("Fecha de la programación: " + fecha);
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

    public List<Programacion> crearProgramacionesEnRango(ProgramacionRangoRequest request) {
        List<Programacion> programaciones = new ArrayList<>();

        // Validar y obtener el médico y procedimiento una vez fuera del bucle
        Medico medico = medicoRepository.findById(request.getIdMedico())
                .orElseThrow(() -> new IllegalArgumentException("Medico no encontrado"));

        Procedimiento procedimiento = procedimientoRepository.findById(request.getIdProcedimiento())
                .orElseThrow(() -> new IllegalArgumentException("Procedimiento no encontrado"));

        for (LocalDate fecha : request.getFechas()) {
            // Validar si ya existe una programación para esa fecha y procedimiento
            if (iProgramacionRepository.countByFechaAndProcedimientoId(fecha, request.getIdProcedimiento()) > 0) {
                continue; // Saltar esta fecha si ya existe una programación
            }

            Programacion nuevaProgramacion = new Programacion();
            nuevaProgramacion.setFecha(fecha);
            nuevaProgramacion.setHoraInicio(request.getHoraInicio());
            nuevaProgramacion.setHoraFin(request.getHoraFin());
            nuevaProgramacion.setTiempoPromedio(request.getTiempoPromedio());
            nuevaProgramacion.setFechaRegistro(LocalDateTime.now());
            nuevaProgramacion.setUsuarioCreador(request.getUsuarioCreador());
            nuevaProgramacion.setMedico(medico);
            nuevaProgramacion.setProcedimiento(procedimiento);

            programaciones.add(nuevaProgramacion);
        }
        return iProgramacionRepository.saveAll(programaciones);
    }

    @Override
    public void deleteById(Long id) {
         iProgramacionRepository.deleteById(id);
    }


    @Override
    public ProgramacionResponse actualizar(Long id, ProgramacionRequest entidad) {
        // Verificar si la programación existe
        Programacion existingProgramacion = iProgramacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Programación no encontrada"));

        // Verificar si ya existe una programación con el mismo procedimiento y fecha, excluyendo la programación actual
        long count = iProgramacionRepository.countByFechaAndProcedimientoIdExcludingId(entidad.getFecha(), entidad.getIdProcedimiento(), id);
        if (count > 0) {
            throw new IllegalArgumentException("Ya existe una programación para ese procedimiento en la fecha indicada.");
        }

        // Verificar citas pagadas asociadas
        long citasPagadasCount = citaRepository.countByProgramacionIdAndEstado(id, EstadoCita.PAGADO);
        long citasAdicionalesCount = citaRepository.countByProgramacionIdAndEsAdicionalTrue(id);

        // Si existen citas pagadas o adicionales, solo permitir cambiar el médico
        if ((citasPagadasCount > 0 || citasAdicionalesCount > 0) &&
                (!entidad.getHoraInicio().equals(existingProgramacion.getHoraInicio()) ||
                        !entidad.getHoraFin().equals(existingProgramacion.getHoraFin()) ||
                        !entidad.getTiempoPromedio().equals(existingProgramacion.getTiempoPromedio()) ||
                        !entidad.getFecha().equals(existingProgramacion.getFecha()) ||
                        !entidad.getIdProcedimiento().equals(existingProgramacion.getProcedimiento().getId()))) {
            throw new IllegalArgumentException("No se puede actualizar la programación ya que existen citas pagadas o adicionales asociadas y solo se permite cambiar el médico.");
        }

        boolean medicoCambiado = !entidad.getIdMedico().equals(existingProgramacion.getMedico().getId());
        Medico nuevoMedico = medicoRepository.findById(entidad.getIdMedico())
                .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado"));

        Medico medico = medicoRepository.findById(entidad.getIdMedico())
                .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado"));

        Procedimiento procedimiento = procedimientoRepository.findById(entidad.getIdProcedimiento())
                .orElseThrow(() -> new IllegalArgumentException("Procedimiento no encontrado"));

        Integer idNuevoInteger = (int) (long) id;
        // Actualizar las citas si el médico cambió
        if (medicoCambiado) {
            List<Cita> citasPorActualizar = citaRepository.findByProgramacionId(idNuevoInteger);
            for (Cita cita : citasPorActualizar) {
                // Actualizar el médico en las citas
                cita.setMedico(nuevoMedico);
                citaRepository.save(cita);
            }
        }

        Programacion programacion = programacionConverter.toProgramacionEntity(entidad);
        programacion.setId(id);
        programacion.setMedico(medico);
        programacion.setProcedimiento(procedimiento);

        Programacion programacionEntity = iProgramacionRepository.save(programacion);
        return programacionConverter.toProgramacionResponseDTO(programacionEntity);
    }

    private Integer getCurrentMedicoId(Long id) {
        return iProgramacionRepository.findById(id)
                .map(Programacion::getMedico)
                .map(Medico::getId)
                .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado en la programación"));
    }

    @Override
    public ProgramacionResponse updateProgramacion(Programacion programacion) {
        return null;
    }
}
