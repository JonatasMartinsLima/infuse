package com.example.infuse.infuse.service;

import com.example.infuse.infuse.entity.Pedido;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;
import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PedidoSpecificationsTest {

    @Test
    void testNumeroPedidoEquals() {
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        Root<Pedido> root = mock(Root.class);
        Predicate predicateMock = mock(Predicate.class);
        when(cb.equal(any(), any())).thenReturn(predicateMock);

        Specification<Pedido> spec = PedidoSpecifications.numeroPedidoEquals(1L);

    }

    @Test
    void testDataCadastroEquals() {
        CriteriaBuilder cb = mock(CriteriaBuilder.class);
        Root<Pedido> root = mock(Root.class);
        Predicate predicateMock = mock(Predicate.class);
        when(cb.equal(any(), any())).thenReturn(predicateMock);

        Specification<Pedido> spec = PedidoSpecifications.dataCadastroEquals(LocalDate.now());

    }
}


