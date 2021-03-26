package com.orange.mercadolivre.cadastroProduto;

import com.orange.mercadolivre.cadastroCategoria.Categoria;
import com.orange.mercadolivre.cadastroCategoria.CategoriaRepository;
import com.orange.mercadolivre.cadastroUsuario.Usuario;
import com.orange.mercadolivre.validator.ExistsById;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ProdutoRequest {

    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    @Positive
    private int quantidadeDisponivel;
    @Size(min = 3)
    private List<CaracteristicaRequest> caracteristicas;
    @NotBlank
    @Size(max = 1000)
    private String descricao;
    @ExistsById(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;


    public ProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @Positive int quantidadeDisponivel, @Size(min = 3) List<CaracteristicaRequest> caracteristicas, @NotBlank @Size(max = 1000) String descricao, Long idCategoria) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.idCategoria = idCategoria;
    }

    public Produto converter (CategoriaRepository categoriaRepository, Usuario usuario) {
       Categoria categoria = categoriaRepository.getOne(idCategoria);
       List<Caracteristica> listaCaracteristicas = caracteristicas.stream().map(CaracteristicaRequest::converter).collect(Collectors.toList());
       return new Produto(nome,valor,quantidadeDisponivel,listaCaracteristicas,descricao,categoria,usuario);
    }
}
