package br.gov.sp.fatec.lab5.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "item_pedido")
@Data
public class ItemPedido {

    @EmbeddedId
    private ItemPedidoPK id;

    private Long quantidade;
    private Double preco;

    public Double getValor(){
        return preco * quantidade;
    }

    @Embeddable
    private static class ItemPedidoPK implements Serializable {

        @ManyToOne
        @JoinColumn(name = "ite_id")
        protected Item item;

        @ManyToOne
        @JoinColumn(name = "ped_id")
        protected Pedido pedido;
    }
}
