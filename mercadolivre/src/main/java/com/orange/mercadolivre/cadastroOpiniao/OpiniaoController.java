package com.orange.mercadolivre.cadastroOpiniao;

import com.orange.mercadolivre.cadastroProduto.Produto;
import com.orange.mercadolivre.cadastroProduto.ProdutoRepository;
import com.orange.mercadolivre.cadastroUsuario.Usuario;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/opinioes")
public class OpiniaoController {

    private OpiniaoRepository opiniaoRepository;
    private ProdutoRepository produtoRepository;

    public OpiniaoController(OpiniaoRepository opiniaoRepository, ProdutoRepository produtoRepository) {
        this.opiniaoRepository = opiniaoRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping(value = "/{id}")
    public ResponseEntity<?> cadastrar(@PathVariable ("id") Long idProduto, @RequestBody @Valid OpiniaoRequest request, @AuthenticationPrincipal Usuario usuario) {

        Optional<Produto> possivelProduto = produtoRepository.findById(idProduto);
       return possivelProduto.map(produto -> {
            Opiniao opiniao = request.converter(usuario,possivelProduto.get());
            opiniaoRepository.save(opiniao);
            return ResponseEntity.ok(opiniao.toString());
        }).orElse(ResponseEntity.notFound().build());
    }
}
