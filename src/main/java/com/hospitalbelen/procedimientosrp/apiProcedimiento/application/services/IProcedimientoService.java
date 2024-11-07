package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Procedimiento;

import java.util.List;

public interface IProcedimientoService {

    public List<Procedimiento> obtenerProcedimientosPorServicio(Long idServicio);

    Procedimiento obtenerProcedimientoPorId(Long id);
    Procedimiento crearProcedimiento(Procedimiento procedimiento);

    Procedimiento obtenerProcedimientoPorNombre(String nombre);
    Procedimiento actualizarProcedimiento(Long id, Procedimiento procedimiento);

    Procedimiento obtenerProcedimientoPorNombreYServicio(String nombre, Long idServicio);
    void eliminarProcedimiento(Long id);



}
