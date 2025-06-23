package com.orlatech.projetosapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class ProjetoComFuncionariosDTO {
    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private List<FuncionarioDTO> funcionarios;

}

