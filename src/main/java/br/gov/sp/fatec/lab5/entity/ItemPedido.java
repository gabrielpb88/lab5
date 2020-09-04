package br.gov.sp.fatec.lab5.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "item_pedido")
@Data @NoArgsConstructor
public class ItemPedido extends Identificador {

    public ItemPedido(Item item, Long quantidade){
        setItem(item);
        setQuantidade(quantidade);
    }

    private Long quantidade;

    @Column(name = "preco")
    private Double precoUnitario;

    @ManyToOne
    @JoinColumn(name = "ite_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "ped_id")
    private Pedido pedido;

    public void setItem(Item item){
        this.item = item;
        this.precoUnitario = item.getPreco();
    }

    public Double getValorTotal(){
        return precoUnitario * quantidade;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "pedido=" + pedido.getId() +
                "quantidade=" + quantidade +
                "precoUnitario=" + precoUnitario +
                ", item=" + item.getNome() +
                '}';
    }
}
