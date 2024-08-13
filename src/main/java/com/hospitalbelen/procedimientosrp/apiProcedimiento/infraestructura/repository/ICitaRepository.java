package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Cita;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ICitaRepository extends JpaRepository<Cita, Integer> {

    List<Cita> findByProgramacionId(Integer programacionId);

    @Query(value = "SELECT FORMAT(fecha, 'yyyy-MM') AS mes, COUNT(*) AS cantidad " +
            "FROM cita " +
            "GROUP BY FORMAT(fecha, 'yyyy-MM')",
            nativeQuery = true) // Usa nativeQuery para consultas SQL nativas
    List<Map<String, Object>> countCitasPorMes();

    @Query("SELECT c.financiamiento AS tipoFinanciamiento, COUNT(c) AS cantidad " +
            "FROM Cita c " +
            "GROUP BY c.financiamiento")
    List<Map<String, Object>> countCitasPorFinanciamiento();

    Long countByProgramacionIdAndEsAdicionalTrue(Long programacionId);
    Long countByProgramacionIdAndEstado(Long programacionId, EstadoCita estado);


}
