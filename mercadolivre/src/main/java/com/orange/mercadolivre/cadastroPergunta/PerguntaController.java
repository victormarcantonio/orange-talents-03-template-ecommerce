package com.orange.mercadolivre.cadastroPergunta;

import com.orange.mercadolivre.cadastroProduto.Produto;
import com.orange.mercadolivre.cadastroProduto.ProdutoRepository;
import com.orange.mercadolivre.cadastroUsuario.Usuario;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

    private ProdutoRepository produtoRepository;
    private PerguntaRepository perguntaRepository;
    private ApplicationContext context;

    public PerguntaController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository, ApplicationContext context) {
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.context = context;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> cadastrar(@PathVariable ("id") Long idProduto, @RequestBody @Valid PerguntaRequest request, @AuthenticationPrincipal Usuario usuario){
        Optional<Produto> possivelProduto = produtoRepository.findById(idProduto);
        return possivelProduto.map(produto -> {
            Pergunta pergunta = request.converter(this,usuario, possivelProduto.get());
            perguntaRepository.save(pergunta);
            context.publishEvent(pergunta);
            return ResponseEntity.ok(context);
        }).orElse(ResponseEntity.notFound().build());
    }
}
