package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.exception.common;

public class CustomException extends RuntimeException{
    public CustomException(String message) {super(message);}
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }
}