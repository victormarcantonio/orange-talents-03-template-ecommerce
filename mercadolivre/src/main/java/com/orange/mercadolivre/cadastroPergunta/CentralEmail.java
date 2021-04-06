package com.orange.mercadolivre.cadastroPergunta;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CentralEmail implements ApplicationListener<Pergunta> {

    private EnviaEmail enviaEmail;

    public CentralEmail(EnviaEmail enviaEmail) {
        this.enviaEmail = enviaEmail;
    }

    @Override
    public void onApplicationEvent(Pergunta event) {
       String email = "Enviando email com as seguintes informações " + event.toString();
       enviaEmail.envia(email);
    }
}
