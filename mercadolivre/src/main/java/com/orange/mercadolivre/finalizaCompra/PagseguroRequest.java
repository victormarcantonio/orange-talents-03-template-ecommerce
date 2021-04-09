package com.orange.mercadolivre.finalizaCompra;

import com.orange.mercadolivre.validator.CampoUnico;
import com.orange.mercadolivre.validator.ExistsById;

import javax.validation.constraints.NotNull;

public class PagseguroRequest implements GatewayCriaTransacao{

    @NotNull
    private Long idPagamento;
    @NotNull
    private StatusPagseguro statusPagseguro;

    public PagseguroRequest(Long idPagamento, StatusPagseguro statusPagseguro) {
        this.idPagamento = idPagamento;
        this.statusPagseguro = statusPagseguro;
    }


    public Long getIdPagamento() {
        return idPagamento;
    }

    public StatusPagseguro getStatusPagseguro() {
        return statusPagseguro;
    }

    @Override
    public Transacao criaTransacao(Compra compra){

        return new Transacao(compra, idPagamento,statusPagseguro.retornaStatus());
    }
}
