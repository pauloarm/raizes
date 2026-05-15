package com.example.raizes_do_nordeste.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.raizes_do_nordeste.dto.ItemPedidoRequestDTO;
import com.example.raizes_do_nordeste.dto.ItemPedidoResponseDTO;
import com.example.raizes_do_nordeste.dto.PedidoRequestDTO;
import com.example.raizes_do_nordeste.dto.PedidoResponseDTO;
import com.example.raizes_do_nordeste.exception.EstoqueInsuficienteException;
import com.example.raizes_do_nordeste.model.Estoque;
import com.example.raizes_do_nordeste.model.ItemPedido;
import com.example.raizes_do_nordeste.model.Pedido;
import com.example.raizes_do_nordeste.model.Produto;
import com.example.raizes_do_nordeste.model.Unidade;
import com.example.raizes_do_nordeste.model.Usuario;
import com.example.raizes_do_nordeste.repository.EstoqueRepository;
import com.example.raizes_do_nordeste.repository.PedidoRepository;
import com.example.raizes_do_nordeste.repository.ProdutoRepository;
import com.example.raizes_do_nordeste.repository.UnidadeRepository;
import com.example.raizes_do_nordeste.repository.UsuarioRepository;
import com.example.raizes_do_nordeste.enums.StatusPedido; 

import jakarta.transaction.Transactional;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final UnidadeRepository unidadeRepository;
    private final ProdutoRepository produtoRepository;
    private final EstoqueRepository estoqueRepository;

    public PedidoService(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository,
                         UnidadeRepository unidadeRepository, ProdutoRepository produtoRepository,
                         EstoqueRepository estoqueRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.unidadeRepository = unidadeRepository;
        this.produtoRepository = produtoRepository;
        this.estoqueRepository = estoqueRepository;
    }


    @Transactional
    public Pedido criarPedido(PedidoRequestDTO pedidoRequest) {
        

        // Buscar o usuário e a unidade para associar ao pedido
        Usuario cliente = usuarioRepository.findById(pedidoRequest.usuarioId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Unidade unidade = unidadeRepository.findById(pedidoRequest.unidadeId())
            .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));
        
        // Criar o pedido e associar os itens
        Pedido pedido = new Pedido();
        pedido.setUsuario(cliente);
        pedido.setUnidade(unidade);
        pedido.setCanalPedido(pedidoRequest.canalPedido());
        pedido.setStatus(StatusPedido.AGUARDANDO_PAGAMENTO);
        pedido.setDataCriacao(LocalDateTime.now());
        pedido.setItens(new ArrayList<>());

        // Calcular o valor total do pedido e atualizar o estoque
        BigDecimal valorTotal = BigDecimal.ZERO;
        
        // Para cada item do pedido, buscar o produto, verificar o estoque e calcular o subtotal
        for(ItemPedidoRequestDTO itemDto:pedidoRequest.itens()){

            Produto produto = produtoRepository.findById(itemDto.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            
            Estoque estoqueLocal = estoqueRepository.findByProdutoIdAndUnidadeId(produto.getId(), unidade.getId())
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado para o produto e unidade"));

            if(estoqueLocal.getQuantidade() < itemDto.quantidade()) {
                throw new EstoqueInsuficienteException("Não há quantidade suficiente. Quantidade Disponivel: " + estoqueLocal.getQuantidade());
            }

            estoqueLocal.setQuantidade((estoqueLocal.getQuantidade() - itemDto.quantidade()));
            estoqueRepository.save(estoqueLocal);


            ItemPedido item = new ItemPedido();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemDto.quantidade());
            item.setPrecoUnitario(produto.getPrecoBase());

            pedido.getItens().add(item);

            BigDecimal subtotal = produto.getPrecoBase().multiply(BigDecimal.valueOf(itemDto.quantidade()));
            valorTotal = valorTotal.add(subtotal);
        }

        pedido.setValorTotal(valorTotal);
        return pedidoRepository.save(pedido);
    }

    public List<PedidoResponseDTO> listarPedidos() {
    return pedidoRepository.findAll().stream()
            .map(pedido -> {
                List<ItemPedidoResponseDTO> itens = pedido.getItens().stream()
                        .map(item -> new ItemPedidoResponseDTO(
                                item.getId(),
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
                        itens
                );
            })
            .collect(Collectors.toList());
    }

}
