package com.example.raizes_do_nordeste.dto;

import java.util.Set;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String email,
    Set<String> perfis
) {

}
