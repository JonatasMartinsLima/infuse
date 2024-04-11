package com.example.infuse.infuse.repository;

import com.example.infuse.infuse.entity.Pedido;
import com.example.infuse.infuse.entity.PedidoFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long>, JpaSpecificationExecutor<Pedido> {
    Optional<Pedido> findByNumeroControle(String numeroControle);
    List<Pedido> findByFiltros(PedidoFilter filter);
    boolean existsByNumeroControle(String numeroControle);

}


