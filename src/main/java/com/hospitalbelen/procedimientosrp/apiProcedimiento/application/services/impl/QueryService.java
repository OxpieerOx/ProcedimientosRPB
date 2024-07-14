package com.hospitalbelen.procedimientosrp.apiProcedimiento.application.services.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class QueryService {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public QueryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> consultarPrimeraAtencion(int idPaciente) {
        String sql = "SELECT Citas.IdCita, Atenciones.IdPaciente, IdServicioIngreso, " +
                "IdMedicoIngreso, IdEspecialidadMedico, Fecha, HoraInicio, HoraFin, " +
                "FuentesFinanciamiento.Descripcion AS Financiamiento " +
                "FROM Atenciones " +
                "INNER JOIN Citas ON Atenciones.IdAtencion = Citas.IdAtencion " +
                "INNER JOIN FuentesFinanciamiento ON FuentesFinanciamiento.IdFuenteFinanciamiento = Atenciones.IdFuenteFinanciamiento " +
                "WHERE IdCuentaAtencion = ?";

        // Ejecutar la consulta utilizando jdbcTemplate.queryForMap
        Map<String, Object> primeraAtencion = jdbcTemplate.queryForMap(sql, idPaciente);

        return primeraAtencion;
    }

    public Map<String, Object> consultarDetallesPaciente(int idCuentaAtencion) {
        String sql = "SELECT " +
                "FuentesFinanciamiento.Descripcion AS Financiamiento, " +
                "Pacientes.IdPaciente, " +
                "COALESCE(Pacientes.ApellidoPaterno, '') + ' ' + " +
                "COALESCE(Pacientes.ApellidoMaterno, '') + ' ' + " +
                "COALESCE(Pacientes.PrimerNombre, '') + ' ' + " +
                "COALESCE(Pacientes.SegundoNombre, '') + ' ' + " +
                "COALESCE(Pacientes.TercerNombre, '') AS Paciente, " +
                "Pacientes.NroDocumento AS DNI, " +
                "Pacientes.NroHistoriaClinica AS HistoriaClinica " +
                "FROM Atenciones " +
                "INNER JOIN Citas ON Atenciones.IdAtencion = Citas.IdAtencion " +
                "INNER JOIN FuentesFinanciamiento ON FuentesFinanciamiento.IdFuenteFinanciamiento = Atenciones.IdFuenteFinanciamiento " +
                "INNER JOIN Pacientes ON Pacientes.IdPaciente = Atenciones.IdPaciente " +
                "WHERE IdCuentaAtencion = ?";

        // Ejecutar la consulta utilizando jdbcTemplate.queryForMap
        Map<String, Object> detallesPaciente = jdbcTemplate.queryForMap(sql, idCuentaAtencion);

        return detallesPaciente;
    }
}
