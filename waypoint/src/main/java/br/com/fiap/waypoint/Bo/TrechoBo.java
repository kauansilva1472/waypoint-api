package br.com.fiap.waypoint.Bo;

import br.com.fiap.waypoint.dao.TrechoDao;
import br.com.fiap.waypoint.entities.Trecho;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrechoBo {

    // Selecionar
    public ArrayList<Trecho> SelecionarBo(Trecho trecho) throws SQLException, ClassNotFoundException {

        TrechoDao trechoDao = new TrechoDao();

        try {
            return trechoDao.Selecionar(trecho);
        } finally {
            trechoDao.fechar();
        }
    }

    // Inserir
    public String InserirBo(Trecho trecho) throws SQLException, ClassNotFoundException {

        validarDados(trecho);

        TrechoDao trechoDao = new TrechoDao();

        try {
            return trechoDao.inserir(trecho);
        } finally {
            trechoDao.fechar();
        }
    }

    // Atualizar
    public String AtualizarBo(Trecho trecho) throws SQLException, ClassNotFoundException {

        validarDados(trecho);

        TrechoDao trechoDao = new TrechoDao();

        try {
            return trechoDao.Atualizar(trecho);
        } finally {
            trechoDao.fechar();
        }
    }

    // Deletar pelo ID
    public String deletarBo(long id) throws SQLException, ClassNotFoundException {
        TrechoDao trechoDao = new TrechoDao();
        try {
            return trechoDao.deletar(id);
        } finally {
            trechoDao.fechar();
        }
    }

    // Buscar por ID
    public Trecho buscarPorid(long id) throws SQLException, ClassNotFoundException {
        Trecho trecho = new Trecho();
        trecho.setIdTrecho(id);
        ArrayList<Trecho> resultado = SelecionarBo(trecho);
        if (resultado.isEmpty()) {
            return null;
        }
        return resultado.get(0);
    }

    // Confere os dados antes de abrir uma conexao com o banco.
    public void validarDados(Trecho trecho) {
        if (trecho == null) {
            throw new IllegalArgumentException("Informe os dados de Trecho.");
        }
        if (trecho.getLogradouro() == null || trecho.getLogradouro().isBlank() || trecho.getLogradouro().length() > 35) {
            throw new IllegalArgumentException("Logradouro: preencha de 1 a 35 caracteres.");
        }
        if (trecho.getBairro() == null || trecho.getBairro().isBlank() || trecho.getBairro().length() > 25) {
            throw new IllegalArgumentException("Bairro: preencha de 1 a 25 caracteres.");
        }
        if (trecho.getLatitude() == null || !Double.isFinite(trecho.getLatitude()) || trecho.getLatitude() < -90 || trecho.getLatitude() > 90) {
            throw new IllegalArgumentException("Latitude deve estar entre -90 e 90.");
        }
        if (trecho.getLongitude() == null || !Double.isFinite(trecho.getLongitude()) || trecho.getLongitude() < -180 || trecho.getLongitude() > 180) {
            throw new IllegalArgumentException("Longitude deve estar entre -180 e 180.");
        }
    }
}
