package com.example.raizes_do_nordeste.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raizes_do_nordeste.dto.PagamentoRequestDTO;
import com.example.raizes_do_nordeste.service.PagamentoService;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {
    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService){
        this.pagamentoService = pagamentoService;
    }

    @PostMapping("/mock")
    public ResponseEntity<Map<String, String>> processarPagamentoMock(@RequestBody PagamentoRequestDTO dto){
        String mensagem =pagamentoService.processarPagamento(dto);
        Map<String, String> resposta = new HashMap<>();
        resposta.put("resultado", mensagem);
        return ResponseEntity.ok(resposta);
    }
}
