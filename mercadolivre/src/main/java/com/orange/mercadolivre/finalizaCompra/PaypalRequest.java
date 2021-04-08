package com.orange.mercadolivre.finalizaCompra;

import com.orange.mercadolivre.validator.ExistsById;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PaypalRequest implements GatewayCriaTransacao{

    @NotNull
    @ExistsById(domainClass = Compra.class, fieldName = "id")
    private Long idCompra;
    @NotNull
    private Long idPagamento;
    @NotNull
    @Max(1)
    @Min(0)
    private int status;

    public PaypalRequest(Long idCompra, Long idPagamento, int status) {
        this.idCompra = idCompra;
        this.idPagamento = idPagamento;
        this.status = status;
    }

    public Long getIdCompra() {
        return idCompra;
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
