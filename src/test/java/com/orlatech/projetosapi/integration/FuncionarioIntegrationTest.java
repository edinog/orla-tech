package com.orlatech.projetosapi.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.orlatech.projetosapi.dto.FuncionarioDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
public class FuncionarioIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void deveCriarFuncionarioComMockMvc() throws Exception {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setNome("Maria Teste");
        dto.setCpf("123.456.889-00");
        dto.setEmail("maria@teste.com");
        dto.setSalario(BigDecimal.valueOf(3500.0));

        mockMvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post("/api/funcionarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
        ).andExpect(org.springframework.test.web.servlet.result.MockMvcResultMatchers.status().isCreated());
    }
}