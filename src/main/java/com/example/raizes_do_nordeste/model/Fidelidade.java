package com.example.raizes_do_nordeste.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "fidelidades")
public class Fidelidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int saldoPontos;
    @Column(nullable = false)
    private LocalDateTime dataUltimaAtualizacao;

    @OneToOne
    @JoinColumn (name = "usuario_id", nullable = false)
    private Usuario cliente;

    public Fidelidade() {
    }

    public Fidelidade(Long id, int saldoPontos, LocalDateTime dataUltimaAtualizacao, Usuario cliente) {
        this.id = id;
        this.saldoPontos = saldoPontos;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public int getSaldoPontos() {
        return saldoPontos;
    }

    public void setSaldoPontos(int saldoPontos) {
        this.saldoPontos = saldoPontos;
    }

    public LocalDateTime getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(LocalDateTime dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    
}
