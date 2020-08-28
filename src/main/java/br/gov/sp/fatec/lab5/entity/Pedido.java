package br.gov.sp.fatec.lab5.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ped_pedido")
@Getter
@Setter @NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "ped_id"))
public class Pedido extends Identificador{

    public Pedido(Cliente cliente, Date dataDaCompra){
        setCliente(cliente);
        setDataDaCompra(dataDaCompra);
    }

    @ManyToOne
    @JoinColumn(name = "cli_id")
    private Cliente cliente;

    @OneToMany
    private List<ItemPedido> items = new ArrayList<>();

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private List<Pagamento> pagamentos = new ArrayList<>();

    @Column(name = "data_do_pedido")
    private Date dataDaCompra;

    public void addItemPedido(ItemPedido itemPedido){
        items.add(itemPedido);
    }

    public double getValorTotal() {
        return items.stream().mapToDouble(ItemPedido::getValorTotal).sum();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "items=" + items +
                ", id=" + id +
                '}';
    }
}
