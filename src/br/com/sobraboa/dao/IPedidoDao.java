package br.com.sobraboa.dao;

import br.com.sobraboa.model.Pedido;
import java.util.ArrayList;

public interface IPedidoDao {
    public Pedido cadastrar(Pedido pedido);
    public ArrayList<Pedido> consultar();
    public Pedido consultar(int id);
    public boolean alterarStatus(int idPedido, String novoStatus);
    public boolean excluir(int id);
}