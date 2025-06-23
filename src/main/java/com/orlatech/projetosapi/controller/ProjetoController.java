
package com.orlatech.projetosapi.controller;

import com.orlatech.projetosapi.dto.ProjetoComFuncionariosDTO;
import com.orlatech.projetosapi.dto.ProjetoDTO;
import com.orlatech.projetosapi.entity.Projeto;
import com.orlatech.projetosapi.service.ProjetoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ProjetoDTO> criarProjeto(@Valid @RequestBody ProjetoDTO dto) {
        Projeto projeto = projetoService.toEntity(dto);
        Projeto salvo = projetoService.salvar(projeto);
        return ResponseEntity.status(201).body(modelMapper.map(salvo, ProjetoDTO.class));
  }

    @GetMapping
    public ResponseEntity<List<ProjetoComFuncionariosDTO>> listarProjetos() {
        List<ProjetoComFuncionariosDTO> lista = projetoService.listarProjetosComFuncionarios();
        return ResponseEntity.ok(lista);
    }

}
