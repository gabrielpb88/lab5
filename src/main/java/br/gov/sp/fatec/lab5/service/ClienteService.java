package br.gov.sp.fatec.lab5.service;

import br.gov.sp.fatec.lab5.entity.Cliente;
import br.gov.sp.fatec.lab5.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Iterable<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public void save(Cliente cliente){
        repository.save(cliente);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Cliente update(Cliente cliente) {
        return repository.save(cliente);
    }

    public Iterable<Cliente> buscarPorNome(String nome) {
        return repository.findByNomeContaining(nome);
    }
}
