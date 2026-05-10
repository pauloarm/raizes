package com.example.raizes_do_nordeste.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.model.Usuario;
import com.example.raizes_do_nordeste.repository.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    
    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNome(usuarioAtualizado.getNome());
                    usuario.setEmail(usuarioAtualizado.getEmail());
                    usuario.setSenha(usuarioAtualizado.getSenha());
                    usuario.setCpf(usuarioAtualizado.getCpf());
                    usuario.setConsentimentoLgpd(usuarioAtualizado.getConsentimentoLgpd());
                    usuario.setPerfil(usuarioAtualizado.getPerfil());
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
