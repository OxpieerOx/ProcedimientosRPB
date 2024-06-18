package com.hospitalbelen.procedimientosrp.application.DTO.request;

import com.hospitalbelen.procedimientosrp.infraestructura.utils.constant.ProcedimientoConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {
    @NotNull
    @NotBlank(message = ProcedimientoConstant.ValidationMessages.NOT_BLANK_MESSAGE)
    private String username;

    @NotNull
    @NotBlank(message = ProcedimientoConstant.ValidationMessages.NOT_BLANK_MESSAGE)
    private String password;
    
}
