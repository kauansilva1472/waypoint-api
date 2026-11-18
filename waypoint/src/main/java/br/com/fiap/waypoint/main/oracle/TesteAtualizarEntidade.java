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

public class TesteAtualizarEntidade {

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

        int opcao = inteiro("Escolha o que deseja atualizar:" +
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
                objUsuario.setId(codigo("Informe o ID de Usuario para atualizar"));

                objUsuario.setNome(texto("Nome"));
                objUsuario.setEmail(texto("Email"));
                objUsuario.setSenha(texto("Senha"));
                objUsuario.setAcessibilidade(texto("Acessibilidade (s/n)"));

                System.out.println(usuarioDao.Atualizar(objUsuario));
                usuarioDao.fechar();
                break;
            case 2:
                CategoriaDao categoriaDao = new CategoriaDao();
                Categoria objCategoria = new Categoria();
                objCategoria.setIdCategoria(codigo("Informe o ID de Categoria para atualizar"));

                objCategoria.setNome(texto("Nome"));
                objCategoria.setDescricao(texto("Descricao"));

                System.out.println(categoriaDao.Atualizar(objCategoria));
                categoriaDao.fechar();
                break;
            case 3:
                TrechoDao trechoDao = new TrechoDao();
                Trecho objTrecho = new Trecho();
                objTrecho.setIdTrecho(codigo("Informe o ID de Trecho para atualizar"));

                objTrecho.setLogradouro(texto("Logradouro"));
                objTrecho.setBairro(texto("Bairro"));
                objTrecho.setLatitude(numero("Latitude"));
                objTrecho.setLongitude(numero("Longitude"));

                System.out.println(trechoDao.Atualizar(objTrecho));
                trechoDao.fechar();
                break;
            case 4:
                RelatoDao relatoDao = new RelatoDao();
                Relato objRelato = new Relato();
                objRelato.setIdRelato(codigo("Informe o ID de Relato para atualizar"));

                objRelato.setDescricao(texto("Descricao"));
                objRelato.setDataRelato(texto("Data do relato (DD/MM/AAAA)"));
                objRelato.setStatus(texto("Status (ate 7 caracteres)"));

                Usuario usuarioDaRelato = new Usuario();
                usuarioDaRelato.setId(codigo("ID de Usuario ja cadastrado"));
                objRelato.setUsuario(usuarioDaRelato);

                Trecho trechoDaRelato = new Trecho();
                trechoDaRelato.setIdTrecho(codigo("ID de Trecho ja cadastrado"));
                objRelato.setTrecho(trechoDaRelato);

                Categoria categoriaDaRelato = new Categoria();
                categoriaDaRelato.setIdCategoria(codigo("ID de Categoria ja cadastrado"));
                objRelato.setCategoria(categoriaDaRelato);

                System.out.println(relatoDao.Atualizar(objRelato));
                relatoDao.fechar();
                break;
            case 5:
                RotaDao rotaDao = new RotaDao();
                Rota objRota = new Rota();
                objRota.setIdRota(codigo("Informe o ID de Rota para atualizar"));

                objRota.setDistancia(numero("Distancia (use ponto para os decimais)"));

                Usuario usuarioDaRota = new Usuario();
                usuarioDaRota.setId(codigo("ID de Usuario ja cadastrado"));
                objRota.setUsuario(usuarioDaRota);

                Trecho trechoInicioDaRota = new Trecho();
                trechoInicioDaRota.setIdTrecho(codigo("ID de TrechoInicio ja cadastrado"));
                objRota.setTrechoInicio(trechoInicioDaRota);

                Trecho trechoFinalDaRota = new Trecho();
                trechoFinalDaRota.setIdTrecho(codigo("ID de TrechoFinal ja cadastrado"));
                objRota.setTrechoFinal(trechoFinalDaRota);

                System.out.println(rotaDao.Atualizar(objRota));
                rotaDao.fechar();
                break;
            case 6:
                Vldc_RelatoDao validacaoDao = new Vldc_RelatoDao();
                Vldc_Relato objValidacao = new Vldc_Relato();
                objValidacao.setIdVoto(codigo("Informe o ID de Validacao para atualizar"));

                objValidacao.setVoto(texto("Voto (s/n)"));

                Usuario usuarioDaValidacao = new Usuario();
                usuarioDaValidacao.setId(codigo("ID de Usuario ja cadastrado"));
                objValidacao.setUsuario(usuarioDaValidacao);

                Relato relatoDaValidacao = new Relato();
                relatoDaValidacao.setIdRelato(codigo("ID de Relato ja cadastrado"));
                objValidacao.setRelato(relatoDaValidacao);

                System.out.println(validacaoDao.Atualizar(objValidacao));
                validacaoDao.fechar();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opcao invalida");
        }
    }
}
