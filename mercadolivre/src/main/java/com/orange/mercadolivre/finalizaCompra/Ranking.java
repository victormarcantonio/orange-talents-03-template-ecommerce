package com.orange.mercadolivre.finalizaCompra;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements ProcessaCompraSucesso {

    @Override
    public void processa(Compra compra) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idVendedor", compra.getDonoProduto().getId());
        restTemplate.postForEntity("http://localhost:8080/ranking",request, String.class);
    }
}
