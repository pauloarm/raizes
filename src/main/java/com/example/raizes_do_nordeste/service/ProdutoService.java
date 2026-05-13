package com.example.raizes_do_nordeste.service;

import com.example.raizes_do_nordeste.repository.PedidoRepository;

import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.dto.ProdutoDTO;
import com.example.raizes_do_nordeste.model.Produto;
import com.example.raizes_do_nordeste.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository, PedidoRepository pedidoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public ProdutoDTO adicionarProduto(ProdutoDTO produtoDTO){
        Produto produto = new Produto();
        produto.setNome(produtoDTO.nome());
        produto.setDescricao(produtoDTO.descricao());
        produto.setPrecoBase(produtoDTO.preco());
        produto.setDisponivel(produtoDTO.disponivel());
        
        Produto produtoSalvo = produtoRepository.save(produto);

        return new ProdutoDTO(produtoSalvo.getId(), produtoSalvo.getNome(), produtoSalvo.getDescricao(), produtoSalvo.getPrecoBase(), produtoSalvo.getDisponivel());
    }

}
