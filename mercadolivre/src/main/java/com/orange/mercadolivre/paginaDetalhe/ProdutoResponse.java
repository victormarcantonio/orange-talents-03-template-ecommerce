package com.orange.mercadolivre.paginaDetalhe;

import com.orange.mercadolivre.cadastroProduto.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class ProdutoResponse {
    private List<ImagemResponse> links = new ArrayList<>();
    private String nome;
    private BigDecimal preco;
    private List<CaracteristicaResponse> caracteristicas = new ArrayList<>();
    private String descricao;
    private List<OpiniaoResponse>opinioes = new ArrayList<>();
    private List<PerguntaResponse>perguntas = new ArrayList<>();
    private long notas;
    private OptionalDouble mediaNotas;


    public ProdutoResponse(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        this.caracteristicas.addAll(produto.getCaracteristicas().stream().map(CaracteristicaResponse::new).collect(Collectors.toList()));
        this.opinioes.addAll(produto.getOpinioes().stream().map(OpiniaoResponse::new).collect(Collectors.toList()));
        this.perguntas.addAll(produto.getPerguntas().stream().map(PerguntaResponse::new).collect(Collectors.toList()));
        this.links.addAll(produto.getImagens().stream().map(ImagemResponse::new).collect(Collectors.toList()));
        this.notas = produto.TotalNotas();
        this.mediaNotas = produto.calculaMediaNotas();
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public List<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public List<PerguntaResponse> getPerguntas() {
        return perguntas;
    }

    public List<ImagemResponse> getLinks() {
        return links;
    }

    public long getNotas() {
        return notas;
    }

    public OptionalDouble getMediaNotas() {
        return mediaNotas;
    }
}
