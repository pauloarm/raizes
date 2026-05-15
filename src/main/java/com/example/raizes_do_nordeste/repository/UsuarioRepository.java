package com.example.raizes_do_nordeste.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.raizes_do_nordeste.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

}
