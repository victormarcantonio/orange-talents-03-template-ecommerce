package com.orange.mercadolivre.cadastroPergunta;

import com.orange.mercadolivre.cadastroProduto.Produto;
import com.orange.mercadolivre.cadastroUsuario.Usuario;

import javax.validation.constraints.NotBlank;

public class PerguntaRequest {
    @NotBlank
    private String titulo;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public Pergunta converter(Usuario usuario, Produto produto){
        return new Pergunta(titulo, usuario, produto);
    }
}
