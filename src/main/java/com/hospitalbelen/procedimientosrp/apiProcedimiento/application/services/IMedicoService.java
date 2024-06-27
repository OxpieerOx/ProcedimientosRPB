package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Medico;

import java.util.List;

public interface IMedicoService {

    Medico findByUserId(Integer userId);

    Medico saveMedico(Medico medico);

    Medico updateMedico(Medico medico);

    void deleteMedico(Integer id);

    Medico findById(Integer id);

    List<Medico> findAll();
}