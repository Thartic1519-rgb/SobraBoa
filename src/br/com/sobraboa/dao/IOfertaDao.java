package br.com.sobraboa.dao;

import br.com.sobraboa.model.Oferta;
import java.util.ArrayList;

public interface IOfertaDao {
    public Oferta cadastrar(Oferta oferta);
    public ArrayList<Oferta> consultar();
    public Oferta consultar(int id);
    public boolean alterar(Oferta oferta, int id);
    public boolean excluir(int id);
}