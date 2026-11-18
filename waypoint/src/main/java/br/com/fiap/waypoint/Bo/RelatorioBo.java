package br.com.fiap.waypoint.Bo;

import br.com.fiap.waypoint.dao.RelatoDao;
import br.com.fiap.waypoint.entities.Relato;
import java.sql.SQLException;
import java.util.ArrayList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class RelatorioBo {

    // Selecionar
    public ArrayList<Relato> SelecionarBo(Relato relato) throws SQLException, ClassNotFoundException {

        RelatoDao relatoDao = new RelatoDao();

        try {
            return relatoDao.Selecionar(relato);
        } finally {
            relatoDao.fechar();
        }
    }

    // Inserir
    public String InserirBo(Relato relato) throws SQLException, ClassNotFoundException {

        validarDados(relato);

        RelatoDao relatoDao = new RelatoDao();

        try {
            return relatoDao.inserir(relato);
        } finally {
            relatoDao.fechar();
        }
    }

    // Atualizar
    public String AtualizarBo(Relato relato) throws SQLException, ClassNotFoundException {

        validarDados(relato);

        RelatoDao relatoDao = new RelatoDao();

        try {
            return relatoDao.Atualizar(relato);
        } finally {
            relatoDao.fechar();
        }
    }

    // Deletar pelo ID
    public String deletarBo(long id) throws SQLException, ClassNotFoundException {
        RelatoDao relatoDao = new RelatoDao();
        try {
            return relatoDao.deletar(id);
        } finally {
            relatoDao.fechar();
        }
    }

    // Consultar relatos de um usuario
    public ArrayList<Relato> SelecionarPorUsuarioBo(long id) throws SQLException, ClassNotFoundException {
        RelatoDao relatoDao = new RelatoDao();
        try {
            return relatoDao.SelecionarPorUsuario(id);
        } finally {
            relatoDao.fechar();
        }
    }

    // Buscar por ID
    public Relato buscarPorid(long id) throws SQLException, ClassNotFoundException {
        Relato relato = new Relato();
        relato.setIdRelato(id);
        ArrayList<Relato> resultado = SelecionarBo(relato);
        if (resultado.isEmpty()) {
            return null;
        }
        return resultado.get(0);
    }

    // Confere os dados antes de abrir uma conexao com o banco.
    public void validarDados(Relato relato) {
        if (relato == null) {
            throw new IllegalArgumentException("Informe os dados de Relato.");
        }
        if (relato.getDescricao() == null || relato.getDescricao().isBlank() || relato.getDescricao().length() > 100) {
            throw new IllegalArgumentException("Descricao: preencha de 1 a 100 caracteres.");
        }
        if (relato.getDataRelato() == null || relato.getDataRelato().isBlank() || relato.getDataRelato().length() > 10) {
            throw new IllegalArgumentException("Data do relato: preencha de 1 a 10 caracteres.");
        }
        if (relato.getStatus() == null || relato.getStatus().isBlank() || relato.getStatus().length() > 7) {
            throw new IllegalArgumentException("Status: preencha de 1 a 7 caracteres.");
        }
        try {
            LocalDate.parse(relato.getDataRelato(), DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));
        } catch (DateTimeParseException erro) {
            throw new IllegalArgumentException("Informe uma data existente no formato DD/MM/AAAA.");
        }
        if (relato.getUsuario() == null || relato.getUsuario().getId() == null || relato.getUsuario().getId() <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para Usuario.");
        }
        if (relato.getTrecho() == null || relato.getTrecho().getIdTrecho() == null || relato.getTrecho().getIdTrecho() <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para Trecho.");
        }
        if (relato.getCategoria() == null || relato.getCategoria().getIdCategoria() == null || relato.getCategoria().getIdCategoria() <= 0) {
            throw new IllegalArgumentException("Informe um ID positivo para Categoria.");
        }
    }
}
