package com.orange.mercadolivre.cadastroProduto;

import com.orange.mercadolivre.cadastroUsuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
