package br.com.fiap.waypoint.dao;

import br.com.fiap.waypoint.entities.Usuario;
import br.com.fiap.waypoint.conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDao {

    Connection MinhaConexao;

    public UsuarioDao() throws SQLException, ClassNotFoundException {
        this.MinhaConexao = new Conexao().conexao();
    }

    // INSERIR
    public String inserir(Usuario usuario) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "INSERT INTO TB_USUARIO (nome, email, senha, acessibilidade) " +
                "VALUES (?, ?, ?, ?)", new String[] {"ID_USUARIO"});
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getAcessibilidade());
        stmt.executeUpdate();

        // Recupera o ID gerado pelo Oracle.
        ResultSet ids = stmt.getGeneratedKeys();
        if (ids.next()) {
            usuario.setId(ids.getLong(1));
        } else {
            ids.close();
            stmt.close();
            throw new SQLException("O Oracle nao retornou o ID gerado.");
        }
        ids.close();
        stmt.close();

        return "Usuario: cadastro inserido com sucesso!";
    }

    // ATUALIZAR PELO ID
    public String Atualizar(Usuario usuario) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "UPDATE TB_USUARIO SET nome=?, email=?, senha=?, acessibilidade=? " +
                "WHERE id_usuario=?");
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getEmail());
        stmt.setString(3, usuario.getSenha());
        stmt.setString(4, usuario.getAcessibilidade());
        stmt.setLong(5, usuario.getId());
        int quantidade = stmt.executeUpdate();
        stmt.close();

        if (quantidade == 0) return "Usuario: ID nao encontrado.";
        return "Usuario: cadastro atualizado com sucesso!";
    }

    // SELECIONAR: sem ID lista todos; com ID filtra o usuario.
    public ArrayList<Usuario> Selecionar(Usuario usuario) throws SQLException {
        ArrayList<Usuario> lista = new ArrayList<>();
        String sql = "SELECT id_usuario, nome, email, senha, acessibilidade FROM TB_USUARIO";
        if (usuario.getId() != null) {
            sql += " WHERE id_usuario=?";
        }

        PreparedStatement stmt = MinhaConexao.prepareStatement(sql);
        if (usuario.getId() != null) {
            stmt.setLong(1, usuario.getId());
        }
        ResultSet kk = stmt.executeQuery();

        while (kk.next()) {
            Usuario encontrado = new Usuario();
            encontrado.setId(kk.getLong("id_usuario"));
            encontrado.setNome(kk.getString("nome"));
            encontrado.setEmail(kk.getString("email"));
            encontrado.setSenha(kk.getString("senha"));
            encontrado.setAcessibilidade(kk.getString("acessibilidade"));

            lista.add(encontrado);
        }
        kk.close();
        stmt.close();

        return lista;
    }

    // EXCLUIR SOMENTE USUARIO
    public String deletar(long id) throws SQLException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "DELETE FROM TB_USUARIO WHERE id_usuario=?");
        stmt.setLong(1, id);
        int quantidade = stmt.executeUpdate();
        stmt.close();

        if (quantidade == 0) return "Usuario: ID nao encontrado.";
        return "Usuario deletado com sucesso!";
    }

    // Chame o terminar de usar este DAO.
    public void fechar() throws SQLException {
        MinhaConexao.close();
    }
}
