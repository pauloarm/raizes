package com.example.raizes_do_nordeste.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.raizes_do_nordeste.dto.LoginRequestDTO;
import com.example.raizes_do_nordeste.dto.LoginResponseDTO;
import com.example.raizes_do_nordeste.model.Usuario;
import com.example.raizes_do_nordeste.service.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> fazerLogin(@RequestBody LoginRequestDTO dto){
        var tokenDeAutenticacao = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        
        var authentication = authenticationManager.authenticate(tokenDeAutenticacao);
        
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
    
        String tokenJWT = tokenService.gerarToken(usuarioLogado);
        
        return ResponseEntity.ok(new LoginResponseDTO(tokenJWT));
    }

    
}
