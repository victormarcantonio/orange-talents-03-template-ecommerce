package com.orange.mercadolivre.cadastroPergunta;

import org.springframework.stereotype.Component;

@Component
public class EnviaEmail {

    public void envia(String email){
        System.out.println(email);
    }
}
