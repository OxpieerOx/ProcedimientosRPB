package com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "idPaciente", nullable = false, length = 50)
    private Integer idPaciente;

    @Column(name = "nroCuenta", nullable = false, length = 20)
    private String nroCuenta;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "horaInicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "horaFin", nullable = false)
    private LocalTime horaFin;

    @ManyToOne
    @JoinColumn(name = "idProgramacion", nullable = false)
    private Programacion programacion;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 20)
    private EstadoCita estado;

    @ManyToOne
    @JoinColumn(name = "idMedico", nullable = false)
    private Medico medico;

    @Column(name = "usuarioCreador", nullable = false, length = 50)
    private String usuarioCreador;

    @Column(name = "fechaRegistro", nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "esAdicional", nullable = false)
    private boolean esAdicional;

    @Column(name = "financiamiento", nullable = false, length = 255)
    private String financiamiento;

    @ManyToOne
    @JoinColumn(name = "idProcedimiento", nullable = true)  // nullable = true indica que es opcional
    private Procedimiento procedimiento;


    @Column(name = "diagnostico", nullable = true, columnDefinition = "TEXT")
    private String informeDiagnostico;
}