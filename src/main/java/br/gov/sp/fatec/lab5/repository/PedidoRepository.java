package br.gov.sp.fatec.lab5.repository;

import br.gov.sp.fatec.lab5.entity.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {
}
