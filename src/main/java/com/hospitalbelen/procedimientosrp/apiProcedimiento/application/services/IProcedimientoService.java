package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Procedimiento;

import java.util.List;

public interface IProcedimientoService {

    public List<Procedimiento> obtenerProcedimientosPorServicio(Long idServicio);

}
