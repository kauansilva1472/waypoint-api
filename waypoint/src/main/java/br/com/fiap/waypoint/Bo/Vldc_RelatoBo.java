package br.com.fiap.waypoint.Bo;

import br.com.fiap.waypoint.dao.Vldc_RelatoDao;
import br.com.fiap.waypoint.entities.Vldc_Relato;
import java.sql.SQLException;
import java.util.ArrayList;

public class Vldc_RelatoBo {

    // Selecionar
    public ArrayList<Vldc_Relato> SelecionarBo(Vldc_Relato vldc_Relato) throws SQLException, ClassNotFoundException {

        Vldc_RelatoDao vldc_RelatoDao = new Vldc_RelatoDao();

        try {
            return vldc_RelatoDao.Selecionar(vldc_Relato);
        } finally {
            vldc_RelatoDao.fechar();
        }
    }

    // Inserir
    public String InserirBo(Vldc_Relato vldc_Relato) throws SQLException, ClassNotFoundException {

        validarDados(vldc_Relato);

        Vldc_RelatoDao vldc_RelatoDao = new Vldc_RelatoDao();

        try {
            return vldc_RelatoDao.inserir(vldc_Relato);
        } finally {
            vldc_RelatoDao.fechar();
        }
    }

    // Atualizar
    public String AtualizarBo(Vldc_Relato vldc_Relato) throws SQLException, ClassNotFoundException {

        validarDados(vldc_Relato);

        Vldc_RelatoDao vldc_RelatoDao = new Vldc_RelatoDao();

        try {
            return vldc_RelatoDao.Atualizar(vldc_Relato);
        } finally {
            vldc_RelatoDao.fechar();
        }
    }

    // Deletar pelo ID
    public String deletarBo(long id) throws SQLException, ClassNotFoundException {
        Vldc_RelatoDao vldc_RelatoDao = new Vldc_RelatoDao();
        try {
            return vldc_RelatoDao.deletar(id);
        } finally {
            vldc_RelatoDao.fechar();
        }
    }

    // Buscar por ID
    public Vldc_Relato buscarPorid(long id) throws SQLException, ClassNotFoundException {
        Vldc_Relato vldc_Relato = new Vldc_Relato();
        vldc_Relato.setIdVoto(id);
        ArrayList<Vldc_Relato> resultado = SelecionarBo(vldc_Relato);
        if (resultado.isEmpty()) {
            return null;
        }
        return resultado.get(0);
    }

    // Confere os dados antes de abrir uma conexao com o banco.
    public void validarDados(Vldc_Relato vldc_Relato) {
        if (vldc_Relato == null) {
            throw new IllegalArgumentException("Informe os dados de Vldc_Relato.");
        }
        if (vldc_Relato.getVoto() == null || vldc_Relato.getVoto().isBlank() || vldc_Relato.getVoto().length() > 1) {
            throw new IllegalArgumentException("Voto: preencha de 1 a 1 caracteres.");
        }
        if (!"s".equals(vldc_Relato.getVoto()) && !"n".equals(vldc_Relato.getVoto())) {
            throw new IllegalArgumentException("Voto deve ser s ou n.");
        }
        if (vldc_Relato.getUsuario() == null || vldc_Relato.getUsuario().getId() == null || vldc_Relato.getUsuario().getId() <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para Usuario.");
        }
        if (vldc_Relato.getRelato() == null || vldc_Relato.getRelato().getIdRelato() == null || vldc_Relato.getRelato().getIdRelato() <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para Relato.");
        }
    }
}
