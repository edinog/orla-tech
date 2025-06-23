package com.orlatech.projetosapi.controller;

import com.orlatech.projetosapi.dto.FuncionarioDTO;
import com.orlatech.projetosapi.entity.Funcionario;
import com.orlatech.projetosapi.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping
    public ResponseEntity<Funcionario> cadastrar(@Valid @RequestBody FuncionarioDTO dto) {
        Funcionario salvo = funcionarioService.salvar(dto);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }
}
