package com.orange.mercadolivre.finalizaCompra;

import com.orange.mercadolivre.cadastroProduto.Produto;
import com.orange.mercadolivre.cadastroProduto.ProdutoRepository;
import com.orange.mercadolivre.cadastroUsuario.Usuario;
import com.orange.mercadolivre.validator.ExistsById;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {
    @NotNull
    @Positive
    private int quantidade;
    @ExistsById(domainClass = Produto.class, fieldName = "id")
    @NotNull
    private Long idProduto;
    @NotNull
    private FormaPagamento formaPagamento;

    public CompraRequest(@NotNull @Positive int quantidade, Long idProduto, FormaPagamento formaPagamento) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.formaPagamento = formaPagamento;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public Compra converter (ProdutoRepository produtoRepository, Usuario usuario){
        Produto produto  = produtoRepository.getOne(idProduto);
        return new Compra(quantidade, produto, formaPagamento, usuario, produto.getValor());
    }
}
