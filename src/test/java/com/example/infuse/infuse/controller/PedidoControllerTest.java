package com.example.infuse.infuse.controller;

import com.example.infuse.infuse.entity.Pedido;
import com.example.infuse.infuse.exception.BusinessException;
import com.example.infuse.infuse.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class PedidoControllerTest {

    @Mock
    private PedidoService pedidoService;

    @InjectMocks
    private PedidoController pedidoController;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void criarPedido() throws BusinessException {
        Pedido pedido = new Pedido(); // Adicione os campos necessários
        when(pedidoService.salvarPedido(any(Pedido.class))).thenReturn(pedido);

        ResponseEntity<Pedido> response = pedidoController.criarPedido(pedido);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pedido, response.getBody());
    }

    @Test
    void getOrders() {
        Pedido pedido1 = new Pedido(); // Configure o pedido1
        Pedido pedido2 = new Pedido(); // Configure o pedido2
        List<Pedido> pedidos = Arrays.asList(pedido1, pedido2);
        when(pedidoService.findByFiltros(any())).thenReturn(pedidos);

        ResponseEntity<List<Pedido>> response = pedidoController.getOrders(null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }
}

