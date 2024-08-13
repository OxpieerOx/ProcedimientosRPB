package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Medico;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Procedimiento;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Programacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IProcedimientoRepository extends JpaRepository<Procedimiento, Long> {

    List<Procedimiento> findByServicioId(Long idServicio);

    @Query("SELECT p.id AS procedimientoId, p.nombre AS nombreProcedimiento, COUNT(c) AS totalCitas " +
            "FROM Cita c " +
            "JOIN c.programacion prog " +
            "JOIN prog.procedimiento p " +
            "GROUP BY p.id, p.nombre")
    List<Object[]> countCitasByProcedimiento();

}
