package br.gov.sp.fatec.lab5.repository;

import br.gov.sp.fatec.lab5.entity.Cliente;
import br.gov.sp.fatec.lab5.entity.ClientePF;
import br.gov.sp.fatec.lab5.entity.Item;
import br.gov.sp.fatec.lab5.entity.Pedido;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PedidoRepository extends CrudRepository<Pedido, Long> {
    Iterable<Pedido> findPedidosByCliente_NomeAndDataDaCompraBetween(String nome, Date dataInicial, Date dataFinal);
}
