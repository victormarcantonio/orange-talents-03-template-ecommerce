package com.orange.mercadolivre.cadastroOpiniao;

import com.orange.mercadolivre.cadastroProduto.Produto;
import com.orange.mercadolivre.cadastroUsuario.Usuario;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class OpiniaoRequest {

    @Min(1)
    @Max(5)
    private int nota;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;


    public OpiniaoRequest(@Size(min = 1, max = 5) int nota, @NotBlank String titulo, @NotBlank String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public Opiniao converter(Usuario usuario, Produto produto){
        return new Opiniao (nota,titulo,descricao,usuario,produto);
    }
}
