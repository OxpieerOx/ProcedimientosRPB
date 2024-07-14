package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IMedicoService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Medico;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService implements IMedicoService {


    private  final IMedicoRepository medicoRepository;

    public MedicoService(IMedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public Medico findByUserId(Integer userId) {
        return medicoRepository.findByUserId(userId);
    }

    @Override
    public Medico saveMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    @Override
    public Medico updateMedico(Medico medico) {
        return medicoRepository.save(medico);
    }

    @Override
    public void deleteMedico(Integer id) {
        medicoRepository.deleteById(id);
    }

    @Override
    public Medico findById(Integer id) {
        Optional<Medico> medico = medicoRepository.findById(id);
        return medico.orElse(null);
    }

    @Override
    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    @Override
    public List<Medico> findByServicioId(Long servicioId) {
        return medicoRepository.findByServicioId(servicioId);
    }
}