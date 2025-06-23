
package com.orlatech.projetosapi.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "funcionario")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @javax.validation.constraints.NotBlank
    private String nome;

    @Pattern(regexp = "\\d{11}", message = "CPF deve conter exatamente 11 dígitos numéricos")
    @Column(nullable = false, length = 11)
    @javax.validation.constraints.Pattern(regexp="\\d{11}")
    private String cpf;

    @Column(nullable = false, unique = true)
    @javax.validation.constraints.Email
    private String email;

    @Column(nullable = false)
    @javax.validation.constraints.NotNull
    @javax.validation.constraints.DecimalMin("0.0")
    private BigDecimal salario;

    @ManyToMany(mappedBy = "funcionarios")
    private List<Projeto> projetos = new ArrayList<>();

}
