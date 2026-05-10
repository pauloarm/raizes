package com.example.raizes_do_nordeste.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.raizes_do_nordeste.enums.CanalPedido;
import com.example.raizes_do_nordeste.enums.StatusPedido;

public record PedidoResponseDTO(
    Long pedidoId,
    StatusPedido status,
    BigDecimal total,
    CanalPedido canalPedido,
    LocalDateTime createdAt,
    List<ItemPedidoResponseDTO> itens
) {

}
