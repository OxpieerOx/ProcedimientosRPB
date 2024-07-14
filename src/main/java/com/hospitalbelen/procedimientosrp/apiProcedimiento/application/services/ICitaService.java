package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.CitaRequestDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.CitaResponseDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Cita;

import java.util.List;

public interface ICitaService {
    List<CitaResponseDTO> getAllCitas();

    Cita getCitaById(int id);

    List<Cita> getCitasByProgramacion(Integer programacionId);
    CitaResponseDTO createCita(CitaRequestDTO citaRequest);

    CitaResponseDTO updateCita(int id, CitaRequestDTO citaRequest);

    void deleteCita(int id);
}