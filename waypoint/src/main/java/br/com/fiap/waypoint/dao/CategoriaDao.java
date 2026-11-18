package br.com.fiap.waypoint.dao;

import br.com.fiap.waypoint.entities.Categoria;
import br.com.fiap.waypoint.conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaDao {

    Connection MinhaConexao;

    public CategoriaDao() throws SQLException, ClassNotFoundException {
        this.MinhaConexao = new Conexao().conexao();
    }

    // INSERIR
    public String inserir(Categoria categoria) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "INSERT INTO TB_CATEGORIA (nome, descricao) " +
                "VALUES (?, ?)", new String[] {"ID_CATEGORIA"});
        stmt.setString(1, categoria.getNome());
        stmt.setString(2, categoria.getDescricao());
        stmt.executeUpdate();

        // Recupera o ID gerado pelo Oracle.
        ResultSet ids = stmt.getGeneratedKeys();
        if (ids.next()) {
            categoria.setIdCategoria(ids.getLong(1));
        } else {
            ids.close();
            stmt.close();
            throw new SQLException("O Oracle nao retornou o ID gerado.");
        }
        ids.close();
        stmt.close();

        return "Categoria: cadastro inserido com sucesso!";
    }

    // ATUALIZAR
    public String Atualizar(Categoria categoria) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "UPDATE TB_CATEGORIA SET nome=?, descricao=? " +
                "WHERE id_categoria=?");
        stmt.setString(1, categoria.getNome());
        stmt.setString(2, categoria.getDescricao());
        stmt.setLong(3, categoria.getIdCategoria());
        int quantidade = stmt.executeUpdate();
        stmt.close();

        if (quantidade == 0) return "Categoria: ID nao encontrado.";
        return "Categoria: cadastro atualizado com sucesso!";
    }

    // SELECIONAR
    public ArrayList<Categoria> Selecionar(Categoria categoria) throws SQLException {
        ArrayList<Categoria> lista = new ArrayList<>();
        String sql = "SELECT id_categoria, nome, descricao FROM TB_CATEGORIA";
        if (categoria.getIdCategoria() != null) {
            sql += " WHERE id_categoria=?";
        }

        PreparedStatement stmt = MinhaConexao.prepareStatement(sql);
        if (categoria.getIdCategoria() != null) {
            stmt.setLong(1, categoria.getIdCategoria());
        }
        ResultSet kk = stmt.executeQuery();

        while (kk.next()) {
            Categoria encontrado = new Categoria();
            encontrado.setIdCategoria(kk.getLong("id_categoria"));
            encontrado.setNome(kk.getString("nome"));
            encontrado.setDescricao(kk.getString("descricao"));

            lista.add(encontrado);
        }
        kk.close();
        stmt.close();

        return lista;
    }

    // Excluir pelo ID; o Oracle impede exclusao de registros com vinculos.
    public String deletar(long id) throws SQLException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "DELETE FROM TB_CATEGORIA WHERE id_categoria=?");
        stmt.setLong(1, id);
        int quantidade = stmt.executeUpdate();
        stmt.close();

        if (quantidade == 0) return "Categoria: ID nao encontrado.";
        return "Categoria: cadastro excluido com sucesso!";
    }

    // Chame ao terminar de usar este DAO.
    public void fechar() throws SQLException {
        MinhaConexao.close();
    }
}
