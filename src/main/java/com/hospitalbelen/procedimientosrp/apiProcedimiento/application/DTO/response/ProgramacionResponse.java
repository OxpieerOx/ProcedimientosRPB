package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Medico;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.Procedimiento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramacionResponse {

    private Long id;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Long tiempoPromedio;
    private LocalDateTime fechaRegistro;
    private String usuarioCreador;
    private Integer idMedico;
    private Long idProcedimiento;

}
