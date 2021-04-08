package com.orange.mercadolivre.finalizaCompra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CompraGatewayController {

    private CompraRepository compraRepository;

    public CompraGatewayController(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @PostMapping("/compras/paypal")
    public ResponseEntity<PaypalRequest> pagamentoPaypal(@RequestBody @Valid PaypalRequest paypalRequest){
        Compra compra = compraRepository.getOne(paypalRequest.getIdCompra());
        compra.novaTransacao(paypalRequest);
        compraRepository.save(compra);
        return ResponseEntity.ok(paypalRequest);
    }

    @PostMapping("/compras/pagseguro")
    public ResponseEntity<PagseguroRequest> pagamentoPagseguro(@RequestBody @Valid PagseguroRequest pagseguroRequest) {
        Compra compra = compraRepository.getOne(pagseguroRequest.getIdCompra());
        compra.novaTransacao(pagseguroRequest);
        compraRepository.save(compra);
        return ResponseEntity.ok(pagseguroRequest);
    }
}
