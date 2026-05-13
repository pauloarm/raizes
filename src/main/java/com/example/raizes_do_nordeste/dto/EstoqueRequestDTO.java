package com.example.raizes_do_nordeste.dto;

public record EstoqueRequestDTO(
    Long produtoId,
    Long unidadeId,
    Integer quantidade
) {

}
