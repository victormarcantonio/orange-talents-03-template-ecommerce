package com.orange.mercadolivre.paginaDetalhe;

import com.orange.mercadolivre.cadastroProduto.Caracteristica;

public class CaracteristicaResponse {

   private String nome;
   private String descricao;

    public CaracteristicaResponse(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
