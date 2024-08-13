package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProcedimientoCitasDTO {
    private Long procedimientoId;
    private String nombreProcedimiento;
    private Long totalCitas;
}
