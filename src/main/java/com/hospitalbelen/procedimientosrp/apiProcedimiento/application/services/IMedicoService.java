package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.MedicoRequestDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Medico;

import java.util.List;

public interface IMedicoService {

    Medico findByUserId(Integer userId);

    Medico saveMedico(MedicoRequestDTO medico, List<Long> roleIds );

    Medico updateMedico(MedicoRequestDTO medicoRequestDTO, Integer medicoId, List<Long> roleIds);

    void deleteMedico(Integer id);

    Medico findById(Integer id);

    List<Medico> findAll();
    List<Medico> findByServicioId(Long servicioId);
}