package br.com.sobraboa.dao;

import br.com.sobraboa.factory.ConnectionFactory;
import br.com.sobraboa.model.Estabelecimento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstabelecimentoDao implements IEstabelecimentoDao {
    private Connection conexao;

    public EstabelecimentoDao() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public Estabelecimento cadastrar(Estabelecimento est) {
        String query = "INSERT INTO ESTABELECIMENTO (id_usuario, nome, descricao, endereco, cidade, telefone, horario_funcionamento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, est.getIdUsuario());
            stmt.setString(2, est.getNome());
            stmt.setString(3, est.getDescricao());
            stmt.setString(4, est.getEndereco());
            stmt.setString(5, est.getCidade());
            stmt.setString(6, est.getTelefone());
            stmt.setString(7, est.getHorarioFuncionamento());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                est.setIdEstabelecimento(rs.getInt(1));
            }
            return est;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao cadastrar estabelecimento: " + erro.getMessage());
        }
    }

    @Override
    public ArrayList<Estabelecimento> consultar() {
        ArrayList<Estabelecimento> lista = new ArrayList<>();
        String query = "SELECT * FROM ESTABELECIMENTO";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Estabelecimento e = new Estabelecimento();
                e.setIdEstabelecimento(rs.getInt("id_estabelecimento"));
                e.setIdUsuario(rs.getInt("id_usuario"));
                e.setNome(rs.getString("nome"));
                e.setDescricao(rs.getString("descricao"));
                e.setEndereco(rs.getString("endereco"));
                e.setCidade(rs.getString("cidade"));
                e.setTelefone(rs.getString("telefone"));
                e.setHorarioFuncionamento(rs.getString("horario_funcionamento"));
                lista.add(e);
            }
            return lista;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao consultar estabelecimentos: " + erro.getMessage());
        }
    }

    @Override
    public Estabelecimento consultar(int id) {
        String query = "SELECT * FROM ESTABELECIMENTO WHERE id_estabelecimento = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Estabelecimento e = new Estabelecimento();
            if (rs.next()) {
                e.setIdEstabelecimento(rs.getInt("id_estabelecimento"));
                e.setIdUsuario(rs.getInt("id_usuario"));
                e.setNome(rs.getString("nome"));
                e.setDescricao(rs.getString("descricao"));
                e.setEndereco(rs.getString("endereco"));
                e.setCidade(rs.getString("cidade"));
                e.setTelefone(rs.getString("telefone"));
                e.setHorarioFuncionamento(rs.getString("horario_funcionamento"));
            }
            return e;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao buscar estabelecimento: " + erro.getMessage());
        }
    }

    @Override
    public boolean alterar(Estabelecimento est, int id) {
        String query = "UPDATE ESTABELECIMENTO SET nome = ?, descricao = ?, endereco = ?, cidade = ?, telefone = ?, horario_funcionamento = ? WHERE id_estabelecimento = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, est.getNome());
            stmt.setString(2, est.getDescricao());
            stmt.setString(3, est.getEndereco());
            stmt.setString(4, est.getCidade());
            stmt.setString(5, est.getTelefone());
            stmt.setString(6, est.getHorarioFuncionamento());
            stmt.setInt(7, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao alterar estabelecimento: " + erro.getMessage());
        }
    }

    @Override
    public boolean excluir(int id) {
        String query = "DELETE FROM ESTABELECIMENTO WHERE id_estabelecimento = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao excluir estabelecimento: " + erro.getMessage());
        }
    }
}