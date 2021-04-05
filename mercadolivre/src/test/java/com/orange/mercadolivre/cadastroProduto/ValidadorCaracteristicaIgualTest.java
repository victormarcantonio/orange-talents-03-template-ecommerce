package com.orange.mercadolivre.cadastroProduto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidadorCaracteristicaIgualTest {

    @Test
    @DisplayName("rejeita o cadastro caso tenha características iguais")
    void teste01(){
        ProibeCaracteristicaComNomeIgualValidator validador = new ProibeCaracteristicaComNomeIgualValidator();
        Produto produto = new Produto("PS5", 4000.0,3,List<Object> objetos)
    }
}
