package com.example.infuse.infuse.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PedidoTest {

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = new Pedido();
        pedido.setId(1L);
        pedido.setNumeroControle("001");
        pedido.setDataCadastro(LocalDate.now());
        pedido.setNome("Produto Teste");
        pedido.setValor(new BigDecimal("100.00"));
        pedido.setQuantidade(1); // Default value

    }

    @Test
    void testGettersAndSetters() {
        assertEquals(Long.valueOf(1), pedido.getId());
        assertEquals("001", pedido.getNumeroControle());
        assertEquals(LocalDate.now(), pedido.getDataCadastro());
        assertEquals("Produto Teste", pedido.getNome());
        assertEquals(new BigDecimal("100.00"), pedido.getValor());
        assertEquals(1, pedido.getQuantidade());
        assertNull(pedido.getCliente()); // Since not set
    }

    @Test
    void calcularValorTotalQuantidadeMenorQue5() {
        pedido.calcularValorTotal();
        assertEquals(new BigDecimal("100.00"), pedido.getValorTotal());
    }

    @Test
    void calcularValorTotalQuantidadeEntre5e10() {
        pedido.setQuantidade(6);
        pedido.calcularValorTotal();
        assertTrue(new BigDecimal("570.00").compareTo(pedido.getValorTotal().stripTrailingZeros()) == 0);
    }

    @Test
    void calcularValorTotalQuantidadeMaiorQue10() {
        pedido.setQuantidade(11);
        pedido.calcularValorTotal();
        assertTrue(new BigDecimal("990.00").compareTo(pedido.getValorTotal().stripTrailingZeros()) == 0);
    }
}
