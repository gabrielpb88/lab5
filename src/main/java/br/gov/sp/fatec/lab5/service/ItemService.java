package br.gov.sp.fatec.lab5.service;

import br.gov.sp.fatec.lab5.entity.Fornecedor;
import br.gov.sp.fatec.lab5.entity.Item;
import br.gov.sp.fatec.lab5.repository.FornecedorRepository;
import br.gov.sp.fatec.lab5.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Iterable<Item> findAll() {
        return repository.findAll();
    }

    public Item findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Iterable<Item> findByNome(String nome) {
        return repository.findByNomeContaining(nome);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COMERCIAL')")
    @Transactional
    public void save(Item item) {
        Fornecedor fornecedor = null;
        if(item.getFornecedor() != null){
            fornecedor = fornecedorRepository.findById(item.getFornecedor().getId()).orElseThrow(new FornecedorNotFound());
        }
        item.setFornecedor(fornecedor);
        item.setId(null);
        repository.save(item);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COMERCIAL')")
    @Transactional
    public void update(Item item) {
        Fornecedor fornecedor = null;
        if(item.getFornecedor() != null){
            fornecedor = fornecedorRepository.findById(item.getFornecedor().getId()).orElseThrow(new FornecedorNotFound());
        }
        item.setFornecedor(fornecedor);
        repository.save(item);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_COMERCIAL')")
    public void delete(Long id) {
        repository.deleteById(id);
    }

    class FornecedorNotFound implements Supplier {

        @Override
        public Object get() {
            return "Fornecedor não encontrado";
        }
    }
}
