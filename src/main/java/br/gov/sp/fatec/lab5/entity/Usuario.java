package br.gov.sp.fatec.lab5.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usr_usuario")
@AttributeOverride(name = "id", column = @Column(name = "usr_id"))
public class Usuario extends Identificador {

    @JsonView({View.UsuarioSimples.class})
    @Column(name = "nome")
    private String nome;

    @JsonView(View.UsuarioCompleto.class)
    private String senha;

    public Usuario() {
    }

    @JsonView(View.UsuarioCompleto.class)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usr_has_roles",
            joinColumns =
                {@JoinColumn(name = "usuario_id", referencedColumnName = "usr_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "rol_id")})
    private Set<Role> roles = new HashSet<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
