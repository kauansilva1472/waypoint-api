package br.com.fiap.waypoint.dao;

import br.com.fiap.waypoint.entities.Trecho;
import br.com.fiap.waypoint.conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TrechoDao {

    Connection MinhaConexao;

    public TrechoDao() throws SQLException, ClassNotFoundException {
        this.MinhaConexao = new Conexao().conexao();
    }

    // INSERIR
    public String inserir(Trecho trecho) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "INSERT INTO TB_TRECHO (logradouro, bairro, latitude, longitude) " +
                "VALUES (?, ?, ?, ?)", new String[] {"ID_TRECHO"});
        stmt.setString(1, trecho.getLogradouro());
        stmt.setString(2, trecho.getBairro());
        stmt.setDouble(3, trecho.getLatitude());
        stmt.setDouble(4, trecho.getLongitude());
        stmt.executeUpdate();

        // Recupera o ID gerado pelo Oracle.
        ResultSet ids = stmt.getGeneratedKeys();
        if (ids.next()) {
            trecho.setIdTrecho(ids.getLong(1));
        } else {
            ids.close();
            stmt.close();
            throw new SQLException("O Oracle nao retornou o ID gerado.");
        }
        ids.close();
        stmt.close();

        return "Trecho: cadastro inserido com sucesso!";
    }

    // ATUALIZAR PELO ID
    public String Atualizar(Trecho trecho) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "UPDATE TB_TRECHO SET logradouro=?, bairro=?, latitude=?, longitude=? " +
                "WHERE id_trecho=?");
        stmt.setString(1, trecho.getLogradouro());
        stmt.setString(2, trecho.getBairro());
        stmt.setDouble(3, trecho.getLatitude());
        stmt.setDouble(4, trecho.getLongitude());
        stmt.setLong(5, trecho.getIdTrecho());
        int quantidade = stmt.executeUpdate();
        stmt.close();

        if (quantidade == 0) return "Trecho: ID nao encontrado.";
        return "Trecho: cadastro atualizado com sucesso!";
    }

    // SELECIONAR PELO ID: preencha o ID do objeto antes de chamar.
    public ArrayList<Trecho> Selecionar(Trecho trecho) throws SQLException {
        ArrayList<Trecho> lista = new ArrayList<>();
        String sql = "SELECT id_trecho, logradouro, bairro, latitude, longitude FROM TB_TRECHO";
        if (trecho.getIdTrecho() != null) {
            sql += " WHERE id_trecho=?";
        }

        PreparedStatement stmt = MinhaConexao.prepareStatement(sql);
        if (trecho.getIdTrecho() != null) {
            stmt.setLong(1, trecho.getIdTrecho());
        }
        ResultSet kk = stmt.executeQuery();

        while (kk.next()) {
            Trecho encontrado = new Trecho();
            encontrado.setIdTrecho(kk.getLong("id_trecho"));
            encontrado.setLogradouro(kk.getString("logradouro"));
            encontrado.setBairro(kk.getString("bairro"));
            encontrado.setLatitude(kk.getDouble("latitude"));
            encontrado.setLongitude(kk.getDouble("longitude"));

            lista.add(encontrado);
        }
        kk.close();
        stmt.close();

        return lista;
    }

    // Excluir pelo ID; o Oracle impede exclusao de registros com vinculos.
    public String deletar(long id) throws SQLException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "DELETE FROM TB_TRECHO WHERE id_trecho=?");
        stmt.setLong(1, id);
        int quantidade = stmt.executeUpdate();
        stmt.close();

        if (quantidade == 0) return "Trecho: ID nao encontrado.";
        return "Trecho: cadastro excluido com sucesso!";
    }

    // Chame ao terminar de usar este DAO.
    public void fechar() throws SQLException {
        MinhaConexao.close();
    }
}
