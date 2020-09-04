package br.gov.sp.fatec.lab5.service;

import br.gov.sp.fatec.lab5.entity.ItemPedido;
import br.gov.sp.fatec.lab5.entity.Pedido;
import br.gov.sp.fatec.lab5.repository.ItemPedidoRepository;
import br.gov.sp.fatec.lab5.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Transactional
    public void salvarPedido(Pedido pedido, Set<ItemPedido> itemPedidos){
        pedidoRepository.save(pedido);
        pedido.setItems(itemPedidos);
        itemPedidos.forEach(itemPedido -> itemPedido.setPedido(pedido));

        itemPedidoRepository.saveAll(itemPedidos);
    }
}
