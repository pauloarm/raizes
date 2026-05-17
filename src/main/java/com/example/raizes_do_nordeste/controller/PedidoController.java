package com.example.raizes_do_nordeste.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.raizes_do_nordeste.dto.PedidoRequestDTO;
import com.example.raizes_do_nordeste.dto.PedidoResponseDTO;
import com.example.raizes_do_nordeste.enums.CanalPedido;
import com.example.raizes_do_nordeste.mapper.PedidoMapper;
import com.example.raizes_do_nordeste.model.Pedido;
import com.example.raizes_do_nordeste.model.Usuario;
import com.example.raizes_do_nordeste.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> fazerPedido(@RequestBody PedidoRequestDTO pedidoDTO) {
        Pedido pedidoFeito = pedidoService.criarPedido(pedidoDTO);
        PedidoResponseDTO responseDTO = PedidoMapper.toPedidoResponseDTO(pedidoFeito);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos(
            @RequestParam(required = false) CanalPedido canalPedido, @AuthenticationPrincipal Usuario usuarioLogado) {
        {
            List<PedidoResponseDTO> pedidos = pedidoService.listarPedidos(canalPedido, usuarioLogado);
            return ResponseEntity.ok(pedidos);
        }
    }
}
