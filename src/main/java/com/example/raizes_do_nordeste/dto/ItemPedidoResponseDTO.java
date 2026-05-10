package com.example.raizes_do_nordeste.dto;

import java.math.BigDecimal;

public record ItemPedidoResponseDTO(
    Long produtoId,
    String nomeProduto,
    Integer quantidade,
    BigDecimal precoUnitario
) {

}
