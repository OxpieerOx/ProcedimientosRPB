package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Medico;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MedicoResponse {
    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String username;
    private List<String> roles;

    public MedicoResponse(Medico medico, String username, List<String> roles) {
        this.id = medico.getId(); // O puedes decidir no incluir el ID
        this.nombre = medico.getNombre();
        this.apellido = medico.getApellido();
        this.telefono = medico.getTelefono();
        this.email = medico.getEmail();
        this.username = username;
        this.roles = roles;
    }
}