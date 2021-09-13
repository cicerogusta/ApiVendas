package com.ciceropinheiro.apivendas.apivendas.dto;

import com.ciceropinheiro.apivendas.apivendas.model.Cliente;
import com.ciceropinheiro.apivendas.apivendas.model.Venda;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VendaDto {


    private Integer quantidadeParcelas;

    private Integer diaVencimento;

    private Integer diaCorte;

    private BigDecimal valor;

    private Cliente cliente;


    public VendaDto(Venda venda) {
        this.quantidadeParcelas = venda.getQuantidadeParcelas();
        this.diaVencimento = venda.getDiaVencimento();
        this.diaCorte = venda.getDiaCorte();
        this.valor = venda.getValor();
        this.cliente = venda.getCliente();

    }

    public static List<VendaDto> toDto(List<Venda> vendas) {

        return vendas.stream().map(VendaDto::new).collect(Collectors.toList());
    }

    public Venda converter() {
        return new Venda(null, quantidadeParcelas, diaVencimento, diaCorte, valor, cliente);
    }
}
