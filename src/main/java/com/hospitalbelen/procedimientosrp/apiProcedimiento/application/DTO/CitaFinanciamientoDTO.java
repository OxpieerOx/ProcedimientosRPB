package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CitaFinanciamientoDTO {

    private String tipoFinanciamiento; // Tipo de financiamiento
    private Long cantidad; // Número de citas con ese tipo de financiamiento
}
