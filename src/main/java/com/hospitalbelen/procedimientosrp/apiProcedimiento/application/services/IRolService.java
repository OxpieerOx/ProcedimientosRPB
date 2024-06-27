package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Role;

import java.util.List;

public interface IRolService {

    List<Role> findRolesByUsername(String username);
}
