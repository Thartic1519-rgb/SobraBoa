package br.com.sobraboa.dao;

import br.com.sobraboa.factory.ConnectionFactory;
import br.com.sobraboa.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDao implements IUsuarioDao {
    private Connection conexao;

    public UsuarioDao() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {
        String query = "INSERT INTO USUARIO (nome, email, senha, telefone) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTelefone());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
            }
            return usuario;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao cadastrar usuário: " + erro.getMessage());
        }
    }

    @Override
    public ArrayList<Usuario> consultar() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String query = "SELECT * FROM USUARIO";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setTelefone(rs.getString("telefone"));
                lista.add(u);
            }
            return lista;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao consultar usuários: " + erro.getMessage());
        }
    }

    @Override
    public Usuario consultar(int id) {
        String query = "SELECT * FROM USUARIO WHERE id_usuario = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Usuario u = new Usuario();
            if (rs.next()) {
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setTelefone(rs.getString("telefone"));
            }
            return u;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao buscar usuário: " + erro.getMessage());
        }
    }

    @Override
    public boolean alterar(Usuario usuario, int id) {
        String query = "UPDATE USUARIO SET nome = ?, email = ?, senha = ?, telefone = ? WHERE id_usuario = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.setString(4, usuario.getTelefone());
            stmt.setInt(5, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao alterar usuário: " + erro.getMessage());
        }
    }

    @Override
    public boolean excluir(int id) {
        String query = "DELETE FROM USUARIO WHERE id_usuario = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao excluir usuário: " + erro.getMessage());
        }
    }
}