package com.ciceropinheiro.apivendas.apivendas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "tb_venda")
public class Venda {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Integer quantidadeParcelas;

    private Integer diaVencimento;

    private Integer diaCorte;

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "tb_cliente")
    Cliente cliente;


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

