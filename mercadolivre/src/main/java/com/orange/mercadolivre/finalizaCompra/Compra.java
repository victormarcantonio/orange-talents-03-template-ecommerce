package com.orange.mercadolivre.finalizaCompra;

import com.orange.mercadolivre.cadastroProduto.Produto;
import com.orange.mercadolivre.cadastroUsuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.UUID;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive
    private int quantidade;
    @ManyToOne
    private Produto produto;
    private FormaPagamento formaPagamento;
    private Status status = Status.INICIADA;
    private UUID identificador = UUID.randomUUID();
    @ManyToOne
    private Usuario comprador;
    private BigDecimal valorProduto;

    public Compra(@Positive int quantidade, Produto produto, FormaPagamento formaPagamento, Usuario comprador, BigDecimal valorProduto) {
        this.quantidade = quantidade;
        this.produto = produto;
        this.formaPagamento = formaPagamento;
        this.comprador = comprador;
        this.valorProduto = produto.getValor();
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Usuario getDonoProduto(){
        return produto.getUsuario();
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public String retornaUrl(){
       return this.formaPagamento.retornaUrl(this.identificador);
    }

}
