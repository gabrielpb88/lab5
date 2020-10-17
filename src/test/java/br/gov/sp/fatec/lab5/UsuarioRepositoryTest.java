package br.gov.sp.fatec.lab5;

import br.gov.sp.fatec.lab5.entity.Usuario;
import br.gov.sp.fatec.lab5.repository.RoleRepository;
import br.gov.sp.fatec.lab5.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void shouldCreateAnUser(){
        Usuario usuario = new Usuario();
        String nome = "gabrielpb88";
        usuario.setNome(nome);
        usuario.setSenha("123");

        repo.save(usuario);

        Usuario found = repo.findById(usuario.getId()).get();
        Assertions.assertEquals(nome, found.getNome());
    }
}
