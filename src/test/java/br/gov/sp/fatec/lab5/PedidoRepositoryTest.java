package br.gov.sp.fatec.lab5;

import br.gov.sp.fatec.lab5.entity.ClientePF;
import br.gov.sp.fatec.lab5.entity.Item;
import br.gov.sp.fatec.lab5.entity.ItemPedido;
import br.gov.sp.fatec.lab5.entity.Pedido;
import br.gov.sp.fatec.lab5.repository.ClienteRepository;
import br.gov.sp.fatec.lab5.repository.ItemPedidoRepository;
import br.gov.sp.fatec.lab5.repository.ItemRepository;
import br.gov.sp.fatec.lab5.repository.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@SpringBootTest
public class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;


    @Test
    public void deveSalvarUmPedido(){
        ClientePF cliente = new ClientePF("361.435.678-75");
        cliente.setNome("Gabriel");
        cliente.setEndereco("Rua Gisele Martins");

        clienteRepository.save(cliente);

        Pedido pedido = new Pedido(cliente, new Date());

        Item item = new Item("Celular", 1500.00, null);
        Item item2 = new Item("cabo usb", 50.0, null);

        ItemPedido itemPedido1 = new ItemPedido(item, 1L);
        ItemPedido itemPedido2 = new ItemPedido(item2, 2L);

        itemRepository.saveAll(Arrays.asList(item, item2));
        pedidoRepository.save(pedido);

        pedido.addItemPedido(itemPedido1);
        pedido.addItemPedido(itemPedido2);

        itemPedido1.setPedido(pedido);
        itemPedido2.setPedido(pedido);

        itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2));

        Assertions.assertEquals(1600, pedido.getValorTotal());
        Assertions.assertNotNull(pedido.getId());

        itemPedidoRepository.deleteAll();
        itemRepository.deleteAll();
        pedidoRepository.deleteAll();
        clienteRepository.deleteAll();
    }

    @Test
    public void deveBuscarPedidosDoClienteComItem(){
        ClientePF cliente = new ClientePF("123.456.789-10");
        cliente.setNome("Gabriel");
        cliente.setEndereco("Rua Gisele Martins");

        clienteRepository.save(cliente);

        Pedido pedido = new Pedido(cliente, new Date(Date.UTC(120, Calendar.DECEMBER, 1, 0, 0, 0)));
        Item item = new Item("Celular", 1500.00, null);
        Item item2 = new Item("cabo usb", 50.0, null);
        ItemPedido itemPedido1 = new ItemPedido(item, 1L);
        ItemPedido itemPedido2 = new ItemPedido(item2, 2L);
        itemRepository.saveAll(Arrays.asList(item, item2));
        pedidoRepository.save(pedido);
        pedido.addItemPedido(itemPedido1);
        pedido.addItemPedido(itemPedido2);
        itemPedido1.setPedido(pedido);
        itemPedido2.setPedido(pedido);
        itemPedidoRepository.saveAll(Arrays.asList(itemPedido1, itemPedido2));

        Pedido pedido2 = new Pedido(cliente, new Date());
        Item item3 = new Item("Notebook", 1600.00, null);
        Item item4 = new Item("nvme", 500.0, null);
        ItemPedido itemPedido3 = new ItemPedido(item3, 1L);
        ItemPedido itemPedido4 = new ItemPedido(item4, 1L);
        itemRepository.saveAll(Arrays.asList(item3, item4));
        pedidoRepository.save(pedido2);
        pedido.addItemPedido(itemPedido3);
        pedido.addItemPedido(itemPedido4);
        itemPedido3.setPedido(pedido);
        itemPedido4.setPedido(pedido);
        itemPedidoRepository.saveAll(Arrays.asList(itemPedido3, itemPedido4));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Date dataInicial = null;
        Date dataFinal = null;
        try {
            dataInicial = sdf.parse("26/08/2020");
            dataFinal = sdf.parse("28/08/2020");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Iterable<Pedido> pedidos =
                pedidoRepository.findPedidosByCliente_NomeAndDataDaCompraBetween(
                        cliente.getNome(), dataInicial, dataFinal);

        Assertions.assertEquals(1, ((Collection) pedidos).size());
    }
}
