package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMedicoRepository extends JpaRepository<Medico, Integer> {

    @Query("SELECT m FROM Medico m WHERE m.userId = :userId")
    Medico findByUserId(@Param("userId") Integer userId);


    @Query("SELECT DISTINCT m FROM Medico m " +
            "JOIN m.user u " +
            "JOIN u.roles r " +
            "JOIN r.services s " +
            "WHERE s.id = :servicioId")
    List<Medico> findByServicioId(@Param("servicioId") Long servicioId);

}