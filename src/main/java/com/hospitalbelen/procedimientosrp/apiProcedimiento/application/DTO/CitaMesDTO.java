package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CitaMesDTO {

    private String mes; // Representa el mes en formato YYYY-MM
    private Long cantidad; // Número de citas en ese mes
}
