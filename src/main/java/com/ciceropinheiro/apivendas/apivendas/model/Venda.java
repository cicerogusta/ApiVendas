package com.ciceropinheiro.apivendas.apivendas.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_cliente")
public class Venda {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Integer quantidadeParcelas;

    private Integer diaVencimento;

    private Integer diaCorte;

    private BigDecimal valor;

    public Venda(Integer diaVencimento, Integer diaCorte, BigDecimal valor, Integer quantidadeParcelas) {
        this.diaVencimento = diaVencimento;
        this.diaCorte = diaCorte;
        this.valor = valor;
        this.quantidadeParcelas = quantidadeParcelas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Venda cliente = (Venda) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

