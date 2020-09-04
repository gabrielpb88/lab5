package br.gov.sp.fatec.lab5.repository;

import br.gov.sp.fatec.lab5.entity.Pedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {

    List<Pedido> findPedidosByCliente_NomeAndDataDaCompraBetween(String nome, Date dataInicial, Date dataFinal);

    @Query("select p from Pedido p " +
            "join p.items itemsPedido " +
            "where p.cliente.nome = ?1 " +
            "and itemsPedido.item.nome = ?2 ")
    List<Pedido> findByClienteAndItem(String nome, String nomeItem);
}
