package com.example.raizes_do_nordeste.dto;

import java.math.BigDecimal;

public record ProdutoDTO(
    Long id,
    String nome,
    String descricao,
    BigDecimal preco,
    Boolean disponivel
) {

}
