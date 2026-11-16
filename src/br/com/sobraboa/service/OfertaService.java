package br.com.sobraboa.service;

import br.com.sobraboa.dao.OfertaDao;
import br.com.sobraboa.model.Oferta;
import java.util.ArrayList;

public class OfertaService {
    private OfertaDao ofertaDao;

    public OfertaService() {
        this.ofertaDao = new OfertaDao();
    }

    public Oferta cadastrar(Oferta oferta) {
        return ofertaDao.cadastrar(oferta);
    }

    public ArrayList<Oferta> consultar() {
        return ofertaDao.consultar();
    }

    public Oferta consultar(int id) {
        return ofertaDao.consultar(id);
    }

    public boolean alterar(Oferta oferta, int id) {
        return ofertaDao.alterar(oferta, id);
    }

    public boolean excluir(int id) {
        return ofertaDao.excluir(id);
    }
}