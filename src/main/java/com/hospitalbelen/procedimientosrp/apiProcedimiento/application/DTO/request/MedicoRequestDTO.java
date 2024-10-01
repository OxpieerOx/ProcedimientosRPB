package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicoRequestDTO {
    private Integer userId;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
}