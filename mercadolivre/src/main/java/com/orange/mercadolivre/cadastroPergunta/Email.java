package com.orange.mercadolivre.cadastroPergunta;

import com.orange.mercadolivre.finalizaCompra.Compra;
import org.springframework.stereotype.Service;

@Service
public class Email {

    private CentralEmail centralEmail;

    public Email(CentralEmail centralEmail) {
        this.centralEmail = centralEmail;
    }

    public void novaPergunta(Pergunta pergunta){
        centralEmail.send("<html>...</html>", "Nova pergunta", pergunta.getUsuario().getUsername(),"novapergunta@email.com", pergunta.getDonoProduto().getUsername());
    }

    public void informaVendedorCompra(Compra compra){
        centralEmail.send("<html>...</html>", "Nova Compra", compra.getComprador().getUsername(),"novacompra@email.com", compra.getDonoProduto().getUsername());
    }

    public void informaClienteCompraSucesso(Compra compra){
        centralEmail.send("<html>Compra efetuada com sucesso: Seguem dados "+compra.toString()+"</html>", "Nova Compra", compra.getDonoProduto().getUsername(),"novacompra@email.com", compra.getComprador().getUsername());
    }
}
