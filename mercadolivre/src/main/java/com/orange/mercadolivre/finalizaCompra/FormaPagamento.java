package com.orange.mercadolivre.finalizaCompra;

import java.util.UUID;

public enum FormaPagamento {
    PAYPAL{
        @Override
        String retornaUrl(UUID identificador) {
            return "paypal.com?buyerId="+identificador+"&redirectUrl={urlRetornoAppPosPagamento}";
        }
    }, PAGSEGURO{
        @Override
        String retornaUrl(UUID identificador) {
            return "pagseguro.com?returnId="+identificador+"&redirectUrl={urlRetornoAppPosPagamento}";
        }
    };
    abstract String retornaUrl(UUID identificador);
}
