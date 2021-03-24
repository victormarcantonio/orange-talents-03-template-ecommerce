package com.orange.mercadolivre.cadastroCategoria;

import com.orange.mercadolivre.validator.CampoUnico;
import com.orange.mercadolivre.validator.ExistsById;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {
    @NotBlank
    @CampoUnico(domainClass = Categoria.class, fieldName = "nome")
    private String nome;
    private Long idCategoriaMae;

    public CategoriaRequest(@NotBlank String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria converter(CategoriaRepository categoriaRepository){
        Categoria categoria = new Categoria(nome);
        if(idCategoriaMae!=null){
            Categoria categoriaMae = categoriaRepository.getOne(idCategoriaMae);
            categoria.setCategoriaMae(categoriaMae);
        }
        return categoria;
    }
}
