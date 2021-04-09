package com.orange.mercadolivre.finalizaCompra;

import com.orange.mercadolivre.cadastroUsuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class IntegraOutrosSistemasController {


    @PostMapping("/fiscal")
    public String comunicaFiscal(@RequestBody NotaFiscalRequest notaFiscalRequest){
        System.out.println("Nota fiscal criada" + notaFiscalRequest.toString());
        return ("Fiscal conunicado");
    }

    @PostMapping("/ranking")
    public String comunicaRanking(@RequestBody RankingRequest rankingRequest){
        System.out.println("Ranking comunicado" +rankingRequest.toString());
        return ("Ranking de vendedores conunicado");
    }
}
