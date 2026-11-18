package br.com.fiap.waypoint.dao;

import br.com.fiap.waypoint.entities.Rota;
import br.com.fiap.waypoint.entities.Trecho;
import br.com.fiap.waypoint.entities.Usuario;
import br.com.fiap.waypoint.conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RotaDao {

    Connection MinhaConexao;

    public RotaDao() throws SQLException, ClassNotFoundException {
        this.MinhaConexao = new Conexao().conexao();
    }

    // INSERIR
    public String inserir(Rota rota) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "INSERT INTO TB_ROTA (distancia, TB_USUARIO_id_usuario, TB_TRECHO_id_trecho_inicio, TB_TRECHO_id_trecho_final) " +
                "VALUES (?, ?, ?, ?)", new String[] {"ID_ROTA"});
        stmt.setDouble(1, rota.getDistancia());
        stmt.setLong(2, rota.getUsuario().getId());
        stmt.setLong(3, rota.getTrechoInicio().getIdTrecho());
        stmt.setLong(4, rota.getTrechoFinal().getIdTrecho());
        stmt.executeUpdate();

        // Recupera o ID gerado pelo Oracle.
        ResultSet ids = stmt.getGeneratedKeys();
        if (ids.next()) {
            rota.setIdRota(ids.getLong(1));
        } else {
            ids.close();
            stmt.close();
            throw new SQLException("O Oracle nao retornou o ID gerado.");
        }
        ids.close();
        stmt.close();

        return "Rota: cadastro inserido com sucesso!";
    }

    // ATUALIZAR PELO ID
    public String Atualizar(Rota rota) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "UPDATE TB_ROTA SET distancia=?, TB_USUARIO_id_usuario=?, TB_TRECHO_id_trecho_inicio=?, TB_TRECHO_id_trecho_final=? " +
                "WHERE id_rota=?");
        stmt.setDouble(1, rota.getDistancia());
        stmt.setLong(2, rota.getUsuario().getId());
        stmt.setLong(3, rota.getTrechoInicio().getIdTrecho());
        stmt.setLong(4, rota.getTrechoFinal().getIdTrecho());
        stmt.setLong(5, rota.getIdRota());
        int quantidade = stmt.executeUpdate();
        stmt.close();

        if (quantidade == 0) return "Rota: ID nao encontrado.";
        return "Rota: cadastro atualizado com sucesso!";
    }

    // SELECIONAR PELO ID: preencha o ID do objeto antes de chamar.
    public ArrayList<Rota> Selecionar(Rota rota) throws SQLException {
        ArrayList<Rota> lista = new ArrayList<>();
        String sql = "SELECT id_rota, distancia, TB_USUARIO_id_usuario, TB_TRECHO_id_trecho_inicio, TB_TRECHO_id_trecho_final FROM TB_ROTA";
        if (rota.getIdRota() != null) {
            sql += " WHERE id_rota=?";
        }

        PreparedStatement stmt = MinhaConexao.prepareStatement(sql);
        if (rota.getIdRota() != null) {
            stmt.setLong(1, rota.getIdRota());
        }
        ResultSet kk = stmt.executeQuery();

        while (kk.next()) {
            Rota encontrado = new Rota();
            encontrado.setIdRota(kk.getLong("id_rota"));
            encontrado.setDistancia(kk.getDouble("distancia"));

            // As referencias abaixo carregam somente os IDs das chaves estrangeiras.
            Usuario usuario = new Usuario();
            usuario.setId(kk.getLong("TB_USUARIO_id_usuario"));
            encontrado.setUsuario(usuario);
            Trecho trechoInicio = new Trecho();
            trechoInicio.setIdTrecho(kk.getLong("TB_TRECHO_id_trecho_inicio"));
            encontrado.setTrechoInicio(trechoInicio);
            Trecho trechoFinal = new Trecho();
            trechoFinal.setIdTrecho(kk.getLong("TB_TRECHO_id_trecho_final"));
            encontrado.setTrechoFinal(trechoFinal);

            lista.add(encontrado);
        }
        kk.close();
        stmt.close();

        return lista;
    }

    // Excluir pelo ID; o Oracle impede exclusao de registros com vinculos.
    public String deletar(long id) throws SQLException {
        PreparedStatement stmt = MinhaConexao.prepareStatement(
                "DELETE FROM TB_ROTA WHERE id_rota=?");
        stmt.setLong(1, id);
        int quantidade = stmt.executeUpdate();
        stmt.close();

        if (quantidade == 0) return "Rota: ID nao encontrado.";
        return "Rota: cadastro excluido com sucesso!";
    }

    // Chame ao terminar de usar este DAO.
    public void fechar() throws SQLException {
        MinhaConexao.close();
    }
}
