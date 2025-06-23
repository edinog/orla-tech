
package com.orlatech.projetosapi.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class ProjetoDTO {
    public Long id;
    public String nome;
    public LocalDate dataCriacao;
    public Set<Long> funcionariosIds;
}
