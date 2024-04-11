package com.example.infuse.infuse.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String filtros;
    private String pedidos;
    private String numeroControle;
    private LocalDate dataCadastro = LocalDate.now();
    private String nome;
    private BigDecimal valor;
    private int quantidade = 1;
    private BigDecimal valorTotal;

    @ManyToOne
    private Cliente cliente;

    public void calcularValorTotal() {
        BigDecimal desconto = BigDecimal.ZERO;

        if (quantidade > 5 && quantidade < 10) {
            desconto = new BigDecimal("0.05");
        } else if (quantidade >= 10) {
            desconto = new BigDecimal("0.10");
        }

        BigDecimal valorDesconto = this.valor.multiply(BigDecimal.valueOf(quantidade)).multiply(desconto);
        this.valorTotal = this.valor.multiply(BigDecimal.valueOf(quantidade)).subtract(valorDesconto);
    }

}

