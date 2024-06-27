package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IRolService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Role;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleService implements IRolService {

    private final IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findRolesByUsername(String username) {
        return roleRepository.findRolesByUsername(username);
    }
}
