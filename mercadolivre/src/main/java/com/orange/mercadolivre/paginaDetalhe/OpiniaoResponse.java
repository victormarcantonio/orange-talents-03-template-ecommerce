package com.orange.mercadolivre.paginaDetalhe;

import com.orange.mercadolivre.cadastroOpiniao.Opiniao;

public class OpiniaoResponse {
    private int nota;
    private String titulo;
    private String descricao;
    private String nomeUsuario;

    public OpiniaoResponse(Opiniao opiniao) {
        this.nota = opiniao.getNota();
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.nomeUsuario = opiniao.getUsuario().getUsername();
    }

    public int getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
}
