package br.gov.sp.fatec.lab5.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@MappedSuperclass
public abstract class Identificador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @JsonView({View.ClienteCompleto.class})
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Identificador{" +
                "id=" + id +
                '}';
    }
}
