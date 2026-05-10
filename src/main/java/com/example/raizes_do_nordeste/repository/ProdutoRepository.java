package com.example.raizes_do_nordeste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.raizes_do_nordeste.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
