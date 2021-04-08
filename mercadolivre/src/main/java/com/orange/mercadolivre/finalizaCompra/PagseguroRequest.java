package com.orange.mercadolivre.finalizaCompra;

import com.orange.mercadolivre.validator.ExistsById;

import javax.validation.constraints.NotNull;

public class PagseguroRequest implements GatewayCriaTransacao{

    @NotNull
    @ExistsById(domainClass = Compra.class, fieldName = "id")
    private Long idCompra;
    @NotNull
    private Long idPagamento;
    @NotNull
    private StatusPagseguro statusPagseguro;

    public PagseguroRequest(Long idCompra, Long idPagamento, StatusPagseguro statusPagseguro) {
        this.idCompra = idCompra;
        this.idPagamento = idPagamento;
        this.statusPagseguro = statusPagseguro;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public StatusPagseguro getStatusPagseguro() {
        return statusPagseguro;
    }

    @Override
    public Transacao criaTransacao(Compra compra){

        return new Transacao(compra, idPagamento,statusPagseguro.retornaStatus());
    }
}
