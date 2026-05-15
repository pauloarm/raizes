package com.example.raizes_do_nordeste.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raizes_do_nordeste.dto.PedidoRequestDTO;
import com.example.raizes_do_nordeste.dto.PedidoResponseDTO;
import com.example.raizes_do_nordeste.mapper.PedidoMapper;
import com.example.raizes_do_nordeste.model.Pedido;
import com.example.raizes_do_nordeste.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO>fazerPedido(@RequestBody PedidoRequestDTO pedidoDTO){
        Pedido pedidoFeito = pedidoService.criarPedido(pedidoDTO);
        PedidoResponseDTO responseDTO = PedidoMapper.toPedidoResponseDTO(pedidoFeito);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
