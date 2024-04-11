package com.example.infuse.infuse.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PedidoDTO {

    private Long id;
    private String numeroControle;
    private LocalDate dataCadastro;
    private String nomeProduto;
    private BigDecimal valor;
    private Integer quantidade;
    private BigDecimal valorTotal;


    public PedidoDTO(Long id, String numeroControle, LocalDate dataCadastro, String nomeProduto, BigDecimal valor, Integer quantidade, BigDecimal valorTotal) {
        this.id = id;
        this.numeroControle = numeroControle;
        this.dataCadastro = dataCadastro;
        this.nomeProduto = nomeProduto;
        this.valor = valor;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "PedidoDTO{" +
                "id=" + id +
                ", numeroControle='" + numeroControle + '\'' +
                ", dataCadastro=" + dataCadastro +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
