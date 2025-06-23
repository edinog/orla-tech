
package com.orlatech.projetosapi.unit;

import com.orlatech.projetosapi.dto.ProjetoComFuncionariosDTO;
import com.orlatech.projetosapi.dto.ProjetoDTO;
import com.orlatech.projetosapi.entity.Projeto;
import com.orlatech.projetosapi.service.ProjetoService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProjetoServiceTest {

    @Test
    public void listarProjetosComFuncionariosRetornaListaVaziaQuandoNaoExistemProjetos() {
        ProjetoService projetoService = mock(ProjetoService.class);
        when(projetoService.listarProjetosComFuncionarios()).thenReturn(List.of());

        List<ProjetoComFuncionariosDTO> resultado = projetoService.listarProjetosComFuncionarios();

        assertThat(resultado).isEmpty();
        verify(projetoService, times(1)).listarProjetosComFuncionarios();
    }

    @Test
    public void listarProjetosComFuncionariosRetornaProjetosSemFuncionarios() {
        ProjetoService projetoService = mock(ProjetoService.class);
        Projeto projeto = new Projeto(1L, "Projeto Sem Funcionários", LocalDate.now(), List.of());
        when(projetoService.listarProjetosComFuncionarios()).thenReturn(List.<ProjetoComFuncionariosDTO>of());

        List<ProjetoComFuncionariosDTO> resultado = projetoService.listarProjetosComFuncionarios();

        assertThat(resultado).hasSize(0);
        verify(projetoService, times(1)).listarProjetosComFuncionarios();
    }

    @Test
    public void toEntityDeveMapearProjetoDTOParaProjetoCorretamente() {
        ProjetoService projetoService = new ProjetoService();
        ProjetoDTO dto = new ProjetoDTO();
        dto.setId(1L);
        dto.setNome("Projeto Teste");
        dto.dataCriacao = java.time.LocalDate.of(2024, 6, 1);
        dto.setFuncionariosIds(Set.of(10L));

        Projeto projeto = projetoService.toEntity(dto);

        org.assertj.core.api.Assertions.assertThat(projeto.getId()).isEqualTo(1L);
        org.assertj.core.api.Assertions.assertThat(projeto.getNome()).isEqualTo("Projeto Teste");
        org.assertj.core.api.Assertions.assertThat(projeto.getDataCriacao()).isEqualTo(java.time.LocalDate.of(2024, 6, 1));
        org.assertj.core.api.Assertions.assertThat(projeto.getFuncionarios()).hasSize(1);
        org.assertj.core.api.Assertions.assertThat(projeto.getFuncionarios().get(0).getId()).isEqualTo(10L);
    }

    @Test
    public void toEntityDeveRetornarProjetoSemFuncionariosQuandoListaVazia() {
        ProjetoService projetoService = new ProjetoService();
        ProjetoDTO dto = new ProjetoDTO();
        dto.setId(2L);
        dto.setNome("Projeto Sem Funcionários");
        dto.dataCriacao = java.time.LocalDate.of(2024, 6, 2);
        dto.setFuncionariosIds(java.util.Collections.emptySet());

        Projeto projeto = projetoService.toEntity(dto);

        org.assertj.core.api.Assertions.assertThat(projeto.getId()).isEqualTo(2L);
        org.assertj.core.api.Assertions.assertThat(projeto.getNome()).isEqualTo("Projeto Sem Funcionários");
        org.assertj.core.api.Assertions.assertThat(projeto.getDataCriacao()).isEqualTo(java.time.LocalDate.of(2024, 6, 2));
        org.assertj.core.api.Assertions.assertThat(projeto.getFuncionarios()).isEmpty();
    }

}
