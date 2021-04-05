package com.orange.mercadolivre.cadastroImagem;

import com.orange.mercadolivre.cadastroProduto.Produto;
import org.hibernate.validator.constraints.URL;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class Imagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @URL
    private String link;
    @ManyToOne
    private Produto produto;

    public Imagem(String link, Produto produto) {
        this.link = link;
        this.produto = produto;
    }
    @Deprecated
    public Imagem() {
    }

    @Override
    public String toString() {
        return "Imagem{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Imagem imagem = (Imagem) o;

        if (id != null ? !id.equals(imagem.id) : imagem.id != null) return false;
        if (link != null ? !link.equals(imagem.link) : imagem.link != null) return false;
        return produto != null ? produto.equals(imagem.produto) : imagem.produto == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (produto != null ? produto.hashCode() : 0);
        return result;
    }
}
