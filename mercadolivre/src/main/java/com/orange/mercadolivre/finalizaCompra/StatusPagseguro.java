package com.orange.mercadolivre.finalizaCompra;

public enum StatusPagseguro {
    SUCESSO, ERRO;

        public StatusTransacao retornaStatus() {
            if (this.equals(SUCESSO)) {
                return StatusTransacao.SUCESSO;
            }
            return StatusTransacao.FALHA;
        }

}
