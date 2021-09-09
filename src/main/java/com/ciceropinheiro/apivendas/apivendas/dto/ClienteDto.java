package com.ciceropinheiro.apivendas.apivendas.dto;

import com.ciceropinheiro.apivendas.apivendas.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDto {


    private String nome;

    private String cpf;

    private Integer diaVencimento;

    private Integer diaCorte;


    public ClienteDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.diaVencimento = cliente.getDiaVencimento();
        this.diaCorte = cliente.getDiaCorte();
    }

    public static List<ClienteDto> toDto(List<Cliente> clientes) {

        return clientes.stream().map(ClienteDto::new).collect(Collectors.toList());
    }

    public Cliente converter() {
        return new Cliente(nome, cpf, diaVencimento, diaCorte);
    }
}
