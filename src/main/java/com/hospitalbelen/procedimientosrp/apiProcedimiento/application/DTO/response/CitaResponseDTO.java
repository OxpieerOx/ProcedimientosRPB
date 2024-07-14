package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.EstadoCita;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CitaResponseDTO {
    private Integer idPaciente;
    private String nroCuenta;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Long idProgramacion;
    private int idMedico;
    private String usuarioCreador;
    private boolean esAdicional;
    private EstadoCita estado;
}
