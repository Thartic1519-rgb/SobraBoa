package br.com.sobraboa.dao;

import br.com.sobraboa.factory.ConnectionFactory;
import br.com.sobraboa.model.Oferta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class OfertaDao implements IOfertaDao {
    private Connection conexao;

    public OfertaDao() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    @Override
    public Oferta cadastrar(Oferta oferta) {
        String query = "INSERT INTO OFERTA (id_estabelecimento, id_categoria, titulo, descricao, tipo_oferta, preco_original, preco_promocional, quantidade_disponivel, validade, horario_retirada, imagem, ativa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, oferta.getIdEstabelecimento());
            stmt.setInt(2, oferta.getIdCategoria());
            stmt.setString(3, oferta.getTitulo());
            stmt.setString(4, oferta.getDescricao());
            stmt.setString(5, oferta.getTipoOferta());
            stmt.setBigDecimal(6, oferta.getPrecoOriginal());
            stmt.setBigDecimal(7, oferta.getPrecoPromocional());
            stmt.setInt(8, oferta.getQuantidadeDisponivel());
            stmt.setTimestamp(9, Timestamp.valueOf(oferta.getValidade()));
            stmt.setString(10, oferta.getHorarioRetirada());
            stmt.setString(11, oferta.getImagem());
            stmt.setBoolean(12, oferta.isAtiva());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                oferta.setIdOferta(rs.getInt(1));
            }
            return oferta;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao cadastrar oferta: " + erro.getMessage());
        }
    }

    @Override
    public ArrayList<Oferta> consultar() {
        ArrayList<Oferta> lista = new ArrayList<>();
        String query = "SELECT * FROM OFERTA";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Oferta o = new Oferta();
                o.setIdOferta(rs.getInt("id_oferta"));
                o.setIdEstabelecimento(rs.getInt("id_estabelecimento"));
                o.setIdCategoria(rs.getInt("id_categoria"));
                o.setTitulo(rs.getString("titulo"));
                o.setDescricao(rs.getString("descricao"));
                o.setTipoOferta(rs.getString("tipo_oferta"));
                o.setPrecoOriginal(rs.getBigDecimal("preco_original"));
                o.setPrecoPromocional(rs.getBigDecimal("preco_promocional"));
                o.setQuantidadeDisponivel(rs.getInt("quantidade_disponivel"));
                o.setValidade(rs.getTimestamp("validade").toLocalDateTime());
                o.setHorarioRetirada(rs.getString("horario_retirada"));
                o.setImagem(rs.getString("imagem"));
                o.setAtiva(rs.getBoolean("ativa"));
                lista.add(o);
            }
            return lista;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao consultar ofertas: " + erro.getMessage());
        }
    }

    @Override
    public Oferta consultar(int id) {
        String query = "SELECT * FROM OFERTA WHERE id_oferta = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Oferta o = new Oferta();
            if (rs.next()) {
                o.setIdOferta(rs.getInt("id_oferta"));
                o.setIdEstabelecimento(rs.getInt("id_estabelecimento"));
                o.setIdCategoria(rs.getInt("id_categoria"));
                o.setTitulo(rs.getString("titulo"));
                o.setDescricao(rs.getString("descricao"));
                o.setTipoOferta(rs.getString("tipo_oferta"));
                o.setPrecoOriginal(rs.getBigDecimal("preco_original"));
                o.setPrecoPromocional(rs.getBigDecimal("preco_promocional"));
                o.setQuantidadeDisponivel(rs.getInt("quantidade_disponivel"));
                o.setValidade(rs.getTimestamp("validade").toLocalDateTime());
                o.setHorarioRetirada(rs.getString("horario_retirada"));
                o.setImagem(rs.getString("imagem"));
                o.setAtiva(rs.getBoolean("ativa"));
            }
            return o;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao buscar oferta: " + erro.getMessage());
        }
    }

    @Override
    public boolean alterar(Oferta oferta, int id) {
        String query = "UPDATE OFERTA SET titulo = ?, descricao = ?, tipo_oferta = ?, preco_original = ?, preco_promocional = ?, quantidade_disponivel = ?, validade = ?, horario_retirada = ?, imagem = ?, ativa = ? WHERE id_oferta = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setString(1, oferta.getTitulo());
            stmt.setString(2, oferta.getDescricao());
            stmt.setString(3, oferta.getTipoOferta());
            stmt.setBigDecimal(4, oferta.getPrecoOriginal());
            stmt.setBigDecimal(5, oferta.getPrecoPromocional());
            stmt.setInt(6, oferta.getQuantidadeDisponivel());
            stmt.setTimestamp(7, Timestamp.valueOf(oferta.getValidade()));
            stmt.setString(8, oferta.getHorarioRetirada());
            stmt.setString(9, oferta.getImagem());
            stmt.setBoolean(10, oferta.isAtiva());
            stmt.setInt(11, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao alterar oferta: " + erro.getMessage());
        }
    }

    @Override
    public boolean excluir(int id) {
        String query = "DELETE FROM OFERTA WHERE id_oferta = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(query);
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException erro) {
            throw new RuntimeException("Erro ao excluir oferta: " + erro.getMessage());
        }
    }
}