package br.com.sobraboa.dao;

import br.com.sobraboa.factory.ConnectionFactory;
import br.com.sobraboa.model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class PedidoDao implements IPedidoDao {
    private Connection conexao;

    public PedidoDao() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public Pedido cadastrar(Pedido pedido) {
        String query = "INSERT INTO PEDIDO (id_usuario, id_estabelecimento, data_pedido, valor_total, status) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, pedido.getIdUsuario());
            stmt.setInt(2, pedido.getIdEstabelecimento());
            stmt.setTimestamp(3, Timestamp.valueOf(pedido.getDataPedido()));
            stmt.setBigDecimal(4, pedido.getValorTotal());
            stmt.setString(5, pedido.getStatus());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                pedido.setIdPedido(rs.getInt(1));
            }
            return pedido;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao cadastrar pedido: " + erro.getMessage());
        }
    }

    @Override
    public ArrayList<Pedido> consultar() {
        ArrayList<Pedido> lista = new ArrayList<>();
        String query = "SELECT * FROM PEDIDO";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setIdPedido(rs.getInt("id_pedido"));
                p.setIdUsuario(rs.getInt("id_usuario"));
                p.setIdEstabelecimento(rs.getInt("id_estabelecimento"));
                p.setDataPedido(rs.getTimestamp("data_pedido").toLocalDateTime());
                p.setValorTotal(rs.getBigDecimal("valor_total"));
                p.setStatus(rs.getString("status"));
                lista.add(p);
            }
            return lista;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao consultar pedidos: " + erro.getMessage());
        }
    }

    @Override
    public Pedido consultar(int id) {
        String query = "SELECT * FROM PEDIDO WHERE id_pedido = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Pedido p = new Pedido();
            if (rs.next()) {
                p.setIdPedido(rs.getInt("id_pedido"));
                p.setIdUsuario(rs.getInt("id_usuario"));
                p.setIdEstabelecimento(rs.getInt("id_estabelecimento"));
                p.setDataPedido(rs.getTimestamp("data_pedido").toLocalDateTime());
                p.setValorTotal(rs.getBigDecimal("valor_total"));
                p.setStatus(rs.getString("status"));
            }
            return p;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao buscar pedido: " + erro.getMessage());
        }
    }

    @Override
    public boolean alterarStatus(int idPedido, String novoStatus) {
        String query = "UPDATE PEDIDO SET status = ? WHERE id_pedido = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, novoStatus);
            stmt.setInt(2, idPedido);
            return stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao atualizar status do pedido: " + erro.getMessage());
        }
    }

    @Override
    public boolean excluir(int id) {
        String query = "DELETE FROM PEDIDO WHERE id_pedido = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao excluir pedido: " + erro.getMessage());
        }
    }
}