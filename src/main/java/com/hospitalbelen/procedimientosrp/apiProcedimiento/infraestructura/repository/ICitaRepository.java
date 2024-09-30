package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response.CitaMedicoProcedimientoResponse;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Cita;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.EstadoCita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
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

    @Query(value = "SELECT YEAR(c.fecha) AS año, " +
            "MONTH(c.fecha) AS mes, " +
            "m.nombre AS medicoNombre, " +
            "p_proc.nombre AS procedimientoNombre, " +
            "COUNT(*) AS cantidad " +
            "FROM cita c " +
            "LEFT JOIN programacion p ON c.idProgramacion = p.id " +
            "LEFT JOIN procedimiento p_proc ON p.id_procedimiento = p_proc.id " +
            "LEFT JOIN medico m ON c.idMedico = m.id " +
            "WHERE c.idProcedimiento IS NULL " +
            "GROUP BY YEAR(c.fecha), MONTH(c.fecha), m.nombre, p_proc.nombre",
            nativeQuery = true)
    List<Map<String, Object>> countCitasConProcedimientoNull();

    @Query(value = "SELECT YEAR(c.fecha) AS año, " +
            "MONTH(c.fecha) AS mes, " +
            "m.nombre AS medicoNombre, " +
            "p_proc.nombre AS procedimientoNombre, " +
            "COUNT(*) AS cantidad " +
            "FROM cita c " +
            "LEFT JOIN procedimiento p_proc ON c.idProcedimiento = p_proc.id " +
            "LEFT JOIN medico m ON c.idMedico = m.id " +
            "WHERE c.idProcedimiento IS NOT NULL " +
            "GROUP BY YEAR(c.fecha), MONTH(c.fecha), m.nombre, p_proc.nombre",
            nativeQuery = true)
    List<Map<String, Object>> countCitasConProcedimientoNotNull();

    @Query("SELECT c FROM Cita c WHERE " +
            "(:username IS NULL OR c.medico.user.username = :username) AND " +
            "(:fecha IS NULL OR c.fecha = :fecha) AND " +
            "(:idPaciente IS NULL OR c.idPaciente = :idPaciente) AND " +
            "(:nroCuenta IS NULL OR c.nroCuenta = :nroCuenta)")
    List<Cita> filtrarCitas(
            @Param("username") String username,   // Ahora filtra por username
            @Param("fecha") LocalDate fecha,
            @Param("idPaciente") Integer idPaciente,
            @Param("nroCuenta") String nroCuenta
    );


}
