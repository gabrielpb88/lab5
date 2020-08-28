package br.gov.sp.fatec.lab5.repository;

import br.gov.sp.fatec.lab5.entity.ItemPedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends CrudRepository<ItemPedido, Long> {
}
