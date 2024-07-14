package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Cita;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICitaRepository extends JpaRepository<Cita, Integer> {

    List<Cita> findByProgramacionId(Integer programacionId);

    Long countByProgramacionIdAndEsAdicionalTrue(Long programacionId);
    Long countByProgramacionIdAndEstado(Long programacionId, EstadoCita estado);

}
