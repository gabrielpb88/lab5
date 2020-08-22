package br.gov.sp.fatec.lab5.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ped_pedido")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "ped_id"))
public class Pedido extends Identificador{

    @ManyToOne
    @JoinColumn(name = "cli_id")
    private Cliente cliente;

    @ManyToMany
//    @JoinTable(name = "item_pedido",
//            joinColumns =
//                    {@JoinColumn(name = "ped_id")},
//            inverseJoinColumns = {@JoinColumn(name = "ite_id")})
    private List<ItemPedido> items = new ArrayList<>();

    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private List<Pagamento> pagamentos = new ArrayList<>();

    @Column(name = "data_do_pedido")
    private Date dataDaCompra;

    @Override
    public String toString() {
        return "Pedido{" +
                "cliente=" + cliente +
                ", items=" + items +
                ", pagamentos=" + pagamentos +
                ", id=" + id +
                '}';
    }
}
