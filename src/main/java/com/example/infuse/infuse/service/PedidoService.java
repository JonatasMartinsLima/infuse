package com.example.infuse.infuse.service;

import com.example.infuse.infuse.dto.PedidoDTO;
import com.example.infuse.infuse.entity.Cliente;
import com.example.infuse.infuse.entity.Pedido;
import com.example.infuse.infuse.entity.PedidoFilter;
import com.example.infuse.infuse.exception.BusinessException;
import com.example.infuse.infuse.repository.ClienteRepository;
import com.example.infuse.infuse.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Pedido> buscarComFiltros(PedidoFilter filter) {
        Specification<Pedido> spec = Specification.where(null);

        if (filter.getNumeroPedido() != null) {
            spec = spec.and(PedidoSpecifications.numeroPedidoEquals(filter.getNumeroPedido()));
        }

        return pedidoRepository.findAll(spec);
    }

    public Pedido salvarPedido(Pedido pedido) throws BusinessException {
        return pedidoRepository.save(pedido);
    }

    public Pedido criarPedido(Pedido pedido) throws Exception {

        if (pedidoRepository.existsByNumeroControle(pedido.getNumeroControle())) {
            throw new Exception("Número de controle já cadastrado.");
        }

        if (pedido.getDataCadastro() == null) {
            pedido.setDataCadastro(LocalDate.now());
        }

        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }

    private static final int MAX_PEDIDOS = 10;

    @Transactional
    public Pedido processarPedido(PedidoDTO pedidoDTO) throws BusinessException {
        validarNumeroDeControle(pedidoDTO.getNumeroControle());
        Cliente cliente = validarEObterProduto(pedidoDTO.getNomeProduto());
        Pedido pedido = converterParaEntidade(pedidoDTO, cliente);
        pedido.calcularValorTotal();
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> processarPedidos(List<PedidoDTO> ordersDTO) throws BusinessException {
        if (ordersDTO.size() > MAX_PEDIDOS) {
            throw new BusinessException();
        }
        return ordersDTO.stream().map(this::processarPedidoIndividual).collect(Collectors.toList());
    }

    private Pedido processarPedidoIndividual(PedidoDTO pedidoDTO) {
        return new Pedido();
    }


    private void validarNumeroDeControle(String numeroControle) throws BusinessException {
        if (pedidoRepository.existsByNumeroControle(numeroControle)) {
            throw new BusinessException();
        }
    }

    private Cliente validarEObterProduto(String nomeProduto) throws BusinessException {
        return clienteRepository.findById(Long.valueOf(nomeProduto))
                .orElseThrow(BusinessException::new);
    }

    private Pedido converterParaEntidade(PedidoDTO pedidoDTO, Cliente cliente) {
        Pedido pedido = new Pedido();
        pedido.setNumeroControle(pedidoDTO.getNumeroControle());
        pedido.setDataCadastro(pedidoDTO.getDataCadastro() != null ? pedidoDTO.getDataCadastro() : LocalDate.now());
        pedido.setNome(pedidoDTO.getNomeProduto());
        pedido.setValor(pedidoDTO.getValor());
        pedido.setQuantidade(pedidoDTO.getQuantidade() != null ? pedidoDTO.getQuantidade() : 1);
        pedido.setCliente(cliente);

        return pedido;
    }

    public List<Pedido> findByFiltros(PedidoFilter filter) {
        Specification<Pedido> spec = Specification.where(null);

        if (filter.getNumeroPedido() != null) {
            spec = spec.and(PedidoSpecifications.numeroPedidoEquals(filter.getNumeroPedido()));
        }

        if (filter.getDataCadastro() != null) {
            spec = spec.and(PedidoSpecifications.dataCadastroEquals(filter.getDataCadastro()));
        }

        return pedidoRepository.findAll(spec);


    }

}




