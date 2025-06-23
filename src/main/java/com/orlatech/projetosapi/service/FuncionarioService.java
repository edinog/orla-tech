package com.orlatech.projetosapi.service;

import com.orlatech.projetosapi.dto.FuncionarioDTO;
import com.orlatech.projetosapi.entity.Funcionario;
import com.orlatech.projetosapi.repository.FuncionarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    @Autowired
    public FuncionarioRepository funcionarioRepository;

    @Autowired
    public ModelMapper modelMapper;

    public Funcionario salvar(FuncionarioDTO dto) {
        if (funcionarioRepository.existsByCpf(dto.getCpf())) {
            throw new IllegalArgumentException("Funcionário com CPF já cadastrado.");
        }
        dto.setCpf(dto.getCpf() != null ? dto.getCpf().replaceAll("\\D", "") : null);
        Funcionario funcionario = modelMapper.map(dto, Funcionario.class);
        return funcionarioRepository.save(funcionario);
    }
}
