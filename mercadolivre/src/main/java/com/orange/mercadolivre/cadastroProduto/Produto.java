package com.orange.mercadolivre.cadastroProduto;

import com.orange.mercadolivre.cadastroCategoria.Categoria;
import com.orange.mercadolivre.cadastroUsuario.Usuario;
import com.orange.mercadolivre.validator.ExistsById;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    @Positive
    private BigDecimal valor;
    @NotNull
    private int quantidadeDisponivel;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    @Size(min = 3)
    private List<Caracteristica> caracteristicas = new ArrayList<>();
    @NotBlank
    @Column(columnDefinition = "text")
    private String descricao;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Usuario usuario;
    private LocalDateTime instante = LocalDateTime.now();

    public Produto() {

    }

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull int quantidadeDisponivel, List<CaracteristicaRequest> caracteristicas, @NotBlank String descricao, Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.converter(this)).collect(Collectors.toSet()));
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
    }


    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", usuario=" + usuario +
                '}';
    }
}
