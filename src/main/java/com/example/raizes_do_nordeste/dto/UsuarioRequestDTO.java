package com.example.raizes_do_nordeste.dto;

public record UsuarioRequestDTO(
    String nome,
    String email,
    String senha,
    String cpf,
    Boolean consentimentoLgpd
) {

}
