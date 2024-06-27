package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IProcedimientoService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Procedimiento;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IProcedimientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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
}
