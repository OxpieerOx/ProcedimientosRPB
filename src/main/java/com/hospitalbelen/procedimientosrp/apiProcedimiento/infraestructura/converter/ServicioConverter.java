package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.converter;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.ServicioDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Servicio;
import org.springframework.stereotype.Component;

@Component
public class ServicioConverter {

    public ServicioDTO toServicioDTO(Servicio servicio) {
        return new ServicioDTO(
                servicio.getId(),
                servicio.getServiceName(),
                servicio.getServiceDescription()
        );
    }
}