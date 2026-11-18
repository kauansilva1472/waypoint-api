package br.com.fiap.waypoint.main.oracle;

import br.com.fiap.waypoint.dao.UsuarioDao;
import br.com.fiap.waypoint.dao.CategoriaDao;
import br.com.fiap.waypoint.dao.TrechoDao;
import br.com.fiap.waypoint.dao.RelatoDao;
import br.com.fiap.waypoint.dao.RotaDao;
import br.com.fiap.waypoint.dao.Vldc_RelatoDao;
import br.com.fiap.waypoint.entities.Usuario;
import br.com.fiap.waypoint.entities.Categoria;
import br.com.fiap.waypoint.entities.Trecho;
import br.com.fiap.waypoint.entities.Relato;
import br.com.fiap.waypoint.entities.Rota;
import br.com.fiap.waypoint.entities.Vldc_Relato;

import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;

public class TesteSelecionarEntidade {

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

        int opcao = inteiro("Escolha o que deseja selecionar:" +
                "\n1 Usuario" +
                "\n2 Categoria" +
                "\n3 Trecho" +
                "\n4 Relato" +
                "\n5 Rota" +
                "\n6 Validacao");

        switch (opcao) {
            case 1:
                UsuarioDao usuarioDao = new UsuarioDao();
                Usuario objUsuario = new Usuario();
                objUsuario.setId(codigo("Informe o ID de Usuario para selecionar"));

                ArrayList<Usuario> listaUsuario = usuarioDao.Selecionar(objUsuario);
                for (Usuario encontrado : listaUsuario) {
                    System.out.println(encontrado.getId() + " | " + encontrado.getNome() + " | " + encontrado.getEmail() + " | " + encontrado.getAcessibilidade());
                }
                usuarioDao.fechar();
                break;
            case 2:
                CategoriaDao categoriaDao = new CategoriaDao();
                Categoria objCategoria = new Categoria();
                objCategoria.setIdCategoria(codigo("Informe o ID de Categoria para selecionar"));

                ArrayList<Categoria> listaCategoria = categoriaDao.Selecionar(objCategoria);
                for (Categoria encontrado : listaCategoria) {
                    System.out.println(encontrado.getIdCategoria() + " | " + encontrado.getNome() + " | " + encontrado.getDescricao());
                }
                categoriaDao.fechar();
                break;
            case 3:
                TrechoDao trechoDao = new TrechoDao();
                Trecho objTrecho = new Trecho();
                objTrecho.setIdTrecho(codigo("Informe o ID de Trecho para selecionar"));

                ArrayList<Trecho> listaTrecho = trechoDao.Selecionar(objTrecho);
                for (Trecho encontrado : listaTrecho) {
                    System.out.println(encontrado.getIdTrecho() + " | " + encontrado.getLogradouro() + " | " + encontrado.getBairro() + " | " + encontrado.getLatitude() + " | " + encontrado.getLongitude());
                }
                trechoDao.fechar();
                break;
            case 4:
                RelatoDao relatoDao = new RelatoDao();
                Relato objRelato = new Relato();
                objRelato.setIdRelato(codigo("Informe o ID de Relato para selecionar"));

                ArrayList<Relato> listaRelato = relatoDao.Selecionar(objRelato);
                for (Relato encontrado : listaRelato) {
                    System.out.println(encontrado.getIdRelato() + " | " + encontrado.getDescricao() + " | " + encontrado.getDataRelato() + " | " + encontrado.getStatus() + " | " + encontrado.getUsuario().getId() + " | " + encontrado.getTrecho().getIdTrecho() + " | " + encontrado.getCategoria().getIdCategoria());
                }
                relatoDao.fechar();
                break;
            case 5:
                RotaDao rotaDao = new RotaDao();
                Rota objRota = new Rota();
                objRota.setIdRota(codigo("Informe o ID de Rota para selecionar"));

                ArrayList<Rota> listaRota = rotaDao.Selecionar(objRota);
                for (Rota encontrado : listaRota) {
                    System.out.println(encontrado.getIdRota() + " | " + encontrado.getDistancia() + " | " + encontrado.getUsuario().getId() + " | " + encontrado.getTrechoInicio().getIdTrecho() + " | " + encontrado.getTrechoFinal().getIdTrecho());
                }
                rotaDao.fechar();
                break;
            case 6:
                Vldc_RelatoDao validacaoDao = new Vldc_RelatoDao();
                Vldc_Relato objValidacao = new Vldc_Relato();
                objValidacao.setIdVoto(codigo("Informe o ID de Validacao para selecionar"));

                ArrayList<Vldc_Relato> listaValidacao = validacaoDao.Selecionar(objValidacao);
                for (Vldc_Relato encontrado : listaValidacao) {
                    System.out.println(encontrado.getIdVoto() + " | " + encontrado.getVoto() + " | " + encontrado.getUsuario().getId() + " | " + encontrado.getRelato().getIdRelato());
                }
                validacaoDao.fechar();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opcao invalida");
        }
    }
}
