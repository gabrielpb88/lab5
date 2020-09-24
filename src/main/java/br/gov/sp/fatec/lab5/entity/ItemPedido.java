package br.gov.sp.fatec.lab5.entity;

import javax.persistence.*;

@Entity
@Table(name = "item_pedido")
public class ItemPedido extends Identificador {

    public ItemPedido() {
    }

    public ItemPedido(Item item, Long quantidade){
        setItem(item);
        setQuantidade(quantidade);
    }

    public ItemPedido(Long quantidade, Double precoUnitario, Item item, Pedido pedido) {
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.item = item;
        this.pedido = pedido;
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

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Item getItem() {
        return item;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
