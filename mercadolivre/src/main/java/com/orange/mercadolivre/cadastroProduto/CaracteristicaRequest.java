package com.orange.mercadolivre.cadastroProduto;

import com.orange.mercadolivre.cadastroCategoria.Categoria;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

public class CaracteristicaRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;

    public CaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Caracteristica converter(){
        return new Caracteristica(nome, descricao);
    }

}
