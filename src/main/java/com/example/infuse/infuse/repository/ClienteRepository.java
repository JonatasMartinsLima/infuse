package com.example.infuse.infuse.repository;

import com.example.infuse.infuse.entity.Cliente;
import com.example.infuse.infuse.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Pedido> findByNumeroControle(String numeroControle);
}
