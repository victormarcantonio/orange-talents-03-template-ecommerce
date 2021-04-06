package com.orange.mercadolivre.cadastroOpiniao;

import com.orange.mercadolivre.cadastroProduto.Produto;
import com.orange.mercadolivre.cadastroUsuario.Usuario;
import com.orange.mercadolivre.validator.ExistsById;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue
    private Long id;
    @Min(1)
    @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Produto produto;

    public Opiniao(@Min(1) @Max(5) int nota, @NotBlank String titulo, @NotBlank String descricao, Usuario usuario, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Opiniao{" +
                "id=" + id +
                ", nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}