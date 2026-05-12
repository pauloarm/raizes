package com.example.raizes_do_nordeste.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.model.Unidade;
import com.example.raizes_do_nordeste.repository.UnidadeRepository;

@Service
public class UnidadeService {
    private final UnidadeRepository unidadeRepository;
    
    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public Unidade criarUnidade(Unidade unidade) {
        return unidadeRepository.save(unidade);
    }

    public List<Unidade> listarUnidades() {
        return unidadeRepository.findAll();
    }
}
