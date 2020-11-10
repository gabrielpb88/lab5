package br.gov.sp.fatec.lab5.service;

import br.gov.sp.fatec.lab5.entity.Fornecedor;
import br.gov.sp.fatec.lab5.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public Iterable<Fornecedor> findAll() {
        return repository.findAll();
    }

    public Fornecedor findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void save(Fornecedor fornecedor) {
        fornecedor.setId(null);
        repository.save(fornecedor);
    }

    public void update(Fornecedor fornecedor) {
        repository.save(fornecedor);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
