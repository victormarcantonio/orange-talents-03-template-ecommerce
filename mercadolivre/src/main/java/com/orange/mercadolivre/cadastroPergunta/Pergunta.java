package com.orange.mercadolivre.cadastroPergunta;

import com.orange.mercadolivre.cadastroProduto.Produto;
import com.orange.mercadolivre.cadastroUsuario.Usuario;
import org.springframework.context.ApplicationEvent;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Entity
public class Pergunta extends ApplicationEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    private Instant instante = Instant.now();
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Produto produto;

    public Pergunta(Object source) {
        super(source);
    }

    public Pergunta(Object source, @NotBlank String titulo, Usuario usuario, Produto produto) {
        super(source);
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
