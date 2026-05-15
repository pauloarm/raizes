package com.example.raizes_do_nordeste.dto;

public record PagamentoRequestDTO(
    Long pedidoId,
    String formaPagamento,
    Boolean aprovado
) {

}
