package com.example.raizes_do_nordeste.security;

import com.example.raizes_do_nordeste.model.Usuario;
import com.example.raizes_do_nordeste.repository.UsuarioRepository;
import com.example.raizes_do_nordeste.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public SecurityFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        // Extrai o token do cabeçalho da requisição
        String token = this.recuperarToken(request);

        if (token != null) {
            //  Valida o token e extrai o e-mail do usuário
            String email = tokenService.validarToken(token);
    
            if (!email.isEmpty()) {
                // Busca o usuário no banco de dados
                Usuario usuario = usuarioRepository.findByEmail(email)
                        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

                // Cria o objeto de autenticação 
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                // Salva a autenticação no contexto 
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        
        filterChain.doFilter(request, response);
    }


    private String recuperarToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.isEmpty() || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.replace("Bearer ", "");
    }
}
