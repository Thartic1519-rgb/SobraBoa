package br.com.sobraboa.service;

import br.com.sobraboa.dao.EstabelecimentoDao;
import br.com.sobraboa.model.Estabelecimento;
import java.util.ArrayList;

public class EstabelecimentoService {
    private EstabelecimentoDao estDao;

    public EstabelecimentoService() {
        this.estDao = new EstabelecimentoDao();
    }

    public Estabelecimento cadastrar(Estabelecimento est) {
        return estDao.cadastrar(est);
    }

    public ArrayList<Estabelecimento> consultar() {
        return estDao.consultar();
    }

    public Estabelecimento consultar(int id) {
        return estDao.consultar(id);
    }

    public boolean alterar(Estabelecimento est, int id) {
        return estDao.alterar(est, id);
    }

    public boolean excluir(int id) {
        return estDao.excluir(id);
    }
}