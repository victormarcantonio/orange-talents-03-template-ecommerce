package com.orange.mercadolivre.cadastroProduto;

import com.orange.mercadolivre.cadastroCategoria.CategoriaRepository;
import com.orange.mercadolivre.cadastroUsuario.Usuario;
import com.orange.mercadolivre.cadastroUsuario.UsuarioRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private CategoriaRepository categoriaRepository;
    private ProdutoRepository produtoRepository;


    public ProdutoController(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public String cadastrar(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal Usuario usuario) {
        Produto produto = request.converter(categoriaRepository, usuario);
        produtoRepository.save(produto);
        return produto.toString();
    }
}
