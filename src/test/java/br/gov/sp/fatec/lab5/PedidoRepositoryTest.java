package br.gov.sp.fatec.lab5;

import br.gov.sp.fatec.lab5.entity.ClientePF;
import br.gov.sp.fatec.lab5.entity.Item;
import br.gov.sp.fatec.lab5.entity.ItemPedido;
import br.gov.sp.fatec.lab5.entity.Pedido;
import br.gov.sp.fatec.lab5.repository.ClienteRepository;
import br.gov.sp.fatec.lab5.repository.ItemRepository;
import br.gov.sp.fatec.lab5.repository.PedidoRepository;
import br.gov.sp.fatec.lab5.service.PedidoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PedidoRepositoryTest {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemRepository itemRepository;

    private static Item item;
    private static Item item2;
    private static Item item3;
    private static Item item4;
    private static ClientePF cliente;

    @BeforeAll()
    public void setUp(){
        item = new Item("Celular", 1500.00, null);
        item2 = new Item("cabo usb", 50.0, null);
        item3 = new Item("Notebook", 1600.00, null);
        item4 = new Item("nvme", 500.0, null);
        itemRepository.saveAll(Arrays.asList(item, item2, item3, item4));

        cliente = new ClientePF("361.435.678-75");
        cliente.setNome("Gabriel");
        cliente.setEndereco("Rua Gisele Martins");
        clienteRepository.save(cliente);

    }

    @Test
    public void deveSalvarUmPedido(){

        Pedido pedido = new Pedido(cliente, new Date());

        pedidoService.salvarPedido(pedido,
                new HashSet<>(Arrays.asList(
                        new ItemPedido(item, 1L),
                        new ItemPedido(item2, 2L))));

        Assertions.assertEquals(1600, pedido.getValorTotal());
        Assertions.assertNotNull(pedido.getId());
    }

    @Test
    public void deveBuscarPedidosDoClienteComItem(){

        Pedido pedido = new Pedido(cliente, new Date());
        pedidoService.salvarPedido(pedido, new HashSet<>(Arrays.asList(
                new ItemPedido(item, 1L),
                new ItemPedido(item2, 2L)
        )));

        pedidoService.salvarPedido(
                new Pedido(cliente, new Date()),
                new HashSet<>(Arrays.asList(
                        new ItemPedido(item3, 1L),
                        new ItemPedido(item4, 1L)
                )));

        List<Pedido> pedidos = pedidoRepository.findByClienteAndItem("Gabriel", "cabo usb");

        Assertions.assertEquals(1, pedidos.size());
        Assertions.assertEquals(1600.00, pedidos.get(0).getValorTotal());

    }

}
