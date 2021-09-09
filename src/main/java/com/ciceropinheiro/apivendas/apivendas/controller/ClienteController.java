package com.ciceropinheiro.apivendas.apivendas.controller;

import com.ciceropinheiro.apivendas.apivendas.dto.ClienteDto;
import com.ciceropinheiro.apivendas.apivendas.model.Cliente;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @RequestMapping("/tclientes")
    public List<ClienteDto> listarClientes() {

        return ClienteDto.toDto(Arrays.asList(new Cliente(1L, "Cícero", "0001", 10, 10),
                new Cliente(2L, "Fabricio", "0002", 15, 15), new
                        Cliente(3L, "Ana", "0003", 18, 18)));

    }
}
