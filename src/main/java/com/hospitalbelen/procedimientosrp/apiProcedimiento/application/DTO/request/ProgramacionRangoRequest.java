package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.request;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity.EstadoCita;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.utils.constant.ProcedimientoConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProgramacionRangoRequest {
    private List<LocalDate> fechas;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Long tiempoPromedio;
    private String usuarioCreador;
    private Integer idMedico;
    private Long idProcedimiento;
}
