package br.com.sobraboa.dao;

import br.com.sobraboa.factory.ConnectionFactory;
import br.com.sobraboa.model.ItemPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemPedidoDao implements IItemPedidoDao {
    private Connection conexao;

    public ItemPedidoDao() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public ItemPedido cadastrar(ItemPedido item) {
        String query = "INSERT INTO ITEM_PEDIDO (id_pedido, id_oferta, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, item.getIdPedido());
            stmt.setInt(2, item.getIdOferta());
            stmt.setInt(3, item.getQuantidade());
            stmt.setBigDecimal(4, item.getPrecoUnitario());
            stmt.setBigDecimal(5, item.getSubtotal());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                item.setIdItem(rs.getInt(1));
            }
            return item;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao cadastrar item do pedido: " + erro.getMessage());
        }
    }

    @Override
    public ArrayList<ItemPedido> consultarPorPedido(int idPedido) {
        ArrayList<ItemPedido> lista = new ArrayList<>();
        String query = "SELECT * FROM ITEM_PEDIDO WHERE id_pedido = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, idPedido);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ItemPedido item = new ItemPedido();
                item.setIdItem(rs.getInt("id_item"));
                item.setIdPedido(rs.getInt("id_pedido"));
                item.setIdOferta(rs.getInt("id_oferta"));
                item.setQuantidade(rs.getInt("quantidade"));
                item.setPrecoUnitario(rs.getBigDecimal("preco_unitario"));
                item.setSubtotal(rs.getBigDecimal("subtotal"));
                lista.add(item);
            }
            return lista;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao consultar itens do pedido: " + erro.getMessage());
        }
    }
}