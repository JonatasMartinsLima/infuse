package com.example.infuse.infuse.service;

import com.example.infuse.infuse.entity.Pedido;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

public class PedidoSpecifications {
    public static Specification<Pedido> dataCadastroEquals(LocalDate dataCadastro) {
        return (root, query, cb) -> cb.equal(root.get("dataCadastro"), dataCadastro);
    }

    public static Specification<Pedido> numeroPedidoEquals(Long numeroPedido) {
        return (root, query, criteriaBuilder) -> {
            if (numeroPedido == null) return criteriaBuilder.conjunction();
            return criteriaBuilder.equal(root.get("numeroControle"), numeroPedido);
        };

    }

}
