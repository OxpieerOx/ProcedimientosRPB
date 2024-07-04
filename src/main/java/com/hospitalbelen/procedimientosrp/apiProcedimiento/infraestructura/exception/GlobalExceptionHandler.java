package com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.exception;

import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.exception.common.CustomException;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.exception.common.ResourceNotFoundException;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.exception.core.ErrorResponse;
import com.hospitalbelen.procedimientosrp.apiProcedimiento.infraestructura.exception.factory.ErrorResponseFactory;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
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

    /**
     * Handles IllegalArgumentException.
     * @param ex the IllegalArgumentException
     * @return the ErrorResponse object
     */
    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("IllegalArgumentException: {}", ex.getMessage());
        return errorFactory.createErrorResponse(HttpStatus.BAD_REQUEST, ex);
    }

    /**
     * Handles AccessDeniedException.
     * @param ex the AccessDeniedException
     * @return the ErrorResponse object
     */
    @ExceptionHandler(value = {AccessDeniedException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        log.error("AccessDeniedException: {}", ex.getMessage());
        return errorFactory.createErrorResponse(HttpStatus.FORBIDDEN, "No autenticado");
    }

    /**
     * Handles ExpiredJwtException.
     * @param ex the ExpiredJwtException
     * @return the ErrorResponse object
     */
    @ExceptionHandler(value = {ExpiredJwtException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> handleExpiredJwtException(ExpiredJwtException ex) {
        log.error("ExpiredJwtException: {}", ex.getMessage());
        return errorFactory.createErrorResponse(HttpStatus.UNAUTHORIZED, "JWT expirado. No autenticado");
    }
}
