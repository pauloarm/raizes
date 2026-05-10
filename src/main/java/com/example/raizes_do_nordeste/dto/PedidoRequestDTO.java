package com.example.raizes_do_nordeste.dto;

import java.util.List;

import com.example.raizes_do_nordeste.enums.CanalPedido;

public record PedidoRequestDTO(
    Long usuarioId,
    Long unidadeId,
    CanalPedido canalPedido,
    List<ItemPedidoRequestDTO> itens
) {}  
