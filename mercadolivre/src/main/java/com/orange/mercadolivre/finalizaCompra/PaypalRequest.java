package com.orange.mercadolivre.finalizaCompra;

import com.orange.mercadolivre.validator.CampoUnico;
import com.orange.mercadolivre.validator.ExistsById;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PaypalRequest implements GatewayCriaTransacao{


    @NotNull
    private Long idPagamento;
    @NotNull
    @Max(1)
    @Min(0)
    private int status;

    public PaypalRequest(Long idPagamento, int status) {
        this.idPagamento = idPagamento;
        this.status = status;
    }


    public Long getIdPagamento() {
        return idPagamento;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public Transacao criaTransacao(Compra compra) {
        StatusTransacao statusTransacao = status == 0 ? StatusTransacao.SUCESSO : StatusTransacao.FALHA;
        return new Transacao(compra, idPagamento, statusTransacao);
    }
}
