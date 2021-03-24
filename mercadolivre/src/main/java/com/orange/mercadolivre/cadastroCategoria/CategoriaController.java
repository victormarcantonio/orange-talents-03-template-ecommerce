package com.orange.mercadolivre.cadastroCategoria;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public String cadastrar (@RequestBody @Valid CategoriaRequest request) {
        Categoria categoria = request.converter(categoriaRepository);
        categoriaRepository.save(categoria);
        return categoria.toString();
    }
}
