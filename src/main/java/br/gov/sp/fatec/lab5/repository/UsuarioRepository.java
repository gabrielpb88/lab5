package br.gov.sp.fatec.lab5.repository;

import br.gov.sp.fatec.lab5.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {
}
