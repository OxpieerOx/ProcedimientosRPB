package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicioDTO {
    private Long serviceId;
    private String serviceName;
    private String serviceDescription;
}