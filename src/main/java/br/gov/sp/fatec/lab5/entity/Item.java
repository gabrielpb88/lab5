package br.gov.sp.fatec.lab5.entity;

import javax.persistence.*;

@Entity
@Table(name = "ite_item")
@AttributeOverride(name = "id", column = @Column(name = "ite_id"))
public class Item extends Identificador {

    private String nome;
    private Double preco;

    public Item() {
    }

    public Item(String nome, Double preco, Fornecedor fornecedor) {
        this.nome = nome;
        this.preco = preco;
        this.fornecedor = fornecedor;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "for_id")
    private Fornecedor fornecedor;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", id=" + id +
                '}';
    }
}
