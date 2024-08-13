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
    
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class CitaRequestDTO {
        private Integer idPaciente;
        private String nroCuenta;
        private LocalDate fecha;
        private LocalTime horaInicio;
        private LocalTime horaFin;
        private Long idProgramacion;
        private EstadoCita estado;
        private int idMedico;
        private String usuarioCreador;
        private boolean esAdicional;
        private String financiamiento;
    }
