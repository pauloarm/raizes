package com.example.raizes_do_nordeste.dto;

public record EstoqueResponseDTO(
    Long id,
    String produto,
    String unidade,
    Integer quantidade
) {

}
