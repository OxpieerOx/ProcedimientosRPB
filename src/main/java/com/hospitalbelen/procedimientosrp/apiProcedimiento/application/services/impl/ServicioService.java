package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.ServicioDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IServicioService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.converter.ServicioConverter;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ServicioService implements IServicioService {
    private final ServicioConverter servicioConverter;
    private final IServiceRepository serviceRepository;

    public ServicioService(ServicioConverter servicioConverter, IServiceRepository serviceRepository) {
        this.servicioConverter = servicioConverter;
        this.serviceRepository = serviceRepository;
    }


    public List<ServicioDTO> getServicesForUser(String username) {
        return serviceRepository.findServicesByUsername(username).stream()
                .map(servicioConverter::toServicioDTO)
                .collect(Collectors.toList());
    }
}
