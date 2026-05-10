package com.example.raizes_do_nordeste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.raizes_do_nordeste.model.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Optional<Estoque> findByProdutoIdAndUnidadeId(Long produtoId, Long unidadeId);

}
