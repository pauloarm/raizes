package com.example.raizes_do_nordeste.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.model.Estoque;
import com.example.raizes_do_nordeste.repository.EstoqueRepository;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public List<Estoque> listarEstoque() {
        return estoqueRepository.findAll();
    }

    public Estoque criarEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }
}
 