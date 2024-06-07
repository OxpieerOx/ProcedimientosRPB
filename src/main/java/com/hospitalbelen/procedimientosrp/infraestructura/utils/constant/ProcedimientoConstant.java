package com.hospitalbelen.procedimientosrp.infraestructura.utils.constant;

public class ProcedimientoConstant {

    public static final String API_BASE_PATH = "/api/v1/";

    public static final String correoMaster="notificaciones.inclub@gmail.com";

    public static final String NAME_SERVICE_ACCOUNT = "notification";

    public static final String TEST = "The %s field should not be blank";

    public static final String UsuarioMensajeCreado = "Notificacion Registro Usuario Enviado";
    public static final String TipoUsuario = "USUARIO";

    public static final String AsuntoCorreoUsuario = "Creacion de Usuario";

    public static final String AsuntoPagoAceptado = "Pago Aceptado";

    public static final String AsuntocambioTipodePago= "Notificación Inclub";

    public static final String AsuntoCorreoCotizacionPagada = "Cotización Pagada y Voucher Enviado";

    public static final String AsuntoCorreoPagoPosterior = "Pago Posterior Pendiente";

    public static final String AsuntoCorreoAlertaSponsor = "Alerta de Patrocinador";

    public static final class ErrorMessages {

        public static final String MALFORMED_JSON_REQUEST = "Malformed JSON request";
        public static final String WRITABLE_ERROR = "Error writing JSON output";
        public static final String METHOD_NOT_FOUND = "Could not find the %s method for URL %s";
        public static final String DATABASE_ERROR = "Database error";
        public static final String VALIDATION_MESSAGE = "Validation error. Check 'errors' field for details.";

        private ErrorMessages() {
            throw new AssertionError("ErrorMessages class should not be instantiated.");
        }
    }

    public static final class ValidationMessages {
        public static final String NOT_BLANK_MESSAGE = "The name field should not be blank";



        private ValidationMessages() {
            throw new AssertionError("ValidationMessages class should not be instantiated.");
        }
    }
}
