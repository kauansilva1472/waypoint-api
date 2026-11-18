package br.com.fiap.waypoint.Bo;

import br.com.fiap.waypoint.dao.RotaDao;
import br.com.fiap.waypoint.entities.Rota;
import java.sql.SQLException;
import java.util.ArrayList;

public class RotaBo {

    // Selecionar
    public ArrayList<Rota> SelecionarBo(Rota rota) throws SQLException, ClassNotFoundException {

        RotaDao rotaDao = new RotaDao();

        try {
            return rotaDao.Selecionar(rota);
        } finally {
            rotaDao.fechar();
        }
    }

    // Inserir
    public String InserirBo(Rota rota) throws SQLException, ClassNotFoundException {

        validarDados(rota);

        RotaDao rotaDao = new RotaDao();

        try {
            return rotaDao.inserir(rota);
        } finally {
            rotaDao.fechar();
        }
    }

    // Atualizar
    public String AtualizarBo(Rota rota) throws SQLException, ClassNotFoundException {

        validarDados(rota);

        RotaDao rotaDao = new RotaDao();

        try {
            return rotaDao.Atualizar(rota);
        } finally {
            rotaDao.fechar();
        }
    }

    // Deletar pelo ID
    public String deletarBo(long id) throws SQLException, ClassNotFoundException {
        RotaDao rotaDao = new RotaDao();
        try {
            return rotaDao.deletar(id);
        } finally {
            rotaDao.fechar();
        }
    }

    // Buscar por ID
    public Rota buscarPorid(long id) throws SQLException, ClassNotFoundException {
        Rota rota = new Rota();
        rota.setIdRota(id);
        ArrayList<Rota> resultado = SelecionarBo(rota);
        if (resultado.isEmpty()) {
            return null;
        }
        return resultado.get(0);
    }

    // Confere os dados antes de abrir uma conexao com o banco.
    public void validarDados(Rota rota) {
        if (rota == null) {
            throw new IllegalArgumentException("Informe os dados de Rota.");
        }
        if (rota.getDistancia() == null || !Double.isFinite(rota.getDistancia()) || rota.getDistancia() < 0 || rota.getDistancia() > 99.99) {
            throw new IllegalArgumentException("Distancia deve estar entre 0 e 99.99.");
        }
        if (rota.getUsuario() == null || rota.getUsuario().getId() == null || rota.getUsuario().getId() <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para Usuario.");
        }
        if (rota.getTrechoInicio() == null || rota.getTrechoInicio().getIdTrecho() == null || rota.getTrechoInicio().getIdTrecho() <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para TrechoInicio.");
        }
        if (rota.getTrechoFinal() == null || rota.getTrechoFinal().getIdTrecho() == null || rota.getTrechoFinal().getIdTrecho() <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para TrechoFinal.");
        }
    }
}
