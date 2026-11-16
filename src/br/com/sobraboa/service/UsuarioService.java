package br.com.sobraboa.service;

import br.com.sobraboa.dao.UsuarioDao;
import br.com.sobraboa.model.Usuario;
import java.util.ArrayList;

public class UsuarioService {
    private UsuarioDao usuarioDao;

    public UsuarioService() {
        this.usuarioDao = new UsuarioDao();
    }

    public Usuario cadastrar(Usuario usuario) {
        return usuarioDao.cadastrar(usuario);
    }

    public ArrayList<Usuario> consultar() {
        return usuarioDao.consultar();
    }

    public Usuario consultar(int id) {
        return usuarioDao.consultar(id);
    }

    public boolean alterar(Usuario usuario, int id) {
        return usuarioDao.alterar(usuario, id);
    }

    public boolean excluir(int id) {
        return usuarioDao.excluir(id);
    }
}