package com.orange.mercadolivre.cadastroCategoria;

import com.orange.mercadolivre.validator.CampoUnico;

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
        if(idCategoriaMae==null){
            return new Categoria(nome);
        }
        Categoria categoriaMae = categoriaRepository.getOne(idCategoriaMae);
        return new Categoria(nome, categoriaMae);
    }
}
