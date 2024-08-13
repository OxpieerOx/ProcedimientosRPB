package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.CitaFinanciamientoDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.CitaMesDTO;
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
import java.util.Map;

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
        Cita existingCita = citaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cita no encontrada con ID: " + id));

        Programacion programacion = programacionRepository.findById(citaRequest.getIdProgramacion())
                .orElseThrow(() -> new IllegalArgumentException("Programación no encontrada con ID: " + citaRequest.getIdProgramacion()));

        existingCita.setProgramacion(programacion);
        existingCita.setHoraInicio(citaRequest.getHoraInicio());
        existingCita.setHoraFin(citaRequest.getHoraFin());
        existingCita.setFecha(citaRequest.getFecha());

        Cita updatedCita = citaRepository.save(existingCita);
        return citaConverter.toCitaResponseDTO(updatedCita);
    }



    @Override
    public void deleteCita(int id) {

    }



    public List<CitaMesDTO> getCitasPorMes() {
        List<Map<String, Object>> result = citaRepository.countCitasPorMes();
        List<CitaMesDTO> citasPorMes = new ArrayList<>();

        for (Map<String, Object> map : result) {
            String mes = (String) map.get("mes");
            Long cantidad = ((Number) map.get("cantidad")).longValue();
            citasPorMes.add(new CitaMesDTO(mes, cantidad));
        }

        return citasPorMes;
    }

    public List<CitaFinanciamientoDTO> getCitasPorFinanciamiento() {
        List<Map<String, Object>> result = citaRepository.countCitasPorFinanciamiento();
        List<CitaFinanciamientoDTO> citasPorFinanciamiento = new ArrayList<>();

        for (Map<String, Object> map : result) {
            String tipoFinanciamiento = (String) map.get("tipoFinanciamiento");
            Long cantidad = ((Number) map.get("cantidad")).longValue();
            citasPorFinanciamiento.add(new CitaFinanciamientoDTO(tipoFinanciamiento, cantidad));
        }

        return citasPorFinanciamiento;
    }
}
