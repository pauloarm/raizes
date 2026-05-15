package com.example.raizes_do_nordeste.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "unidades")
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String endereco;
    @Column(nullable = false)
    private Boolean possuiCozinhaCompleta;

    @OneToMany(mappedBy = "unidade")
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "unidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Estoque> estoques;

    public Unidade() {
    }

    public Unidade(Long id, String nome, String endereco, Boolean possuiCozinhaCompleta, List<Pedido> pedidos,
            List<Estoque> estoques) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.possuiCozinhaCompleta = possuiCozinhaCompleta;
        this.pedidos = pedidos;
        this.estoques = estoques;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Boolean getPossuiCozinhaCompleta() {
        return possuiCozinhaCompleta;
    }

    public void setPossuiCozinhaCompleta(Boolean possuiCozinhaCompleta) {
        this.possuiCozinhaCompleta = possuiCozinhaCompleta;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }

    

    
}
