package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.CitaFinanciamientoDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.CitaMesDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.CitaRequestDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.CitaMedicoProcedimientoResponse;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.CitaResponseDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.ICitaService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Cita;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Medico;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Procedimiento;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Programacion;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.converter.CitaConverter;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.ICitaRepository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IMedicoRepository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IProcedimientoRepository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IProgramacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CitaService implements ICitaService {
    private final IProcedimientoRepository procedimientoRepository;
    private final ICitaRepository citaRepository;
    private final IMedicoRepository medicoRepository;
    private final IProgramacionRepository programacionRepository;

    private final CitaConverter citaConverter;

    public CitaService(IProcedimientoRepository procedimientoRepository, ICitaRepository citaRepository, IMedicoRepository medicoRepository, IProgramacionRepository programacionRepository, CitaConverter citaConverter) {
        this.procedimientoRepository = procedimientoRepository;
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
        if (citaRequest.getIdProcedimiento() != null) {
            Procedimiento procedimiento = procedimientoRepository.findById(citaRequest.getIdProcedimiento())
                    .orElse(null);
            cita.setProcedimiento(procedimiento);  // Si no se encuentra, se dejará como null
        } else {
            cita.setProcedimiento(null);
        }

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

        // Verificar que la nueva fecha no sea anterior a la fecha existente
        if (citaRequest.getFecha().isBefore(existingCita.getFecha())) {
            throw new IllegalArgumentException("La nueva fecha de la cita no puede ser anterior a la fecha de la cita existente.");
        }

        existingCita.setProgramacion(programacion);
        existingCita.setHoraInicio(citaRequest.getHoraInicio());
        existingCita.setHoraFin(citaRequest.getHoraFin());
        existingCita.setFecha(citaRequest.getFecha());
        existingCita.setEstado(citaRequest.getEstado());
        existingCita.setInformeDiagnostico(citaRequest.getInformeDiagnostico());
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

    public List<CitaMedicoProcedimientoResponse> getCitasCountByMedicoAndProcedimiento() {
        // Consultas a la base de datos
        List<Map<String, Object>> citasConProcedimientoNull = citaRepository.countCitasConProcedimientoNull();
        List<Map<String, Object>> citasConProcedimientoNotNull = citaRepository.countCitasConProcedimientoNotNull();

        // Mapear los resultados de citas con procedimiento nulo
        List<CitaMedicoProcedimientoResponse> citasNull = citasConProcedimientoNull.stream()
                .map(result -> new CitaMedicoProcedimientoResponse(
                        (String) result.get("medicoNombre"),
                        (String) result.get("procedimientoNombre"),
                        (String) result.get("servicioNombre"), // Agregar servicio
                        (Integer) result.get("año"),
                        (Integer) result.get("mes"),
                        ((Number) result.get("cantidad")).longValue()
                ))
                .collect(Collectors.toList());

        // Mapear los resultados de citas con procedimiento no nulo
        List<CitaMedicoProcedimientoResponse> citasNotNull = citasConProcedimientoNotNull.stream()
                .map(result -> new CitaMedicoProcedimientoResponse(
                        (String) result.get("medicoNombre"),
                        (String) result.get("procedimientoNombre"),
                        (String) result.get("servicioNombre"), // Agregar servicio
                        (Integer) result.get("año"),
                        (Integer) result.get("mes"),
                        ((Number) result.get("cantidad")).longValue()
                ))
                .collect(Collectors.toList());

        // Combinar las listas
        List<CitaMedicoProcedimientoResponse> result = new ArrayList<>();
        result.addAll(citasNull);
        result.addAll(citasNotNull);

        // Ordenar por año y mes
        result.sort(Comparator.comparing(CitaMedicoProcedimientoResponse::getAño)
                .thenComparing(CitaMedicoProcedimientoResponse::getMes));

        return result;
    }


    public List<Cita> filtrarCitas(String username, String fecha, Integer idPaciente, String nroCuenta) {
        LocalDate localDate = null;
        if (fecha != null && !fecha.isEmpty()) {
            localDate = LocalDate.parse(fecha); // Conversión de fecha a LocalDate
        }

        return citaRepository.filtrarCitas(username, localDate, idPaciente, nroCuenta);
    }

}
