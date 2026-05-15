package com.example.raizes_do_nordeste.service;

import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.dto.PagamentoRequestDTO;
import com.example.raizes_do_nordeste.enums.StatusPedido;
import com.example.raizes_do_nordeste.model.Pedido;
import com.example.raizes_do_nordeste.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
public class PagamentoService {
    private final PedidoRepository pedidoRepository;

    public PagamentoService(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    public String processarPagamento(PagamentoRequestDTO dto){
        Pedido pedido = pedidoRepository.findById(dto.pedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if(pedido.getStatus() != StatusPedido.AGUARDANDO_PAGAMENTO ){
            throw new RuntimeException("Este pedido não está aguardando pagamento");
        }

        if(dto.aprovado()){
            pedido.setStatus(StatusPedido.PREPARANDO);
            pedidoRepository.save(pedido);
            return "Pagamento Aprovado! Pedido em produção.";
        }else{
            pedido.setStatus(StatusPedido.CANCELADO);
            pedidoRepository.save(pedido);

            return "Pagamento Recusado. Pedido cancelado";
        }
    }
}
