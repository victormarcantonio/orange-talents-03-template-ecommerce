package com.orange.mercadolivre.cadastroProduto;

import com.orange.mercadolivre.cadastroCategoria.CategoriaRepository;
import com.orange.mercadolivre.cadastroImagem.Imagem;
import com.orange.mercadolivre.cadastroImagem.ImagemRequest;
import com.orange.mercadolivre.cadastroImagem.Uploader;
import com.orange.mercadolivre.cadastroImagem.UploaderFake;
import com.orange.mercadolivre.cadastroUsuario.Usuario;
import com.orange.mercadolivre.cadastroUsuario.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private CategoriaRepository categoriaRepository;
    private ProdutoRepository produtoRepository;
    private Uploader uploaderFake;

    @InitBinder(value = "produtoRequest")
    public void init(WebDataBinder binder){
      binder.addValidators(new ProibeCaracteristicaComNomeIgualValidator());
    }

    public ProdutoController(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository, Uploader uploaderFake) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.uploaderFake = uploaderFake;
    }

    @PostMapping
    public String cadastrar(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = request.converter(categoriaRepository, usuario);
        produtoRepository.save(produto);
        return produto.toString();
    }

    @PostMapping(value="{id}/imagens")
    public ResponseEntity<?> cadastrarImagem(@PathVariable("id") Long idProduto,@Valid ImagemRequest request, @AuthenticationPrincipal Usuario usuario){


        Produto produto = produtoRepository.getOne(idProduto);


        if(!produto.pertenceAoUsuario(usuario)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Set<String> links = uploaderFake.envia(request.getImagens());
        produto.associaImagens(links);
        produtoRepository.save(produto);
        return ResponseEntity.ok(produto.toString());
    }
}
