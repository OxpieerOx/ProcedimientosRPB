package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Programacion;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IProgramacionRepository extends JpaRepository<Programacion, Long> {

    Optional<Programacion> findByFechaAndProcedimientoId(LocalDate fecha, Long idProcedimiento);
    boolean existsById(Long id);
    List<Programacion> findByProcedimientoId(Long idProcedimiento);

    @Query("SELECT COUNT(p) FROM Programacion p WHERE p.fecha = :fecha AND p.procedimiento.id = :idProcedimiento AND p.id <> :id")
    long countByFechaAndProcedimientoIdExcludingId(@Param("fecha") LocalDate fecha, @Param("idProcedimiento") Long idProcedimiento, @Param("id") Long id);

    int countByFechaAndProcedimientoId(LocalDate fecha, Long procedimientoId);
}
