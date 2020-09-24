package br.gov.sp.fatec.lab5.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "pag_pagamento_cartao")
public class PagamentoCartao extends Pagamento {

    public PagamentoCartao() {
    }

    public PagamentoCartao(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public PagamentoCartao(Double valor, Pedido pedido, Integer parcelas) {
        super(valor, pedido);
        this.parcelas = parcelas;
    }

    private Integer parcelas;

    public Double getValorParcelas() {
        return this.valor / parcelas;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    @Override
    public String toString() {
        return "PagamentoCartao{" +
                "parcelas=" + parcelas +
                ", valor=" + valor +
                ", pedido=" + pedido +
                ", id=" + id +
                '}';
    }
}
