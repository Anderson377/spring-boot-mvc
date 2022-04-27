package com.example.cursomc.domain;

import com.example.cursomc.domain.enuns.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
    private static final long serialVersionUID = 1L;

    private Integer numerodeParcelas;

    public PagamentoComCartao(){

    }

    public PagamentoComCartao(Integer numerodeParcelas) {
        this.numerodeParcelas = numerodeParcelas;
    }

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numerodeParcelas) {
        super(id, estado, pedido);
        this.numerodeParcelas = numerodeParcelas;
    }

    public Integer getNumerodeParcelas() {
        return numerodeParcelas;
    }

    public void setNumerodeParcelas(Integer numerodeParcelas) {
        this.numerodeParcelas = numerodeParcelas;
    }
}
