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

public class TesteInserirEntidade {

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

        int opcao = inteiro("Escolha o que deseja inserir:" +
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

                objUsuario.setNome(texto("Nome"));
                objUsuario.setEmail(texto("Email"));
                objUsuario.setSenha(texto("Senha"));
                objUsuario.setAcessibilidade(texto("Acessibilidade (s/n)"));

                System.out.println(usuarioDao.inserir(objUsuario));
                System.out.println("ID gerado: " + objUsuario.getId());
                usuarioDao.fechar();
                break;
            case 2:
                CategoriaDao categoriaDao = new CategoriaDao();
                Categoria objCategoria = new Categoria();

                objCategoria.setNome(texto("Nome"));
                objCategoria.setDescricao(texto("Descricao"));

                System.out.println(categoriaDao.inserir(objCategoria));
                System.out.println("ID gerado: " + objCategoria.getIdCategoria());
                categoriaDao.fechar();
                break;
            case 3:
                TrechoDao trechoDao = new TrechoDao();
                Trecho objTrecho = new Trecho();

                objTrecho.setLogradouro(texto("Logradouro"));
                objTrecho.setBairro(texto("Bairro"));
                objTrecho.setLatitude(numero("Latitude"));
                objTrecho.setLongitude(numero("Longitude"));

                System.out.println(trechoDao.inserir(objTrecho));
                System.out.println("ID gerado: " + objTrecho.getIdTrecho());
                trechoDao.fechar();
                break;
            case 4:
                RelatoDao relatoDao = new RelatoDao();
                Relato objRelato = new Relato();

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

                System.out.println(relatoDao.inserir(objRelato));
                System.out.println("ID gerado: " + objRelato.getIdRelato());
                relatoDao.fechar();
                break;
            case 5:
                RotaDao rotaDao = new RotaDao();
                Rota objRota = new Rota();

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

                System.out.println(rotaDao.inserir(objRota));
                System.out.println("ID gerado: " + objRota.getIdRota());
                rotaDao.fechar();
                break;
            case 6:
                Vldc_RelatoDao validacaoDao = new Vldc_RelatoDao();
                Vldc_Relato objValidacao = new Vldc_Relato();

                objValidacao.setVoto(texto("Voto (s/n)"));

                Usuario usuarioDaValidacao = new Usuario();
                usuarioDaValidacao.setId(codigo("ID de Usuario ja cadastrado"));
                objValidacao.setUsuario(usuarioDaValidacao);

                Relato relatoDaValidacao = new Relato();
                relatoDaValidacao.setIdRelato(codigo("ID de Relato ja cadastrado"));
                objValidacao.setRelato(relatoDaValidacao);

                System.out.println(validacaoDao.inserir(objValidacao));
                System.out.println("ID gerado: " + objValidacao.getIdVoto());
                validacaoDao.fechar();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opcao invalida");
        }
    }
}
