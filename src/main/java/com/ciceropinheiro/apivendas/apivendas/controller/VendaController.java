package com.ciceropinheiro.apivendas.apivendas.controller;

import com.ciceropinheiro.apivendas.apivendas.dto.AtualizarVendaDto;
import com.ciceropinheiro.apivendas.apivendas.dto.VendaDto;
import com.ciceropinheiro.apivendas.apivendas.model.Venda;
import com.ciceropinheiro.apivendas.apivendas.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")

public class VendaController {

    @Autowired
    VendaRepository vendaRepository;

    @GetMapping("/tvendas")
    public List<VendaDto> listarVendas() {
        List<Venda> vendas = vendaRepository.findAll();
        return VendaDto.toDto(vendas);

    }

    @GetMapping(value = "/{id}")
    public Venda listarVendaById(@PathVariable Long id) {
        Optional<Venda> cliente = vendaRepository.findById(id);
        return cliente.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Objeto não encontrado"));


    }

    @PostMapping
    @Transactional
    public ResponseEntity<VendaDto> cadastrarVenda(@RequestBody @Valid VendaDto vendaDto, UriComponentsBuilder uriBuilder) {


        Venda vendaDtoConverter = vendaDto.converter();
        vendaRepository.save(vendaDtoConverter);

        URI uri = uriBuilder.path("/vendas/{id}").buildAndExpand(vendaDtoConverter.getId()).toUri();
        return ResponseEntity.created(uri).body(new VendaDto(vendaDtoConverter));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<VendaDto> atualizarVenda(@PathVariable Long id, @RequestBody @Valid AtualizarVendaDto atualizarVendaDto) {
        Optional<Venda> optional = vendaRepository.findById(id);
        if (optional.isPresent()){
            Venda venda = atualizarVendaDto.atualizar(id, vendaRepository);
            return ResponseEntity.ok(new VendaDto(venda));
        }

        return ResponseEntity.notFound().build();



    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removerVenda(@PathVariable Long id) {
        Optional<Venda> optional = vendaRepository.findById(id);
        if (optional.isPresent()) {
            vendaRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();



    }
}
