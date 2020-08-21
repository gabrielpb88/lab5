package br.gov.sp.fatec.lab5.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ite_item")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "ite_id"))
public class Item extends Identificador {

    private String nome;
    private Double preco;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "for_id")
    private Fornecedor fornecedor;

    @Override
    public String toString() {
        return "Item{" +
                "nome='" + nome + '\'' +
                ", preco=" + preco +
                ", id=" + id +
                '}';
    }
}
