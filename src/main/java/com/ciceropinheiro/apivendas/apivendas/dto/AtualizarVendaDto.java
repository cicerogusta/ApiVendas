package com.ciceropinheiro.apivendas.apivendas.dto;

import com.ciceropinheiro.apivendas.apivendas.model.Cliente;
import com.ciceropinheiro.apivendas.apivendas.model.Venda;
import com.ciceropinheiro.apivendas.apivendas.repository.ClienteRepository;
import com.ciceropinheiro.apivendas.apivendas.repository.VendaRepository;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Data
public class AtualizarVendaDto {

    @NotNull
    @NotEmpty
    private Integer quantidadeParcelas;


    @NotNull
    @NotEmpty
    private BigDecimal valor;


    public Venda atualizar(Long id, VendaRepository vendaRepository) {
        Venda venda = vendaRepository.getById(id);
        venda.setQuantidadeParcelas(this.quantidadeParcelas);
        venda.setValor(this.valor);

        return venda;
    }
}
