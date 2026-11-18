package br.com.fiap.waypoint.main;

import br.com.fiap.waypoint.conexao.Conexao;

import java.sql.Connection;
import java.sql.SQLException;

public class TesteConexao {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Connection on = new Conexao().conexao();

        System.out.println("Conectado com sucesso!");

        on.close();
    }

}

