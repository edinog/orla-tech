package com.orlatech.projetosapi.unit;

import com.orlatech.projetosapi.dto.FuncionarioDTO;
import com.orlatech.projetosapi.entity.Funcionario;
import com.orlatech.projetosapi.repository.FuncionarioRepository;
import com.orlatech.projetosapi.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FuncionarioServiceTest {

    private FuncionarioRepository funcionarioRepository;
    private ModelMapper modelMapper;
    private FuncionarioService funcionarioService;

    @BeforeEach
    void configurar() {
        funcionarioRepository = mock(FuncionarioRepository.class);
        modelMapper = new ModelMapper();
        funcionarioService = new FuncionarioService();
        funcionarioService.funcionarioRepository = funcionarioRepository;
        funcionarioService.modelMapper = modelMapper;
    }

    @Test
    void deveSalvarFuncionarioComCpfValido() {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setCpf("123.456.789-00");
        dto.setNome("João Silva");

        when(funcionarioRepository.existsByCpf("12345678900")).thenReturn(false);
        when(funcionarioRepository.save(any(Funcionario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Funcionario resultado = funcionarioService.salvar(dto);

        assertThat(resultado.getCpf()).isEqualTo("12345678900");
        assertThat(resultado.getNome()).isEqualTo("João Silva");
        verify(funcionarioRepository, times(1)).save(any(Funcionario.class));
    }

    @Test
    void deveLancarExcecaoQuandoCpfJaCadastrado() {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setCpf("12345678900");
        dto.setNome("João Silva");

        when(funcionarioRepository.existsByCpf("12345678900")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> funcionarioService.salvar(dto));
        verify(funcionarioRepository, never()).save(any(Funcionario.class));
    }

    @Test
    void deveSalvarFuncionarioComCpfNulo() {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setCpf(null);
        dto.setNome("Maria Souza");

        when(funcionarioRepository.existsByCpf(null)).thenReturn(false);
        when(funcionarioRepository.save(any(Funcionario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Funcionario resultado = funcionarioService.salvar(dto);

        assertThat(resultado.getCpf()).isNull();
        assertThat(resultado.getNome()).isEqualTo("Maria Souza");
        verify(funcionarioRepository, times(1)).save(any(Funcionario.class));
    }

    @Test
    void deveRemoverCaracteresNaoNumericosDoCpf() {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setCpf("123-456.789/00");
        dto.setNome("Carlos Lima");

        when(funcionarioRepository.existsByCpf("12345678900")).thenReturn(false);
        when(funcionarioRepository.save(any(Funcionario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Funcionario resultado = funcionarioService.salvar(dto);

        assertThat(resultado.getCpf()).isEqualTo("12345678900");
        verify(funcionarioRepository, times(1)).save(any(Funcionario.class));
    }
}