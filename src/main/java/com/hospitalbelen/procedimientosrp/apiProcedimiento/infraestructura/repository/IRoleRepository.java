package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r FROM Role r INNER JOIN r.users u WHERE u.username = :username")
    List<Role> findRolesByUsername(String username);

    List<Role> findByCodigo(String codigo);
}
