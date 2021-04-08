package com.orange.mercadolivre.finalizaCompra;

public interface GatewayCriaTransacao {

    Transacao criaTransacao(Compra compra);
}
