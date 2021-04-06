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
    private Email email;

    public PerguntaController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository, Email email) {
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.email = email;
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> cadastrar(@PathVariable ("id") Long idProduto, @RequestBody @Valid PerguntaRequest request, @AuthenticationPrincipal Usuario usuario){
        Optional<Produto> possivelProduto = produtoRepository.findById(idProduto);
        return possivelProduto.map(produto -> {
            Pergunta pergunta = request.converter(usuario, possivelProduto.get());
            perguntaRepository.save(pergunta);
           email.novaPergunta(pergunta);
            return ResponseEntity.ok(pergunta.toString());
        }).orElse(ResponseEntity.notFound().build());
    }
}
