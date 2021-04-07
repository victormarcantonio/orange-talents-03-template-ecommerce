package com.orange.mercadolivre.finalizaCompra;

import com.orange.mercadolivre.cadastroPergunta.Email;
import com.orange.mercadolivre.cadastroProduto.Produto;
import com.orange.mercadolivre.cadastroProduto.ProdutoRepository;
import com.orange.mercadolivre.cadastroUsuario.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private ProdutoRepository produtoRepository;
    private CompraRepository compraRepository;
    private VerificaEstoqueValidator verificaEstoqueValidator;
    private Email email;

    public CompraController(ProdutoRepository produtoRepository, CompraRepository compraRepository, VerificaEstoqueValidator verificaEstoqueValidator, Email email) {
        this.produtoRepository = produtoRepository;
        this.compraRepository = compraRepository;
        this.verificaEstoqueValidator = verificaEstoqueValidator;
        this.email = email;
    }

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(verificaEstoqueValidator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CompraRequest request, @AuthenticationPrincipal Usuario usuario) {
        Compra compra = request.converter(produtoRepository, usuario);
        compraRepository.save(compra);
        email.informaVendedorCompra(compra);
        String url = compra.retornaUrl();
        return new ResponseEntity<>(url, HttpStatus.FOUND);
    }
}
