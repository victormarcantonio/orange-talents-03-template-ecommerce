package com.orange.mercadolivre.cadastroPergunta;

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
}
