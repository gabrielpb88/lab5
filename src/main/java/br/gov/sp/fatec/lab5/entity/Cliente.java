package br.gov.sp.fatec.lab5.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@Entity
@Table(name = "cli_cliente")
@AttributeOverride(name = "id", column = @Column(name = "cli_id"))
public abstract class Cliente extends Identificador {

    protected String nome;
    protected String endereco;

    public Cliente() {
    }

    public Cliente(String nome, String endereco, List<Pedido> pedidos) {
        this.nome = nome;
        this.endereco = endereco;
        this.pedidos = pedidos;
    }

    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    protected List<Pedido> pedidos = new ArrayList<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
