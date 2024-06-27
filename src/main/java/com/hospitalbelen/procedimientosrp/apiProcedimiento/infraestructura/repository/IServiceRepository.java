package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Role;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IServiceRepository  extends JpaRepository<Servicio, Long> {

    @Query("SELECT s FROM Servicio s JOIN s.roles r JOIN r.users u WHERE u.username = :username")
    List<Servicio> findServicesByUsername(@Param("username") String username);
}
