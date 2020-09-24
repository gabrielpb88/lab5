package br.gov.sp.fatec.lab5.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "for_fornecedor")
@AttributeOverride(name = "id", column = @Column(name = "for_id"))
public class Fornecedor extends Identificador {

    private String nome;
    private String cnpj;

    public Fornecedor() {
    }

    public Fornecedor(String nome, String cnpj, List<Item> items) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.items = items;
    }

    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
    private List<Item> items = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "nome='" + nome + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", items=" + items +
                ", id=" + id +
                '}';
    }
}
