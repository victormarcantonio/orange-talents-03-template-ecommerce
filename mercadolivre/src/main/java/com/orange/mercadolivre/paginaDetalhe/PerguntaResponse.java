package com.orange.mercadolivre.paginaDetalhe;

import com.orange.mercadolivre.cadastroPergunta.Pergunta;

public class PerguntaResponse {

    private String titulo;
    private String nomeUsuario;

    public PerguntaResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.nomeUsuario = pergunta.getUsuario().getUsername();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
}
