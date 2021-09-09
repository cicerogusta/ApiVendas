package com.ciceropinheiro.apivendas.apivendas.controller;

import com.ciceropinheiro.apivendas.apivendas.dto.ClienteDto;
import com.ciceropinheiro.apivendas.apivendas.model.Cliente;
import com.ciceropinheiro.apivendas.apivendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")

public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/tclientes")
    public List<ClienteDto> listarTodosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ClienteDto.toDto(clientes);

    }

    @GetMapping(value = "/{id}")
    public Cliente listarClientesById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado"));


    }
    @PostMapping
    public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody ClienteDto clienteDto, UriComponentsBuilder uriBuilder) {
        Cliente clienteDtoConverter = clienteDto.converter();
        clienteRepository.save(clienteDtoConverter);

        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(clienteDtoConverter.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDto(clienteDtoConverter));
    }
}
