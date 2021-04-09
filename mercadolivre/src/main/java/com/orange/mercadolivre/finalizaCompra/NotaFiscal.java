package com.orange.mercadolivre.finalizaCompra;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements  ProcessaCompraSucesso{

    @Override
    public void processa(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getComprador().getId());
        restTemplate.postForEntity("http://localhost:8080/fiscal",request, String.class);
    }
}
