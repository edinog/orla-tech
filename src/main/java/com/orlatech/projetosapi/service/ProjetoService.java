
package com.orlatech.projetosapi.service;

import com.orlatech.projetosapi.dto.FuncionarioDTO;
import com.orlatech.projetosapi.dto.ProjetoComFuncionariosDTO;
import com.orlatech.projetosapi.dto.ProjetoDTO;
import com.orlatech.projetosapi.entity.Funcionario;
import com.orlatech.projetosapi.entity.Projeto;
import com.orlatech.projetosapi.repository.ProjetoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    public Projeto salvar(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public List<Projeto> listar() {
        return projetoRepository.findAll();
    }

    public Projeto toEntity(ProjetoDTO dto) {
           Projeto mapProjeto = new Projeto();
            mapProjeto.setId(dto.getId());
            mapProjeto.setNome(dto.getNome());
            mapProjeto.setDataCriacao(dto.dataCriacao);
            List<Funcionario> mapFuncionario = new ArrayList<>();
            dto.getFuncionariosIds().forEach(funcionarioId -> {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(funcionarioId);
                mapFuncionario.add(funcionario);
            });
            mapProjeto.setFuncionarios(mapFuncionario);
        return mapProjeto;
    }

    public List<ProjetoComFuncionariosDTO> listarProjetosComFuncionarios() {
        List<Projeto> projetos = projetoRepository.buscarProjetosComFuncionarios();

        return projetos.stream().map(p -> new ProjetoComFuncionariosDTO(
                p.getId(),
                p.getNome(),
                p.getDataCriacao(),
                p.getFuncionarios().stream().map(f -> new FuncionarioDTO(
                        f.getId(),
                        f.getNome(),
                        f.getCpf(),
                        f.getEmail(),
                        f.getSalario()
                )).collect(Collectors.toList())
        )).collect(Collectors.toList());
    }


}
