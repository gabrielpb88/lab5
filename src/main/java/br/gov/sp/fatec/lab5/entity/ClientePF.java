package br.gov.sp.fatec.lab5.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue(value = "pf")
public class ClientePF extends Cliente {

    @JsonView({View.ClienteCompletoPF.class})
    @Column(name = "cpf", length = 14)
    private String cpf;

    public ClientePF() {
    }

    public ClientePF(String cpf) {
        this.cpf = cpf;
    }

    public ClientePF(String nome, String endereco, List<Pedido> pedidos, String cpf) {
        super(nome, endereco, pedidos);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "ClientePF{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", id=" + id +
                '}';
    }
}
