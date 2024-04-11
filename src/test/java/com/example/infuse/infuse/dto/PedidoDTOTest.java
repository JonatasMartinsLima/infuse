package com.example.infuse.infuse.dto;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoDTOTest {

    @Test
    public void testPedidoDTO() {
        Long id = 1L;
        String numeroControle = "123";
        LocalDate dataCadastro = LocalDate.now();
        String nomeProduto = "Produto Teste";
        BigDecimal valor = new BigDecimal("100.00");
        Integer quantidade = 10;
        BigDecimal valorTotal = new BigDecimal("1000.00");

        PedidoDTO pedidoDTO = new PedidoDTO(id, numeroControle, dataCadastro, nomeProduto, valor, quantidade, valorTotal);

        assertEquals(id, pedidoDTO.getId());
        assertEquals(numeroControle, pedidoDTO.getNumeroControle());
        assertEquals(dataCadastro, pedidoDTO.getDataCadastro());
        assertEquals(nomeProduto, pedidoDTO.getNomeProduto());
        assertEquals(valor, pedidoDTO.getValor());
        assertEquals(quantidade, pedidoDTO.getQuantidade());
        assertEquals(valorTotal, pedidoDTO.getValorTotal());
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        LocalDate dataCadastro = LocalDate.of(1970, 1, 1);
        BigDecimal valor = new BigDecimal("2.3");

        assertEquals(
                "PedidoDTO{id=1, numeroControle='Numero Controle', dataCadastro=1970-01-01, nomeProduto='alice.liddell"
                        + "@example.org', valor=2.3, quantidade=1, valorTotal=2.3}",
                (new PedidoDTO(1L, "Numero Controle", dataCadastro, "alice.liddell@example.org", valor, 1,
                        new BigDecimal("2.3"))).toString());
    }


    @Test
    public void testToString() {
        PedidoDTO pedidoDTO = new PedidoDTO(1L, "123", LocalDate.now(), "Produto Teste", new BigDecimal("100.00"), 10, new BigDecimal("1000.00"));
        String expectedString = String.format("PedidoDTO{id=1, numeroControle='123', dataCadastro=%s, nomeProduto='Produto Teste', valor=100.00, quantidade=10, valorTotal=1000.00}", pedidoDTO.getDataCadastro());

        assertEquals(expectedString, pedidoDTO.toString());
    }
}
