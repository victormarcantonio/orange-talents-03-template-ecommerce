package com.orange.mercadolivre.finalizaCompra;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class EventoNovaCompra {

    Set<ProcessaCompraSucesso> eventosSucesso;

    public EventoNovaCompra(Set<ProcessaCompraSucesso> eventosSucesso) {
        this.eventosSucesso = eventosSucesso;
    }

    public void processa(Compra compra){
        if(compra.retornaSucesso()){
           eventosSucesso.forEach(evento -> evento.processa(compra));
        }else {
            // Falha
        }
    }
}
