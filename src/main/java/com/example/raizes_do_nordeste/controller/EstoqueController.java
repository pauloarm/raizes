package com.example.raizes_do_nordeste.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raizes_do_nordeste.dto.EstoqueRequestDTO;
import com.example.raizes_do_nordeste.dto.EstoqueResponseDTO;
import com.example.raizes_do_nordeste.service.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService){
        this.estoqueService = estoqueService;
    }

    @PostMapping
    public ResponseEntity<EstoqueResponseDTO> adicionarEstoque(@RequestBody EstoqueRequestDTO estoqueDTO){
        EstoqueResponseDTO estoqueCriado = estoqueService.criarEstoque(estoqueDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(estoqueCriado);
    }

    @GetMapping
    public ResponseEntity<List<EstoqueResponseDTO>> listarTodos(){
        List<EstoqueResponseDTO> estoques = estoqueService.listarTodoEstoque();
        return ResponseEntity.ok(estoques);
    }
}
