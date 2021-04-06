package com.orange.mercadolivre.paginaDetalhe;

import com.orange.mercadolivre.cadastroImagem.Imagem;
import com.orange.mercadolivre.cadastroProduto.Produto;

import java.util.Set;

public class ImagemResponse {

    private String link;

    public ImagemResponse(Imagem imagem) {
        this.link = imagem.getLink();
    }

    public String getLink() {
        return link;
    }
}
