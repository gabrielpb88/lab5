package br.gov.sp.fatec.lab5.entity;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "pag_pagamento")
@AttributeOverride(name = "id", column = @Column(name = "pag_id"))
public abstract class Pagamento extends Identificador {

    protected Double valor;

    public Pagamento() {
    }

    public Pagamento(Double valor, Pedido pedido) {
        this.valor = valor;
        this.pedido = pedido;
    }

    @ManyToOne
    @JoinColumn(name = "ped_id")
    protected Pedido pedido;

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
