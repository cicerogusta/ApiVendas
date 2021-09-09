package com.ciceropinheiro.apivendas.apivendas.config.validacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErroDeFormularioDto {

    private String campo;
    private String erro;
}
