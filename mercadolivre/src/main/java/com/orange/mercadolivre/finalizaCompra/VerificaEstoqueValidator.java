package com.orange.mercadolivre.finalizaCompra;

import com.orange.mercadolivre.cadastroProduto.Produto;
import com.orange.mercadolivre.cadastroProduto.ProdutoRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class VerificaEstoqueValidator implements Validator {

    private ProdutoRepository produtoRepository;

    public VerificaEstoqueValidator(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
      if(errors.hasErrors()){
          return;
      }

      CompraRequest request = (CompraRequest) target;
      Produto produto = produtoRepository.getOne(request.getIdProduto());
      if(!produto.abateEstoque(request.getQuantidade())){
          errors.reject(null, "Produto indisponível no estoque");
      }
    }
}
