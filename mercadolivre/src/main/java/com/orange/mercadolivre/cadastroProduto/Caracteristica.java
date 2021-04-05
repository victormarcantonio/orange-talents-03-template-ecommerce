package com.orange.mercadolivre.cadastroProduto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @ManyToOne
    private Produto produto;

    public Caracteristica(@NotBlank String nome, @NotBlank String descricao, Produto produto) {
        this.nome = nome;
        this.descricao = descricao;
        this.produto = produto;
    }

}
