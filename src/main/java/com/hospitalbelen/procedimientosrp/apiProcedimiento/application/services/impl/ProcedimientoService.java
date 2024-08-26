package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.ProcedimientoCitasDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IProcedimientoService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Procedimiento;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IProcedimientoRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProcedimientoService implements IProcedimientoService {

    private final IProcedimientoRepository procedimientoRepository;

    public ProcedimientoService(IProcedimientoRepository procedimientoRepository) {
        this.procedimientoRepository = procedimientoRepository;
    }

    @Override
    public List<Procedimiento> obtenerProcedimientosPorServicio(Long idServicio) {
        return procedimientoRepository.findByServicioId(idServicio);
    }
    @Override
    public Procedimiento obtenerProcedimientoPorId(Long id) {
        Optional<Procedimiento> procedimiento = procedimientoRepository.findById(id);
        return procedimiento.orElse(null);
    }

    @Override
    public Procedimiento crearProcedimiento(Procedimiento procedimiento) {
        return procedimientoRepository.save(procedimiento);
    }

    @Override
    public Procedimiento obtenerProcedimientoPorNombre(String nombre) {
        return procedimientoRepository.findByNombre(nombre);
    }

    public List<ProcedimientoCitasDTO> getCountCitasByProcedimiento() {
        List<Object[]> citasProgramacion = procedimientoRepository.countCitasByProcedimientoInProgramacion();
        List<Object[]> citasCita = procedimientoRepository.countCitasByProcedimientoInCita();

        Map<Long, ProcedimientoCitasDTO> resultMap = new HashMap<>();

        // Procesar citas de Programacion
        for (Object[] result : citasProgramacion) {
            Long procedimientoId = (Long) result[0];
            String nombreProcedimiento = (String) result[1];
            Long totalCitas = (Long) result[2];

            // Filtrar el procedimiento con nombre "General"
            if (!"General".equalsIgnoreCase(nombreProcedimiento)) {
                resultMap.put(procedimientoId, new ProcedimientoCitasDTO(procedimientoId, nombreProcedimiento, totalCitas));
            }
        }

        // Procesar citas directas de Cita
        for (Object[] result : citasCita) {
            Long procedimientoId = (Long) result[0];
            String nombreProcedimiento = (String) result[1];
            Long totalCitas = (Long) result[2];

            // Filtrar el procedimiento con nombre "General"
            if (!"General".equalsIgnoreCase(nombreProcedimiento)) {
                if (resultMap.containsKey(procedimientoId)) {
                    ProcedimientoCitasDTO dto = resultMap.get(procedimientoId);
                    dto.setTotalCitas(dto.getTotalCitas() + totalCitas);
                } else {
                    resultMap.put(procedimientoId, new ProcedimientoCitasDTO(procedimientoId, nombreProcedimiento, totalCitas));
                }
            }
        }

        return new ArrayList<>(resultMap.values());
    }



    @Override
    public Procedimiento actualizarProcedimiento(Long id, Procedimiento procedimiento) {
        if (procedimientoRepository.existsById(id)) {
            procedimiento.setId(id);
            return procedimientoRepository.save(procedimiento);
        }
        return null;
    }

    @Override
    public void eliminarProcedimiento(Long id) {
        procedimientoRepository.deleteById(id);
    }
}
