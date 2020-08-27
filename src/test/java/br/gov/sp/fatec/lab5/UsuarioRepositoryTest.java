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
    public void teste01(){
        Usuario usuario = new Usuario();
        usuario.setUsuario("Gabriel");
        usuario.setSenha("123");

        repo.save(usuario);

        Usuario found = repo.findById("Gabriel").get();
        Assertions.assertEquals("Gabriel", found.getUsuario());
    }
}
