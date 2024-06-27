package com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;

@Entity
@Table(name = "programacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Programacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    @Column(name = "tiempo_promedio")
    private Long tiempoPromedio;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "usuario_creador")
    private String usuarioCreador;
    @ManyToOne
    @JoinColumn(name = "id_medico", nullable = false)
    private Medico medico;


    @ManyToOne
    @JoinColumn(name = "id_procedimiento", nullable = false)
    private Procedimiento procedimiento;
}
