package br.gov.sp.fatec.lab5;

import br.gov.sp.fatec.lab5.entity.Usuario;
import br.gov.sp.fatec.lab5.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repo;

    @Test
    public void shouldCreateAnUser(){
        Usuario usuario = new Usuario();
        String nome = "gabrielpb88";
        usuario.setUsuario(nome);
        usuario.setSenha("123");

        repo.save(usuario);

        Usuario found = repo.findById(nome).get();
        Assertions.assertEquals(nome, found.getUsuario());
    }
}
