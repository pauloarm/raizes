package com.example.raizes_do_nordeste.service;

import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.dto.UnidadeDTO;
import com.example.raizes_do_nordeste.model.Unidade;
import com.example.raizes_do_nordeste.repository.UnidadeRepository;

import jakarta.transaction.Transactional;

@Service
public class UnidadeService {
    private final UnidadeRepository unidadeRepository;
    
    public UnidadeService(UnidadeRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    @Transactional
    public UnidadeDTO criarUnidade(UnidadeDTO unidadeDTO) {
        Unidade unidade = new Unidade();
        unidade.setNome(unidadeDTO.nome());
        unidade.setEndereco(unidadeDTO.endereco());
        unidade.setPossuiCozinhaCompleta(unidadeDTO.possuiCozinhaCompleta());
        Unidade unidadeSalva = unidadeRepository.save(unidade);
        return new UnidadeDTO(unidadeSalva.getId(), unidadeSalva.getNome(), unidadeSalva.getEndereco(), unidadeSalva.getPossuiCozinhaCompleta());
    }

}
