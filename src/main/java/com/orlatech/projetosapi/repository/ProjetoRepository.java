
package com.orlatech.projetosapi.repository;

import com.orlatech.projetosapi.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

    @Query("SELECT DISTINCT p FROM Projeto p LEFT JOIN FETCH p.funcionarios")
    List<Projeto> buscarProjetosComFuncionarios();

}

