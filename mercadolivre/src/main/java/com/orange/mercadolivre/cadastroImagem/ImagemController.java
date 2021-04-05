package com.orange.mercadolivre.cadastroImagem;

import com.orange.mercadolivre.cadastroProduto.Produto;
import com.orange.mercadolivre.cadastroProduto.ProdutoRepository;
import com.orange.mercadolivre.cadastroUsuario.Usuario;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ImagemController {

    private ProdutoRepository produtoRepository;
    private ImagemRepository imagemRepository;

    public ImagemController(ProdutoRepository produtoRepository, ImagemRepository imagemRepository) {
        this.produtoRepository = produtoRepository;
        this.imagemRepository = imagemRepository;
    }

    @PostMapping(value="/produtos/{id}/imagens")
    public ResponseEntity<?> cadastrar(@PathVariable("id") Long idProduto, @RequestBody @Valid ImagemRequest request, @AuthenticationPrincipal Usuario usuario){

           Optional<Produto> possivelProduto = produtoRepository.findById(idProduto);
           if(possivelProduto.get().getUsuario().getId().equals(usuario.getId())) {
               return possivelProduto.map(produto -> {
                   Imagem imagem = produto.novaImagem(request.getLink());
                   imagemRepository.save(imagem);
                   return ResponseEntity.ok(imagem.toString());
               }).orElse(ResponseEntity.notFound().build());
           }
           return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
