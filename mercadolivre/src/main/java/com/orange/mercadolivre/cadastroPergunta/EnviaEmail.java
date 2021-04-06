package com.orange.mercadolivre.cadastroPergunta;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;

@Component
@Primary
public class EnviaEmail implements CentralEmail {


    @Override
    public void send(String body, String subject, String nameFrom, String from, String to) {
        System.out.println(body);
        System.out.println(subject);
        System.out.println(nameFrom);
        System.out.println(from);
        System.out.println(to);
    }
}
