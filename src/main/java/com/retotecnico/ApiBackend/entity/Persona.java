package com.retotecnico.ApiBackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Table(name = "personas")
@Data
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "El campo 'Nombres' no puede estar vacío")
    private String nombres;
    @NotEmpty(message = "El campo 'apellidoPaterno' no puede estar vacío")
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int edad;
}
