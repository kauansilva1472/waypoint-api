package br.com.fiap.waypoint.dao;

import br.com.fiap.waypoint.entities.Vldc_Relato;
import br.com.fiap.waypoint.entities.Relato;
import br.com.fiap.waypoint.entities.Usuario;
import br.com.fiap.waypoint.conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Vldc_RelatoDao {

    Connection MinhaConexao;

    public Vldc_RelatoDao() throws SQLException, ClassNotFoundException {
        this.MinhaConexao = new Conexao().conexao();
    }

    // INSERIR
    public String inserir(Vldc_Relato validacao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "INSERT INTO TB_VLDC_RELATO (voto, TB_USUARIO_id_usuario, TB_RELATO_id) " +
                "VALUES (?, ?, ?)", new String[] {"ID_VOTO"});
        stmt.setString(1, validacao.getVoto());
        stmt.setLong(2, validacao.getUsuario().getId());
        stmt.setLong(3, validacao.getRelato().getIdRelato());
        stmt.executeUpdate();

        // Recupera o ID gerado pelo Oracle.
        ResultSet ids = stmt.getGeneratedKeys();
        if (ids.next()) {
            validacao.setIdVoto(ids.getLong(1));
        } else {
            ids.close();
            stmt.close();
            throw new SQLException("O Oracle nao retornou o ID gerado.");
        }
        ids.close();
        stmt.close();

        return "Validacao: cadastro inserido com sucesso!";
    }

    // ATUALIZAR
    public String Atualizar(Vldc_Relato validacao) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "UPDATE TB_VLDC_RELATO SET voto=?, TB_USUARIO_id_usuario=?, TB_RELATO_id=? " +
                "WHERE id_voto=?");
        stmt.setString(1, validacao.getVoto());
        stmt.setLong(2, validacao.getUsuario().getId());
        stmt.setLong(3, validacao.getRelato().getIdRelato());
        stmt.setLong(4, validacao.getIdVoto());
        int quantidade = stmt.executeUpdate();
        stmt.close();

        if (quantidade == 0) return "Validacao: ID nao encontrado.";
        return "Validacao: cadastro atualizado com sucesso!";
    }

    // SELECIONAR
    public ArrayList<Vldc_Relato> Selecionar(Vldc_Relato validacao) throws SQLException {
        ArrayList<Vldc_Relato> lista = new ArrayList<>();
        String sql = "SELECT id_voto, voto, TB_USUARIO_id_usuario, TB_RELATO_id FROM TB_VLDC_RELATO";
        if (validacao.getIdVoto() != null) {
            sql += " WHERE id_voto=?";
        }
        PreparedStatement stmt = MinhaConexao.prepareStatement(sql);
        if (validacao.getIdVoto() != null) {
            stmt.setLong(1, validacao.getIdVoto());
        }
        ResultSet kk = stmt.executeQuery();

        while (kk.next()) {
            Vldc_Relato encontrado = new Vldc_Relato();
            encontrado.setIdVoto(kk.getLong("id_voto"));
            encontrado.setVoto(kk.getString("voto"));

            // As referencias abaixo carregam somente os IDs das chaves estrangeiras.
            Usuario usuario = new Usuario();
            usuario.setId(kk.getLong("TB_USUARIO_id_usuario"));
            encontrado.setUsuario(usuario);
            Relato relato = new Relato();
            relato.setIdRelato(kk.getLong("TB_RELATO_id"));
            encontrado.setRelato(relato);

            lista.add(encontrado);
        }
        kk.close();
        stmt.close();

        return lista;
    }

    // Excluir pelo ID
    public String deletar(long id) throws SQLException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "DELETE FROM TB_VLDC_RELATO WHERE id_voto=?");
        stmt.setLong(1, id);
        int quantidade = stmt.executeUpdate();
        stmt.close();
        if (quantidade == 0) return "Validacao: ID nao encontrado.";
        return "Validacao: cadastro excluido com sucesso!";
    }

    // Chame ao terminar de usar este DAO.
    public void fechar() throws SQLException {
        MinhaConexao.close();
    }
}
