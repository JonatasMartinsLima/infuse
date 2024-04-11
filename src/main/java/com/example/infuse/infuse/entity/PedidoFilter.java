package com.example.infuse.infuse.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Setter
public class PedidoFilter {
    private Long numeroPedido;
    private LocalDate dataCadastro;
    private EntityManager entityManager;

    public List<Pedido> findByFiltros(PedidoFilter filter) {
        EntityManager entityManager = null;
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pedido> cq = cb.createQuery(Pedido.class);
        Root<Pedido> order = cq.from(Pedido.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filter.getNumeroPedido() != null) {
            predicates.add(cb.equal(order.get("id"), filter.getNumeroPedido()));
        }
        if (filter.getDataCadastro() != null) {
            predicates.add(cb.equal(order.get("dataCadastro"), filter.getDataCadastro()));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Pedido> query = entityManager.createQuery(cq);

        return query.getResultList();
    }

}


