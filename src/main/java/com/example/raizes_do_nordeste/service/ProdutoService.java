package com.example.raizes_do_nordeste.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.model.Produto;
import com.example.raizes_do_nordeste.repository.ProdutoRepository;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto>listarProdutos(){
        return produtoRepository.findAll();
    }

    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

}
