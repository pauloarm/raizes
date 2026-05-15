package com.example.raizes_do_nordeste.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.dto.EstoqueRequestDTO;
import com.example.raizes_do_nordeste.dto.EstoqueResponseDTO;
import com.example.raizes_do_nordeste.model.Estoque;
import com.example.raizes_do_nordeste.model.Produto;
import com.example.raizes_do_nordeste.model.Unidade;
import com.example.raizes_do_nordeste.repository.EstoqueRepository;
import com.example.raizes_do_nordeste.repository.ProdutoRepository;
import com.example.raizes_do_nordeste.repository.UnidadeRepository;

@Service
public class EstoqueService {
    private final UnidadeRepository unidadeRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository, ProdutoRepository produtoRepository, UnidadeRepository unidadeRepository) {
        this.estoqueRepository = estoqueRepository;
        this.produtoRepository = produtoRepository; 
        this.unidadeRepository = unidadeRepository;
    }
    public EstoqueResponseDTO criarEstoque(EstoqueRequestDTO dto){
        Produto produto = produtoRepository.findById(dto.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Unidade unidade = unidadeRepository.findById(dto.unidadeId())
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));

        Estoque estoque = new Estoque();
        estoque.setQuantidade(dto.quantidade());
        estoque.setProduto(produto);
        estoque.setUnidade(unidade);

        Estoque estoqueSalvo = estoqueRepository.save(estoque);

        return new EstoqueResponseDTO(
            estoqueSalvo.getId(),
            estoqueSalvo.getProduto().getNome(),
            estoqueSalvo.getUnidade().getNome(),
            estoqueSalvo.getQuantidade()
        );
    }

    public List<EstoqueResponseDTO> listarTodoEstoque(){
        return estoqueRepository.findAll().stream()
                .map(estoque -> new EstoqueResponseDTO(estoque.getId(),
                                                        estoque.getProduto().getNome(),
                                                        estoque.getUnidade().getNome(),
                                                        estoque.getQuantidade()))
                .collect((Collectors.toList()));
    }

}

 