package com.orange.mercadolivre.cadastroUsuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Optional;
import java.util.stream.Stream;

public class EmailUnicoTest {


    @DisplayName("deveria lidar com email duplicado")
    @ParameterizedTest
    @MethodSource("geradorTeste1")
    void teste1(Optional<Usuario> possivelUsuario, boolean esperado) throws Exception{
      UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
      ProibeEmailDuplicadoUsuarioValidator validador = new ProibeEmailDuplicadoUsuarioValidator(usuarioRepository);
      Object target = new UsuarioRequest("email@email.com.br","senha");
      Errors errors = new BeanPropertyBindingResult(target, "teste");
      Mockito.when(usuarioRepository.findByLogin("email@email.com.br")).thenReturn(possivelUsuario);

      validador.validate(target, errors);

        Assertions.assertEquals(esperado, errors.hasFieldErrors("login"));
    }

    private static Stream<Arguments> geradorTeste1(){
        Optional<Usuario>usuario = Optional.of(new Usuario("email@email.com.br",
                new SenhaLimpa("senha")));
        return Stream.of(Arguments.of(usuario,true),
                Arguments.of(Optional.empty(), false));
    }
}
