package br.com.fiap.waypoint.main.oracle;

import br.com.fiap.waypoint.dao.UsuarioDao;
import br.com.fiap.waypoint.entities.Usuario;

import javax.swing.JOptionPane;
import java.sql.SQLException;

public class TesteDeletarEntidade {

    static String texto(String j){
        return JOptionPane.showInputDialog(j);
    }

    static int inteiro(String j){
        return Integer.parseInt(JOptionPane.showInputDialog(j));
    }

    static double numero(String j){
        return Double.parseDouble(JOptionPane.showInputDialog(j));
    }

    static long codigo(String j){
        return Long.parseLong(JOptionPane.showInputDialog(j));
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        int opcao = inteiro("Escolha o que deseja deletar:" +
                "\n1 Usuario");

        switch (opcao) {
            case 1:
                UsuarioDao usuarioDao = new UsuarioDao();
                Usuario objUsuario = new Usuario();
                objUsuario.setId(codigo("Informe o ID de Usuario para deletar"));

                System.out.println(usuarioDao.deletar(objUsuario.getId()));
                usuarioDao.fechar();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opcao invalida");
        }
    }
}
