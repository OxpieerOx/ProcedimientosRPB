    package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public class CitaMedicoProcedimientoResponse {
        private String medicoNombre;             // Nombre del médico
        private String procedimientoNombre;     // Nombre del procedimiento
        private Integer año;                    // Año extraído de la fecha de la cita
        private Integer mes;                    // Mes extraído de la fecha de la cita
        private Long cantidad;
    }
