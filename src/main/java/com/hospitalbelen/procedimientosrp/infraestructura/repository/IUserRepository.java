package com.hospitalbelen.procedimientosrp.infraestructura.repository;

import com.hospitalbelen.procedimientosrp.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IUserRepository  extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
