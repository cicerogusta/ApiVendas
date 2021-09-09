package com.ciceropinheiro.apivendas.apivendas.dto;

import com.ciceropinheiro.apivendas.apivendas.model.Cliente;
import com.ciceropinheiro.apivendas.apivendas.repository.ClienteRepository;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AtualizarClienteDto {

    @NotNull
    @NotEmpty
    private String nome;

    @NotNull
    @NotEmpty
    private String cpf;

    public Cliente atualizar(Long id, ClienteRepository clienteRepository) {
        Cliente cliente = clienteRepository.getById(id);
        cliente.setNome(this.nome);
        cliente.setCpf(this.cpf);

        return cliente;
    }
}
