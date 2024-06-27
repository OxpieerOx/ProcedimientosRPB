package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.ServicioDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Servicio;

import java.util.List;
import java.util.Set;

public interface IServicioService {

    public List<ServicioDTO> getServicesForUser(String username);

    public List<ServicioDTO> getAllServices();
}
