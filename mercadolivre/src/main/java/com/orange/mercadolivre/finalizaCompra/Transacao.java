package com.orange.mercadolivre.finalizaCompra;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import java.time.Instant;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Compra compra;
    private Long idPagamento;
    private StatusTransacao status;
    @PastOrPresent
    private Instant instante = Instant.now();

    @Deprecated
    public Transacao() {
    }

    public Transacao(Compra compra, Long idPagamento, StatusTransacao status) {
        this.compra = compra;
        this.idPagamento = idPagamento;
        this.status = status;
    }

    public boolean retornaSucesso(){
        return this.status.equals(StatusTransacao.SUCESSO);
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transacao transacao = (Transacao) o;

        if (id != null ? !id.equals(transacao.id) : transacao.id != null) return false;
        if (compra != null ? !compra.equals(transacao.compra) : transacao.compra != null) return false;
        if (idPagamento != null ? !idPagamento.equals(transacao.idPagamento) : transacao.idPagamento != null)
            return false;
        if (status != transacao.status) return false;
        return instante != null ? instante.equals(transacao.instante) : transacao.instante == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (compra != null ? compra.hashCode() : 0);
        result = 31 * result + (idPagamento != null ? idPagamento.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (instante != null ? instante.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id=" + id +
                ", status=" + status +
                ", instante=" + instante +
                '}';
    }
}
