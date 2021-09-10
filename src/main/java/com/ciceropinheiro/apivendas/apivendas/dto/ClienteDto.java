package com.ciceropinheiro.apivendas.apivendas.dto;

import com.ciceropinheiro.apivendas.apivendas.model.Cliente;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDto {


    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String cpf;

    @NotNull
    private Integer diaVencimento;

    @NotNull
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
