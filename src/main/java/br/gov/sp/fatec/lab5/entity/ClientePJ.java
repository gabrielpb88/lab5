package br.gov.sp.fatec.lab5.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue(value = "pj")
public class ClientePJ extends Cliente {

    @Column(name = "cnpj", length = 18)
    private String cnpj;

    public ClientePJ() {
    }

    public ClientePJ(String cnpj) {
        this.cnpj = cnpj;
    }

    public ClientePJ(String nome, String endereco, List<Pedido> pedidos, String cnpj) {
        super(nome, endereco, pedidos);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "ClientePJ{" +
                "cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", id=" + id +
                '}';
    }
}
