package com.example.raizes_do_nordeste.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raizes_do_nordeste.dto.ProdutoDTO;
import com.example.raizes_do_nordeste.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> adicionarProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produtoCriado = produtoService.adicionarProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoCriado);
    } 
}
