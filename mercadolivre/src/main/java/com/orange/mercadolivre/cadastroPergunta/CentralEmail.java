package com.orange.mercadolivre.cadastroPergunta;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public interface CentralEmail {

   void send(String body, String subject, String nameFrom, String from, String to);
}
