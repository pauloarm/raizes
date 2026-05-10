package com.example.raizes_do_nordeste.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.example.raizes_do_nordeste.enums.CanalPedido;
import com.example.raizes_do_nordeste.enums.StatusPedido;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime dataCriacao;
    @Column(nullable = false)
    private StatusPedido status;
    @Column(precision = 10, scale = 2)
    private BigDecimal valorTotal;
    @Column(nullable = false)
    private CanalPedido canalPedido;

    @ManyToOne
    private Unidade unidade;
    @ManyToOne
    private Usuario usuario;
    @OneToMany (mappedBy = "pedido")
    private List<ItemPedido> itens;
    public Pedido() {
    }
    public Pedido(Long id, LocalDateTime dataCriacao, StatusPedido status, BigDecimal valorTotal,
            CanalPedido canalPedido, Unidade unidade, Usuario usuario, List<ItemPedido> itens) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.status = status;
        this.valorTotal = valorTotal;
        this.canalPedido = canalPedido;
        this.unidade = unidade;
        this.usuario = usuario;
        this.itens = itens;
    }
    public Long getId() {
        return id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    public StatusPedido getStatus() {
        return status;
    }
    public void setStatus(StatusPedido status) {
        this.status = status;
    }
    public BigDecimal getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
    public CanalPedido getCanalPedido() {
        return canalPedido;
    }
    public void setCanalPedido(CanalPedido canalPedido) {
        this.canalPedido = canalPedido;
    }
    public Unidade getUnidade() {
        return unidade;
    }
    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public List<ItemPedido> getItens() {
        return itens;
    }
    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    

    
}
