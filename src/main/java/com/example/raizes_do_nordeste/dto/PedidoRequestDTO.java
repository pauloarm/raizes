package com.example.raizes_do_nordeste.dto;

import java.util.List;

import com.example.raizes_do_nordeste.enums.CanalPedido;
import com.example.raizes_do_nordeste.enums.StatusPedido;

public record PedidoRequestDTO(
    Long usuarioId,
    Long unidadeId,
    CanalPedido canalPedido,
    StatusPedido status,
    List<ItemPedidoRequestDTO> itens
) {}  
