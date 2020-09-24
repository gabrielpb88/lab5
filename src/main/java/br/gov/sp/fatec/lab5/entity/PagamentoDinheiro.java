package br.gov.sp.fatec.lab5.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pag_pagamento_dinheiro")
public class PagamentoDinheiro extends Pagamento {

    public PagamentoDinheiro() {
    }

    public PagamentoDinheiro(Double desconto) {
        this.desconto = desconto;
    }

    public PagamentoDinheiro(Double valor, Pedido pedido, Double desconto) {
        super(valor, pedido);
        this.desconto = desconto;
    }

    private Double desconto;

    public Double getValor() {
        return this.valor - (this.valor * (desconto / 100));
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    @Override
    public String toString() {
        return "PagamentoDinheiro{" +
                "desconto=" + desconto +
                ", valor=" + valor +
                ", pedido=" + pedido +
                ", id=" + id +
                '}';
    }
}
