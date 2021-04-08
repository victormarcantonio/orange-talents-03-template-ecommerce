package com.orange.mercadolivre.finalizaCompra;

import com.orange.mercadolivre.cadastroProduto.Produto;
import com.orange.mercadolivre.cadastroUsuario.Usuario;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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
    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    public Compra() {
    }

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

    public void novaTransacao(GatewayCriaTransacao gatewayCriaTransacao){
        Transacao transacao = gatewayCriaTransacao.criaTransacao(this);
        Assert.isTrue(!this.transacoes.contains(transacao), "Já existe uma transação processada" + transacao);
        Set<Transacao>transacoesSucesso = this.transacoes.stream().filter(Transacao::retornaSucesso).collect(Collectors.toSet());
        Assert.isTrue(transacoesSucesso.isEmpty(), "Compra já concluída");
        this.transacoes.add(transacao);
    }

}
