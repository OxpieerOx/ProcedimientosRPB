package com.hospitalbelen.procedimientosrp.apiProcedimiento.domain.entity;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "servicio")
@Entity
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String serviceName;
    @Column(name = "descripcion", nullable = false, unique = true)
    private String serviceDescription;

    @OneToMany(mappedBy = "servicio")
    private List<Procedimiento> procedimientos;

    @ManyToMany(mappedBy = "services")
    private Set<Role> roles = new HashSet<>();

}