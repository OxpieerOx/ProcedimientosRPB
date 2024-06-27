package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.exception;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.exception.common.CustomException;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.exception.common.ResourceNotFoundException;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.exception.core.ErrorResponse;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.exception.factory.ErrorResponseFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j(topic = "GlobalExceptionHandler")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ErrorResponseFactory errorFactory;

    public GlobalExceptionHandler(ErrorResponseFactory errorFactory) {
        this.errorFactory = errorFactory;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            org.springframework.web.context.request.WebRequest request) {

        log.error("MethodArgumentNotValidException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage("Validation failed");

        // Pass all errors to the ErrorResponse object
        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errorResponse.addValidationError(fieldName, errorMessage);
        });
        return errorFactory.buildResponseEntity(errorResponse);
    }

    /**
     * Handles ResourceNotFoundException.
     * @param ex the ResourceNotFoundException
     * @return the ErrorResponse object
     */
    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException ex) {
        log.error("ResourceNotFoundException: {}", ex.getMessage());
        return errorFactory.createErrorResponse(HttpStatus.NOT_FOUND, ex);
    }

    /**
     * Handles CustomException.
     * @param ex the CustomException
     * @return the ErrorResponse object
     */
    @ExceptionHandler(value = {CustomException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> customException(CustomException ex) {
        log.error("CustomException: {}", ex.getMessage());
        return errorFactory.createErrorResponse(HttpStatus.BAD_REQUEST, ex);
    }

}
