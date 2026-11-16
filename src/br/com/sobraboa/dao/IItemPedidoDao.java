package br.com.sobraboa.dao;

import br.com.sobraboa.model.ItemPedido;
import java.util.ArrayList;

public interface IItemPedidoDao {
    public ItemPedido cadastrar(ItemPedido item);
    public ArrayList<ItemPedido> consultarPorPedido(int idPedido);
}