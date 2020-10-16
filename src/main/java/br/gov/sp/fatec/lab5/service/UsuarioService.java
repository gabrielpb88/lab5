package br.gov.sp.fatec.lab5.service;

import br.gov.sp.fatec.lab5.entity.Usuario;
import br.gov.sp.fatec.lab5.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public Iterable<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario buscarPorNome(String nome){
        return repository.findById(nome).orElse(null);
    }

    public void save(Usuario Usuario){
        repository.save(Usuario);
    }

    public void delete(String nome) {
        repository.deleteById(nome);
    }

    public Usuario update(Usuario Usuario) {
        return repository.save(Usuario);
    }

}
