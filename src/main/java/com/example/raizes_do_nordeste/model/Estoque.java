package com.example.raizes_do_nordeste.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "estoque")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantidade;

    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Unidade unidade;

    
    public Estoque() {
    }

    public Estoque(Long id, int quantidade, Produto produto, Unidade unidade) {
        this.id = id;
        this.quantidade = quantidade;
        this.produto = produto;
        this.unidade = unidade;
    }

    public Long getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Unidade getUnidade() {
        return unidade;
    }
    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    
}
