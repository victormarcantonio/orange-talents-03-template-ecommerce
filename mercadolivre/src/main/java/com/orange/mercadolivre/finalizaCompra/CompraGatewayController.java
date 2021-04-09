package com.orange.mercadolivre.finalizaCompra;

import com.orange.mercadolivre.cadastroPergunta.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.BindException;
import java.util.Optional;

@RestController
public class CompraGatewayController {

    private CompraRepository compraRepository;
    private TransacaoRepository transacaoRepository;
    private Email email;
    private EventoNovaCompra evento;

    public CompraGatewayController(CompraRepository compraRepository, TransacaoRepository transacaoRepository, Email email, EventoNovaCompra evento) {
        this.compraRepository = compraRepository;
        this.transacaoRepository = transacaoRepository;
        this.email = email;
        this.evento = evento;
    }

    @PostMapping("/compras/paypal/{id}")
    public ResponseEntity<PaypalRequest> pagamentoPaypal(@PathVariable ("id") Long idCompra, @RequestBody @Valid PaypalRequest paypalRequest){
      processa(idCompra, paypalRequest);
      return ResponseEntity.ok(paypalRequest);
    }

    @PostMapping("/compras/pagseguro/{id}")
    public ResponseEntity<PagseguroRequest> pagamentoPagseguro(@PathVariable ("id") Long idCompra, @RequestBody @Valid PagseguroRequest pagseguroRequest) {
        processa(idCompra, pagseguroRequest);
        return ResponseEntity.ok(pagseguroRequest);
    }

    public String processa(@PathVariable ("id") Long idCompra, GatewayCriaTransacao gatewayCriaTransacao){
        Compra compra = compraRepository.getOne(idCompra);
        compra.novaTransacao(gatewayCriaTransacao);
        compraRepository.save(compra);
        if(compra.retornaSucesso()){
           evento.processa(compra);
        }
        return compra.toString();
    }
}
