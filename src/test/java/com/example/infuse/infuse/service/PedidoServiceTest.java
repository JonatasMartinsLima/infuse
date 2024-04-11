package com.example.infuse.infuse.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.infuse.infuse.dto.PedidoDTO;
import com.example.infuse.infuse.entity.Cliente;
import com.example.infuse.infuse.entity.Pedido;
import com.example.infuse.infuse.entity.PedidoFilter;
import com.example.infuse.infuse.exception.BusinessException;
import com.example.infuse.infuse.repository.ClienteRepository;
import com.example.infuse.infuse.repository.PedidoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PedidoService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PedidoServiceTest {
    @MockBean
    private ClienteRepository clienteRepository;

    @MockBean
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    @Test
    void testBuscarComFiltros() {

        ArrayList<Pedido> pedidoList = new ArrayList<>();
        when(pedidoRepository.findAll(Mockito.<Specification<Pedido>>any())).thenReturn(pedidoList);

        List<Pedido> actualBuscarComFiltrosResult = pedidoService.buscarComFiltros(new PedidoFilter());

        verify(pedidoRepository).findAll(Mockito.<Specification<Pedido>>any());
        assertTrue(actualBuscarComFiltrosResult.isEmpty());
        assertSame(pedidoList, actualBuscarComFiltrosResult);
    }

    @Test
    void testBuscarComFiltros2() {

        ArrayList<Pedido> pedidoList = new ArrayList<>();
        when(pedidoRepository.findAll(Mockito.<Specification<Pedido>>any())).thenReturn(pedidoList);

        PedidoFilter filter = new PedidoFilter();
        filter.setNumeroPedido(1L);

        List<Pedido> actualBuscarComFiltrosResult = pedidoService.buscarComFiltros(filter);

        verify(pedidoRepository).findAll(Mockito.<Specification<Pedido>>any());
        assertTrue(actualBuscarComFiltrosResult.isEmpty());
        assertSame(pedidoList, actualBuscarComFiltrosResult);
    }


    @Test
    void testSalvarPedido() throws BusinessException {

        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(1L);
        cliente.setDataCadastro(1L);
        cliente.setId(1L);
        cliente.setNomeProduto("alice.liddell@example.org");
        cliente.setNumeroControle(String.valueOf(1L));
        cliente.setQuantidade(1L);
        cliente.setValor(new BigDecimal("2.3"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCadastro(LocalDate.of(1970, 1, 1));
        pedido.setId(1L);
        pedido.setNome("Nome");
        pedido.setNumeroControle("Numero Controle");
        pedido.setPedidos("Pedidos");
        pedido.setQuantidade(1);
        pedido.setValor(new BigDecimal("2.3"));
        pedido.setValorTotal(new BigDecimal("2.3"));
        when(pedidoRepository.save(Mockito.<Pedido>any())).thenReturn(pedido);

        Cliente cliente2 = new Cliente();
        cliente2.setCodigoCliente(1L);
        cliente2.setDataCadastro(1L);
        cliente2.setId(1L);
        cliente2.setNomeProduto("alice.liddell@example.org");
        cliente2.setNumeroControle(String.valueOf(1L));
        cliente2.setQuantidade(1L);
        cliente2.setValor(new BigDecimal("2.3"));

        Pedido pedido2 = new Pedido();
        pedido2.setCliente(cliente2);
        pedido2.setDataCadastro(LocalDate.of(1970, 1, 1));
        pedido2.setId(1L);
        pedido2.setNome("Nome");
        pedido2.setNumeroControle("Numero Controle");
        pedido2.setPedidos("Pedidos");
        pedido2.setQuantidade(1);
        pedido2.setValor(new BigDecimal("2.3"));
        pedido2.setValorTotal(new BigDecimal("2.3"));

        Pedido actualSalvarPedidoResult = pedidoService.salvarPedido(pedido2);

        verify(pedidoRepository).save(Mockito.<Pedido>any());
        assertSame(pedido, actualSalvarPedidoResult);
    }


    @Test
    void testCriarPedido() throws Exception {

        when(pedidoRepository.existsByNumeroControle(Mockito.<String>any())).thenReturn(true);

        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(1L);
        cliente.setDataCadastro(1L);
        cliente.setId(1L);
        cliente.setNomeProduto("alice.liddell@example.org");
        cliente.setNumeroControle(String.valueOf(1L));
        cliente.setQuantidade(1L);
        cliente.setValor(new BigDecimal("2.3"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCadastro(LocalDate.of(1970, 1, 1));
        pedido.setId(1L);
        pedido.setNome("Nome");
        pedido.setNumeroControle("Numero Controle");
        pedido.setPedidos("Pedidos");
        pedido.setQuantidade(1);
        pedido.setValor(new BigDecimal("2.3"));
        pedido.setValorTotal(new BigDecimal("2.3"));

        assertThrows(Exception.class, () -> pedidoService.criarPedido(pedido));
        verify(pedidoRepository).existsByNumeroControle(eq("Numero Controle"));
    }


    @Test
    void testCriarPedido2() throws Exception {

        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(1L);
        cliente.setDataCadastro(1L);
        cliente.setId(1L);
        cliente.setNomeProduto("alice.liddell@example.org");
        cliente.setNumeroControle(String.valueOf(1L));
        cliente.setQuantidade(1L);
        cliente.setValor(new BigDecimal("2.3"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setDataCadastro(LocalDate.of(1970, 1, 1));
        pedido.setId(1L);
        pedido.setNome("Nome");
        pedido.setNumeroControle("Numero Controle");
        pedido.setPedidos("Pedidos");
        pedido.setQuantidade(1);
        pedido.setValor(new BigDecimal("2.3"));
        pedido.setValorTotal(new BigDecimal("2.3"));
        when(pedidoRepository.existsByNumeroControle(Mockito.<String>any())).thenReturn(false);
        when(pedidoRepository.save(Mockito.<Pedido>any())).thenReturn(pedido);

        Cliente cliente2 = new Cliente();
        cliente2.setCodigoCliente(1L);
        cliente2.setDataCadastro(1L);
        cliente2.setId(1L);
        cliente2.setNomeProduto("alice.liddell@example.org");
        cliente2.setNumeroControle(String.valueOf(1L));
        cliente2.setQuantidade(1L);
        cliente2.setValor(new BigDecimal("2.3"));

        Pedido pedido2 = new Pedido();
        pedido2.setCliente(cliente2);
        pedido2.setDataCadastro(LocalDate.of(1970, 1, 1));
        pedido2.setId(1L);
        pedido2.setNome("Nome");
        pedido2.setNumeroControle("Numero Controle");
        pedido2.setPedidos("Pedidos");
        pedido2.setQuantidade(1);
        pedido2.setValor(new BigDecimal("2.3"));
        pedido2.setValorTotal(new BigDecimal("2.3"));

        Pedido actualCriarPedidoResult = pedidoService.criarPedido(pedido2);

        verify(pedidoRepository).existsByNumeroControle(eq("Numero Controle"));
        verify(pedidoRepository).save(Mockito.<Pedido>any());
        BigDecimal expectedValorTotal = new BigDecimal("2.3");
        assertEquals(expectedValorTotal, pedido2.getValorTotal());
        assertSame(pedido, actualCriarPedidoResult);
    }

    @Test
    void testProcessarPedido() throws BusinessException {

        when(pedidoRepository.existsByNumeroControle(Mockito.<String>any())).thenReturn(true);
        LocalDate dataCadastro = LocalDate.of(1970, 1, 1);
        BigDecimal valor = new BigDecimal("2.3");

        // Act and Assert
        assertThrows(BusinessException.class, () -> pedidoService.processarPedido(new PedidoDTO(1L, "Numero Controle",
                dataCadastro, "alice.liddell@example.org", valor, 1, new BigDecimal("2.3"))));
        verify(pedidoRepository).existsByNumeroControle(eq("Numero Controle"));
    }

    @Test
    void testProcessarPedido2() throws BusinessException {

        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(1L);
        cliente.setDataCadastro(1L);
        cliente.setId(1L);
        cliente.setNomeProduto("alice.liddell@example.org");
        cliente.setNumeroControle(String.valueOf(1L));
        cliente.setQuantidade(1L);
        cliente.setValor(new BigDecimal("2.3"));
        Optional<Cliente> ofResult = Optional.of(cliente);
        when(clienteRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Cliente cliente2 = new Cliente();
        cliente2.setCodigoCliente(1L);
        cliente2.setDataCadastro(1L);
        cliente2.setId(1L);
        cliente2.setNomeProduto("alice.liddell@example.org");
        cliente2.setNumeroControle(String.valueOf(1L));
        cliente2.setQuantidade(1L);
        cliente2.setValor(new BigDecimal("2.3"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente2);
        pedido.setDataCadastro(LocalDate.of(1970, 1, 1));
        pedido.setId(1L);
        pedido.setNome("Nome");
        pedido.setNumeroControle("Numero Controle");
        pedido.setPedidos("Pedidos");
        pedido.setQuantidade(1);
        pedido.setValor(new BigDecimal("2.3"));
        pedido.setValorTotal(new BigDecimal("2.3"));
        when(pedidoRepository.save(Mockito.<Pedido>any())).thenReturn(pedido);
        when(pedidoRepository.existsByNumeroControle(Mockito.<String>any())).thenReturn(false);
        LocalDate dataCadastro = LocalDate.of(1970, 1, 1);
        BigDecimal valor = new BigDecimal("2.3");

        Pedido actualProcessarPedidoResult = pedidoService
                .processarPedido(new PedidoDTO(1L, "Numero Controle", dataCadastro, "42", valor, 1, new BigDecimal("2.3")));

        verify(pedidoRepository).existsByNumeroControle(eq("Numero Controle"));
        verify(clienteRepository).findById(Mockito.<Long>any());
        verify(pedidoRepository).save(Mockito.<Pedido>any());
        assertSame(pedido, actualProcessarPedidoResult);
    }

    @Test
    void testProcessarPedidos() throws BusinessException {

        assertTrue(pedidoService.processarPedidos(new ArrayList<>()).isEmpty());
    }

    @Test
    void testProcessarPedidos2() throws BusinessException {

        ArrayList<PedidoDTO> ordersDTO = new ArrayList<>();
        LocalDate dataCadastro = LocalDate.of(1970, 1, 1);
        BigDecimal valor = new BigDecimal("2.3");
        ordersDTO.add(new PedidoDTO(1L, "Numero Controle", dataCadastro, "alice.liddell@example.org", valor, 1,
                new BigDecimal("2.3")));

        assertEquals(1, pedidoService.processarPedidos(ordersDTO).size());
    }

    @Test
    void testProcessarPedidos3() throws BusinessException {
        // Arrange
        ArrayList<PedidoDTO> ordersDTO = new ArrayList<>();
        LocalDate dataCadastro = LocalDate.of(1970, 1, 1);
        BigDecimal valor = new BigDecimal("2.3");
        ordersDTO.add(new PedidoDTO(1L, "Numero Controle", dataCadastro, "alice.liddell@example.org", valor, 1,
                new BigDecimal("2.3")));
        LocalDate dataCadastro2 = LocalDate.of(1970, 1, 1);
        BigDecimal valor2 = new BigDecimal("2.3");
        ordersDTO.add(new PedidoDTO(1L, "Numero Controle", dataCadastro2, "alice.liddell@example.org", valor2, 1,
                new BigDecimal("2.3")));

        assertEquals(2, pedidoService.processarPedidos(ordersDTO).size());
    }

    @Test
    void testFindByFiltros() {

        ArrayList<Pedido> pedidoList = new ArrayList<>();
        when(pedidoRepository.findAll(Mockito.<Specification<Pedido>>any())).thenReturn(pedidoList);

        List<Pedido> actualFindByFiltrosResult = pedidoService.findByFiltros(new PedidoFilter());

        verify(pedidoRepository).findAll(Mockito.<Specification<Pedido>>any());
        assertTrue(actualFindByFiltrosResult.isEmpty());
        assertSame(pedidoList, actualFindByFiltrosResult);
    }

    @Test
    void testFindByFiltros2() {

        ArrayList<Pedido> pedidoList = new ArrayList<>();
        when(pedidoRepository.findAll(Mockito.<Specification<Pedido>>any())).thenReturn(pedidoList);

        PedidoFilter filter = new PedidoFilter();
        filter.setNumeroPedido(1L);

        List<Pedido> actualFindByFiltrosResult = pedidoService.findByFiltros(filter);

        verify(pedidoRepository).findAll(Mockito.<Specification<Pedido>>any());
        assertTrue(actualFindByFiltrosResult.isEmpty());
        assertSame(pedidoList, actualFindByFiltrosResult);
    }

    @Test
    void testFindByFiltros3() {

        ArrayList<Pedido> pedidoList = new ArrayList<>();
        when(pedidoRepository.findAll(Mockito.<Specification<Pedido>>any())).thenReturn(pedidoList);

        PedidoFilter filter = new PedidoFilter();
        filter.setDataCadastro(LocalDate.of(1970, 1, 1));

        List<Pedido> actualFindByFiltrosResult = pedidoService.findByFiltros(filter);

        verify(pedidoRepository).findAll(Mockito.<Specification<Pedido>>any());
        assertTrue(actualFindByFiltrosResult.isEmpty());
        assertSame(pedidoList, actualFindByFiltrosResult);
    }
}
