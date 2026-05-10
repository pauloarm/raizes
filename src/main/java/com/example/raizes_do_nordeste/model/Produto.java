package com.example.raizes_do_nordeste.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column (nullable = false)
    private String nome;
    @Column (nullable = false)
    private String descricao;
    @Column (nullable = false)
    private Double precoBase;
    @Column (nullable = false)
    private Boolean disponivel;

    @OneToMany(mappedBy = "produto")
    private List<ItemPedido> itensPedido;

    @OneToMany(mappedBy = "produto")
    private List<Estoque> estoques;

    
    public Produto() {
    }

    public Produto(Long id, String nome, String descricao, Double precoBase, Boolean disponivel,
            List<ItemPedido> itensPedido, List<Estoque> estoques) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoBase = precoBase;
        this.disponivel = disponivel;
        this.itensPedido = itensPedido;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoBase() {
        return precoBase;
    }

    public void setPrecoBase(Double precoBase) {
        this.precoBase = precoBase;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public List<Estoque> getEstoques() {
        return estoques;
    }

    public void setEstoques(List<Estoque> estoques) {
        this.estoques = estoques;
    }

    

}
