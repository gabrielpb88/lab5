package br.gov.sp.fatec.lab5.repository;

import br.gov.sp.fatec.lab5.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario findByNome(String nome);
    Usuario findByEmail(String email);
}
