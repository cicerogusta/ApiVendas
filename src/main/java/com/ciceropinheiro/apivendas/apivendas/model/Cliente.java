package com.ciceropinheiro.apivendas.apivendas.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_cliente")
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nome;

    private String cpf;

    private Integer diaVencimento;

    private Integer diaCorte;

    public Cliente(String nome, String cpf, Integer diaVencimento, Integer diaCorte) {
        this.nome = nome;
        this.cpf = cpf;
        this.diaVencimento = diaVencimento;
        this.diaCorte = diaCorte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

