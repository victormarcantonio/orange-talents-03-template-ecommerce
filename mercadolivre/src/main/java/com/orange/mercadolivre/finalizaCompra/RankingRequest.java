package com.orange.mercadolivre.finalizaCompra;

public class RankingRequest {

    private Long idCompra;
    private Long idVendedor;

    public RankingRequest(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    @Override
    public String toString() {
        return "RankingRequest{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }
}
