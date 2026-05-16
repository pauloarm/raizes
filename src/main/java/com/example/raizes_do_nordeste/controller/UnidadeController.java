package com.example.raizes_do_nordeste.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raizes_do_nordeste.dto.UnidadeDTO;
import com.example.raizes_do_nordeste.service.UnidadeService;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {
    private final UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @PostMapping
    public ResponseEntity<UnidadeDTO> criarUnidade(@RequestBody UnidadeDTO unidadeDTO) {
        UnidadeDTO unidadeCriada = unidadeService.criarUnidade(unidadeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(unidadeCriada);
    }

    @GetMapping
    public ResponseEntity<List<UnidadeDTO>> listarTodasUnidades() {
        List<UnidadeDTO> unidades = unidadeService.listarTodasUnidades();
        return ResponseEntity.ok(unidades);
    }
}
