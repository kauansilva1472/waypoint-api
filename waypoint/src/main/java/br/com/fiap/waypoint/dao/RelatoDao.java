package br.com.fiap.waypoint.dao;

import br.com.fiap.waypoint.entities.Relato;
import br.com.fiap.waypoint.entities.Categoria;
import br.com.fiap.waypoint.entities.Trecho;
import br.com.fiap.waypoint.entities.Usuario;
import br.com.fiap.waypoint.conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RelatoDao {

    Connection MinhaConexao;

    public RelatoDao() throws SQLException, ClassNotFoundException {
        this.MinhaConexao = new Conexao().conexao();
    }

    // INSERIR
    public String inserir(Relato relato) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "INSERT INTO TB_RELATO (descricao, data_relato, status, TB_USUARIO_id_usuario, TB_TRECHO_id_trecho, TB_CATEGORIA_id_categoria) " +
                "VALUES (?, TO_DATE(?, 'DD/MM/YYYY'), ?, ?, ?, ?)", new String[] {"ID_RELATO"});
        stmt.setString(1, relato.getDescricao());
        stmt.setString(2, relato.getDataRelato());
        stmt.setString(3, relato.getStatus());
        stmt.setLong(4, relato.getUsuario().getId());
        stmt.setLong(5, relato.getTrecho().getIdTrecho());
        stmt.setLong(6, relato.getCategoria().getIdCategoria());
        stmt.executeUpdate();

        // Recupera o ID gerado pelo Oracle.
        ResultSet ids = stmt.getGeneratedKeys();
        if (ids.next()) {
            relato.setIdRelato(ids.getLong(1));
        } else {
            ids.close();
            stmt.close();
            throw new SQLException("O Oracle nao retornou o ID gerado.");
        }
        ids.close();
        stmt.close();

        return "Relato: cadastro inserido com sucesso!";
    }

    // ATUALIZAR
    public String Atualizar(Relato relato) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "UPDATE TB_RELATO SET descricao=?, data_relato=TO_DATE(?, 'DD/MM/YYYY'), status=?, TB_USUARIO_id_usuario=?, TB_TRECHO_id_trecho=?, TB_CATEGORIA_id_categoria=? " +
                "WHERE id_relato=?");
        stmt.setString(1, relato.getDescricao());
        stmt.setString(2, relato.getDataRelato());
        stmt.setString(3, relato.getStatus());
        stmt.setLong(4, relato.getUsuario().getId());
        stmt.setLong(5, relato.getTrecho().getIdTrecho());
        stmt.setLong(6, relato.getCategoria().getIdCategoria());
        stmt.setLong(7, relato.getIdRelato());
        int quantidade = stmt.executeUpdate();
        stmt.close();

        if (quantidade == 0) return "Relato: ID nao encontrado.";
        return "Relato: cadastro atualizado com sucesso!";
    }

    // SELECIONAR
    public ArrayList<Relato> Selecionar(Relato relato) throws SQLException {
        ArrayList<Relato> lista = new ArrayList<>();
        String sql = "SELECT id_relato, descricao, TO_CHAR(data_relato, 'DD/MM/YYYY') AS data_relato_texto, status, TB_USUARIO_id_usuario, TB_TRECHO_id_trecho, TB_CATEGORIA_id_categoria FROM TB_RELATO";
        if (relato.getIdRelato() != null) {
            sql += " WHERE id_relato=?";
        }

        PreparedStatement stmt = MinhaConexao.prepareStatement(sql);
        if (relato.getIdRelato() != null) {
            stmt.setLong(1, relato.getIdRelato());
        }
        ResultSet kk = stmt.executeQuery();

        while (kk.next()) {
            Relato encontrado = new Relato();
            encontrado.setIdRelato(kk.getLong("id_relato"));
            encontrado.setDescricao(kk.getString("descricao"));
            encontrado.setDataRelato(kk.getString("data_relato_texto"));
            encontrado.setStatus(kk.getString("status"));

            // As referencias abaixo carregam somente os IDs das chaves estrangeiras.
            Usuario usuario = new Usuario();
            usuario.setId(kk.getLong("TB_USUARIO_id_usuario"));
            encontrado.setUsuario(usuario);
            Trecho trecho = new Trecho();
            trecho.setIdTrecho(kk.getLong("TB_TRECHO_id_trecho"));
            encontrado.setTrecho(trecho);
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(kk.getLong("TB_CATEGORIA_id_categoria"));
            encontrado.setCategoria(categoria);

            lista.add(encontrado);
        }
        kk.close();
        stmt.close();

        return lista;
    }

    // Lista os relatos de um usuario (relacionamento 1:N).
    public ArrayList<Relato> SelecionarPorUsuario(long id) throws SQLException {
        ArrayList<Relato> lista = new ArrayList<>();
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "SELECT id_relato, descricao, TO_CHAR(data_relato, 'DD/MM/YYYY') AS data_relato_texto, status, TB_USUARIO_id_usuario, TB_TRECHO_id_trecho, TB_CATEGORIA_id_categoria FROM TB_RELATO WHERE TB_USUARIO_id_usuario=?");
        stmt.setLong(1, id);
        ResultSet kk = stmt.executeQuery();

        while (kk.next()) {
            Relato encontrado = new Relato();
            encontrado.setIdRelato(kk.getLong("id_relato"));
            encontrado.setDescricao(kk.getString("descricao"));
            encontrado.setDataRelato(kk.getString("data_relato_texto"));
            encontrado.setStatus(kk.getString("status"));

            // As referencias abaixo carregam somente os IDs das chaves estrangeiras.
            Usuario usuario = new Usuario();
            usuario.setId(kk.getLong("TB_USUARIO_id_usuario"));
            encontrado.setUsuario(usuario);
            Trecho trecho = new Trecho();
            trecho.setIdTrecho(kk.getLong("TB_TRECHO_id_trecho"));
            encontrado.setTrecho(trecho);
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(kk.getLong("TB_CATEGORIA_id_categoria"));
            encontrado.setCategoria(categoria);

            lista.add(encontrado);
        }
        kk.close();
        stmt.close();

        return lista;
    }

    // Excluir pelo ID; o Oracle impede exclusao de registros com vinculos.
    public String deletar(long id) throws SQLException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "DELETE FROM TB_RELATO WHERE id_relato=?");
        stmt.setLong(1, id);
        int quantidade = stmt.executeUpdate();
        stmt.close();

        if (quantidade == 0) return "Relato: ID nao encontrado.";
        return "Relato: cadastro excluido com sucesso!";
    }

    // Chame ao terminar de usar este DAO.
    public void fechar() throws SQLException {
        MinhaConexao.close();
    }
}
