
package com.orlatech.projetosapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orlatech.projetosapi.dto.ProjetoDTO;
import com.orlatech.projetosapi.entity.Projeto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjetoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    private ProjetoDTO dto;

    @Test
    void deveCriarProjetoComMockMvc() throws Exception {
        dto = new ProjetoDTO(); // Inicialização corrigida
        dto.setNome("Projeto de Integração");
        dto.setDataCriacao(LocalDate.now());
        dto.setFuncionariosIds(Collections.singleton(1L));

        mockMvc.perform(post("/api/projetos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

}
