package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request.MedicoRequestDTO;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.IMedicoService;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Medico;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Role;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.User;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IMedicoRepository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IRoleRepository;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService implements IMedicoService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;
    private  final IMedicoRepository medicoRepository;

    public MedicoService(IMedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public Medico findByUserId(Integer userId) {
        return medicoRepository.findByUserId(userId);
    }



    @Override
    public Medico saveMedico(MedicoRequestDTO medicoRequestDTO, List<Long> roleIds) {
        // 1. Crear el User
        String username = medicoRequestDTO.getNombre().charAt(0) + medicoRequestDTO.getApellido();
        Optional<User> existingUserOptional = userRepository.findByUsername(username.toLowerCase());

        User user = new User();
        if (existingUserOptional.isPresent()) {
            user = existingUserOptional.get();
        } else {
            user = new User();
            user.setUsername(username.toLowerCase()); // Convertir a minúsculas
            String password = passwordEncoder.encode("123456"); // Encriptar contraseña
            user.setPassword(password);

            // 2. Asignar roles recibidos
            for (Long roleId : roleIds) {
                Role role = roleRepository.findById(roleId)
                        .orElseThrow(() -> new RuntimeException("Role not found for ID: " + roleId));
                user.getRoles().add(role);
            }

            // Guardar el User
            user = userRepository.save(user);
        }

        // 3. Crear el Medico
        Medico medico = new Medico();
        medico.setNombre(medicoRequestDTO.getNombre());
        medico.setApellido(medicoRequestDTO.getApellido());
        medico.setTelefono(medicoRequestDTO.getTelefono());
        medico.setEmail(medicoRequestDTO.getEmail());
        medico.setUserId(user.getId()); // Asignar el ID del User

        // Guardar el Medico
        return medicoRepository.save(medico);
    }


    @Override
    public Medico updateMedico(MedicoRequestDTO medicoRequestDTO, Integer medicoId, List<Long> roleIds) {
        // 1. Buscar el Medico existente
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Medico not found with ID: " + medicoId));

        // 2. Actualizar atributos del Medico
        medico.setNombre(medicoRequestDTO.getNombre());
        medico.setApellido(medicoRequestDTO.getApellido());
        medico.setTelefono(medicoRequestDTO.getTelefono());
        medico.setEmail(medicoRequestDTO.getEmail());

        // 3. Actualizar los roles del User asociado
        User user = userRepository.findById(medico.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found for Medico ID: " + medicoId));

        // Limpiar roles actuales
        user.getRoles().clear();

        // Asignar nuevos roles
        for (Long roleId : roleIds) {
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Role not found for ID: " + roleId));
            user.getRoles().add(role);
        }

        // Guardar el User con los roles actualizados
        userRepository.save(user);

        // Guardar el Medico actualizado
        return medicoRepository.save(medico);
    }

    @Override
    public void deleteMedico(Integer id) {
        medicoRepository.deleteById(id);
    }

    public List<Medico> findAllMedicosWithRoles() {
        return medicoRepository.findAllMedicosWithRoles();
    }

    @Override
    public Medico findById(Integer id) {
        Optional<Medico> medico = medicoRepository.findById(id);
        return medico.orElse(null);
    }

    @Override
    public List<Medico> findAll() {
        return medicoRepository.findAll();
    }

    @Override
    public List<Medico> findByServicioId(Long servicioId) {
        return medicoRepository.findByServicioId(servicioId);
    }
}