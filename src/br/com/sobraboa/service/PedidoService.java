package br.com.sobraboa.service;

import br.com.sobraboa.dao.ItemPedidoDao;
import br.com.sobraboa.dao.PedidoDao;
import br.com.sobraboa.model.ItemPedido;
import br.com.sobraboa.model.Pedido;
import java.util.ArrayList;

public class PedidoService {
    private PedidoDao pedidoDao;
    private ItemPedidoDao itemPedidoDao;

    public PedidoService() {
        this.pedidoDao = new PedidoDao();
        this.itemPedidoDao = new ItemPedidoDao();
    }

    public Pedido cadastrarPedido(Pedido pedido, ArrayList<ItemPedido> itens) {
        Pedido novoPedido = pedidoDao.cadastrar(pedido);
        
        for (ItemPedido item : itens) {
            item.setIdPedido(novoPedido.getIdPedido());
            itemPedidoDao.cadastrar(item);
        }
        
        return novoPedido;
    }

    public ArrayList<Pedido> consultar() {
        return pedidoDao.consultar();
    }

    public Pedido consultar(int id) {
        return pedidoDao.consultar(id);
    }

    public boolean alterarStatus(int idPedido, String novoStatus) {
        return pedidoDao.alterarStatus(idPedido, novoStatus);
    }

    public ArrayList<ItemPedido> consultarItens(int idPedido) {
        return itemPedidoDao.consultarPorPedido(idPedido);
    }
}