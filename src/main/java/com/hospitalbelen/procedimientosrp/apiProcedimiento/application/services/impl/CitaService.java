package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.CitaRequestDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.CitaResponseDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.ICitaService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Cita;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Medico;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Programacion;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.converter.CitaConverter;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.ICitaRepository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IMedicoRepository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IProgramacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitaService implements ICitaService {

    private final ICitaRepository citaRepository;
    private final IMedicoRepository medicoRepository;
    private final IProgramacionRepository programacionRepository;

    private final CitaConverter citaConverter;

    public CitaService(ICitaRepository citaRepository, IMedicoRepository medicoRepository, IProgramacionRepository programacionRepository, CitaConverter citaConverter) {
        this.citaRepository = citaRepository;
        this.medicoRepository = medicoRepository;
        this.programacionRepository = programacionRepository;
        this.citaConverter = citaConverter;
    }

    @Override
    public List<CitaResponseDTO> getAllCitas() {
        return null;
    }

    @Override
    public Cita getCitaById(int id) {
        return null;
    }

    @Override
    public CitaResponseDTO createCita(CitaRequestDTO citaRequest) {
       Medico medico = medicoRepository.findById(citaRequest.getIdMedico())
                .orElseThrow(() -> new IllegalArgumentException("Médico no encontrado con ID: " + citaRequest.getIdMedico()));

       Programacion programacion = programacionRepository.findById(citaRequest.getIdProgramacion())
                .orElseThrow(() -> new IllegalArgumentException("Programación no encontrada con ID: " + citaRequest.getIdProgramacion()));

        Cita cita = citaConverter.toCitaEntity(citaRequest);
        cita.setProgramacion(programacion);
        cita.setMedico(medico);
        Cita citaEntity = citaRepository.save(cita);
        return citaConverter.toCitaResponseDTO(citaEntity);
    }

    @Override
    public List<Cita> getCitasByProgramacion(Integer programacionId) {
        List<Cita> citas = citaRepository.findByProgramacionId(programacionId);
        return new ArrayList<>(citas);
    }

    @Override
    public CitaResponseDTO updateCita(int id, CitaRequestDTO citaRequest) {
        return null;
    }

    @Override
    public void deleteCita(int id) {

    }
}
