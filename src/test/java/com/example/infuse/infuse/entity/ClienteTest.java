package com.example.infuse.infuse.entity;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTest {

    @Test
    public void testGetterAndSetter() {

        Cliente cliente = new Cliente();

        cliente.setId(1L);
        cliente.setNumeroControle(String.valueOf(123L));
        cliente.setDataCadastro(20230410L);
        cliente.setNomeProduto("Produto Teste");
        cliente.setValor(new BigDecimal("99.99"));
        cliente.setQuantidade(10L);
        cliente.setCodigoCliente(456L);

        assertEquals(Long.valueOf(1), cliente.getId());
        assertEquals("123", cliente.getNumeroControle());
        assertEquals(Long.valueOf(20230410), cliente.getDataCadastro());
        assertEquals("Produto Teste", cliente.getNomeProduto());
        assertEquals(new BigDecimal("99.99"), cliente.getValor());
        assertEquals(Long.valueOf(10), cliente.getQuantidade());
        assertEquals(Long.valueOf(456), cliente.getCodigoCliente());
    }

}
