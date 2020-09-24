package br.gov.sp.fatec.lab5.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ped_pedido")
@AttributeOverride(name = "id", column = @Column(name = "ped_id"))
public class Pedido extends Identificador{

    public Pedido() {
    }

    public Pedido(Cliente cliente, Set<ItemPedido> items, Set<Pagamento> pagamentos, Date dataDaCompra) {
        this.cliente = cliente;
        this.items = items;
        this.pagamentos = pagamentos;
        this.dataDaCompra = dataDaCompra;
    }

    public Pedido(Cliente cliente, Date dataDaCompra){
        setCliente(cliente);
        setDataDaCompra(dataDaCompra);
    }

    @ManyToOne
    @JoinColumn(name = "cli_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private Set<ItemPedido> items = new HashSet<>();

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private Set<Pagamento> pagamentos = new HashSet<>();

    @Column(name = "data_do_pedido")
    private Date dataDaCompra;

    public double getValorTotal() {
        return items.stream().mapToDouble(ItemPedido::getValorTotal).sum();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<ItemPedido> getItems() {
        return items;
    }

    public void setItems(Set<ItemPedido> items) {
        this.items = items;
    }

    public Set<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(Set<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    public Date getDataDaCompra() {
        return dataDaCompra;
    }

    public void setDataDaCompra(Date dataDaCompra) {
        this.dataDaCompra = dataDaCompra;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "items=" + items +
                ", id=" + id +
                '}';
    }
}
