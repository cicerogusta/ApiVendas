package com.ciceropinheiro.apivendas.apivendas.controller;

import com.ciceropinheiro.apivendas.apivendas.dto.AtualizarClienteDto;
import com.ciceropinheiro.apivendas.apivendas.dto.ClienteDto;
import com.ciceropinheiro.apivendas.apivendas.model.Cliente;
import com.ciceropinheiro.apivendas.apivendas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.awt.print.Pageable;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")

public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping("/tclientes")
    public Page<ClienteDto> listarTodosClientes(@RequestParam(required = false) @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) PageRequest paginacao) {


        Page<Cliente> clientes = clienteRepository.findAll(paginacao);
        return ClienteDto.toDto(clientes);

    }

    @GetMapping(value = "/{id}")
    public Cliente listarClientesById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado"));


    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Valid ClienteDto clienteDto, UriComponentsBuilder uriBuilder) {


        Cliente clienteDtoConverter = clienteDto.converter();
        clienteRepository.save(clienteDtoConverter);

        URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(clienteDtoConverter.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDto(clienteDtoConverter));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClienteDto> atualizarCliente(@PathVariable Long id, @RequestBody @Valid AtualizarClienteDto atualizarClienteDto) {
        Optional<Cliente> optional = clienteRepository.findById(id);
        if (optional.isPresent()) {
            Cliente cliente = atualizarClienteDto.atualizar(id, clienteRepository);
            return ResponseEntity.ok(new ClienteDto(cliente));
        }

        return ResponseEntity.notFound().build();


    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removerCliente(@PathVariable Long id) {
        Optional<Cliente> optional = clienteRepository.findById(id);
        if (optional.isPresent()) {
            clienteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();


    }
}
