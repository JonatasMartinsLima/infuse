package com.example.infuse.infuse.controller;

import com.example.infuse.infuse.entity.Pedido;
import com.example.infuse.infuse.entity.PedidoFilter;
import com.example.infuse.infuse.exception.BusinessException;
import com.example.infuse.infuse.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) throws BusinessException {

        return ResponseEntity.ok(pedidoService.salvarPedido(pedido));
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> getOrders(
            @RequestParam(value = "numeroPedido", required = false) Long numeroPedido,
            @RequestParam(value = "dataCadastro", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataCadastro) {

        PedidoFilter filter = new PedidoFilter();
        List<Pedido> pedidos = pedidoService.findByFiltros(filter);
        return ResponseEntity.ok(pedidos);
    }

}
