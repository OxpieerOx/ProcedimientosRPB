package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Programacion;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IProgramacionRepository extends JpaRepository<Programacion, Long> {

    Optional<Programacion> findByFechaAndProcedimientoId(LocalDate fecha, Long idProcedimiento);

    List<Programacion> findByProcedimientoId(Long idProcedimiento);

    int countByFechaAndProcedimientoId(LocalDate fecha, Long procedimientoId);
}
