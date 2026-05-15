package com.example.raizes_do_nordeste.service;

import java.util.Collections;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.dto.UsuarioRequestDTO;
import com.example.raizes_do_nordeste.dto.UsuarioResponseDTO;
import com.example.raizes_do_nordeste.enums.Perfil;
import com.example.raizes_do_nordeste.model.Usuario;
import com.example.raizes_do_nordeste.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO usuarioRequestDTO) {

        if(usuarioRepository.findByEmail(usuarioRequestDTO.email()).isPresent()){
            throw new RuntimeException("Este e-mail já esta cadastrado.");
        }
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDTO.nome());
        usuario.setEmail(usuarioRequestDTO.email());
        usuario.setCpf(usuarioRequestDTO.cpf());
        usuario.setConsentimentoLgpd(usuarioRequestDTO.consentimentoLgpd());

        usuario.setSenha(passwordEncoder.encode(usuarioRequestDTO.senha()));
        usuario.setPerfil(Perfil.CLIENTE);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getEmail(),
                Collections.singleton(usuarioSalvo.getPerfil().name()) //
                
        );
    }
}

