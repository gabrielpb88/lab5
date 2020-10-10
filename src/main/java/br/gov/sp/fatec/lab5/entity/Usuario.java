package br.gov.sp.fatec.lab5.entity;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usr_usuario")
public class Usuario {

    @Id
    @JsonView({View.UsuarioSimples.class})
    private String usuario;

    @JsonView(View.UsuarioCompleto.class)
    private String senha;

    public Usuario() {
    }

    @JsonView(View.UsuarioCompleto.class)
    @ManyToMany
    @JoinTable(name = "usr_has_roles",
            joinColumns =
                {@JoinColumn(name = "usuario", referencedColumnName = "usuario")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "role_name")})
    private Set<Role> roles = new HashSet<>();

    public boolean hasHole(String string){
        Object[] array = roles.toArray();
        for(Object role : array){
            Role temp = (Role) role;
            if (temp.getRole().equals(string)){
                return true;
            }
        }
        return false;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
