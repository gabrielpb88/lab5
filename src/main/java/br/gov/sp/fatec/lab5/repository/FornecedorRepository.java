package br.gov.sp.fatec.lab5.repository;

import br.gov.sp.fatec.lab5.entity.Fornecedor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends CrudRepository<Fornecedor, Long> {
}
