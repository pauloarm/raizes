package com.example.raizes_do_nordeste.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.raizes_do_nordeste.model.Pedido;
import com.example.raizes_do_nordeste.enums.CanalPedido;


public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByCanalPedido(CanalPedido canalPedido);
}
