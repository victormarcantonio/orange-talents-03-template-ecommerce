package com.orange.mercadolivre.cadastroProduto;

import com.orange.mercadolivre.cadastroUsuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Transactional
    @Modifying
    @Query("update Produto p set p.quantidadeDisponivel = :quantidadeAtualizada where p.id = :idProduto")
    void atualizaQuantidadeEstoque(int quantidadeAtualizada, Long idProduto);
}
