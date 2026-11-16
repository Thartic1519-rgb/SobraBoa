package br.com.sobraboa.dao;

import br.com.sobraboa.model.Usuario;
import java.util.ArrayList;

public interface IUsuarioDao {
    public ArrayList<Usuario> consultar();
    public Usuario consultar(int id);
    public Usuario cadastrar(Usuario usuario);
    public boolean alterar(Usuario usuario, int id);
    public boolean excluir(int id);
}