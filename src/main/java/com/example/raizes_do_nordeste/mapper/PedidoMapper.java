package com.example.raizes_do_nordeste.mapper;

import java.util.stream.Collectors;

import com.example.raizes_do_nordeste.dto.ItemPedidoResponseDTO;
import com.example.raizes_do_nordeste.dto.PedidoResponseDTO;
import com.example.raizes_do_nordeste.model.Pedido;

public class PedidoMapper {

    public static PedidoResponseDTO toPedidoResponseDTO(Pedido pedido) {
        var itensDTO = pedido.getItens().stream()
                .map(item -> new ItemPedidoResponseDTO(
                        item.getProduto().getId(),
                        item.getProduto().getNome(),
                        item.getQuantidade(),
                        item.getPrecoUnitario()
                ))
                .collect(Collectors.toList());
        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getStatus(),
                pedido.getValorTotal(),
                pedido.getCanalPedido(),
                pedido.getDataCriacao(),
                itensDTO
        );
    }
    
}
