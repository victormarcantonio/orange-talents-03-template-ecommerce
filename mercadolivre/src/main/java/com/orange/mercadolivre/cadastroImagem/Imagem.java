package com.orange.mercadolivre.cadastroImagem;

import com.orange.mercadolivre.cadastroProduto.Produto;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    @ManyToOne
    private Produto produto;

    public Imagem(String link, Produto produto) {
        this.link = link;
        this.produto = produto;
    }

    public Imagem() {
    }

    @Override
    public String toString() {
        return "Imagem{" +
                "id=" + id +
                ", produto=" + produto +
                '}';
    }
}
