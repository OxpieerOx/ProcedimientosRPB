package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IProcedimientoService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Procedimiento;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IProcedimientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
