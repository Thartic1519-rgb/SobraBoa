package br.com.sobraboa.dao;

import br.com.sobraboa.model.Estabelecimento;
import java.util.ArrayList;

public interface IEstabelecimentoDao {
    public Estabelecimento cadastrar(Estabelecimento est);
    public ArrayList<Estabelecimento> consultar();
    public Estabelecimento consultar(int id);
    public boolean alterar(Estabelecimento est, int id);
    public boolean excluir(int id);
}