package br.com.fiap.waypoint.main;

import br.com.fiap.waypoint.entities.*;

import javax.swing.*;
import java.util.ArrayList;

public class TesteArrayList {

    static String texto(String J) {
        return JOptionPane.showInputDialog(J);
    }

    static Double Real(String J) {
        return Double.parseDouble(JOptionPane.showInputDialog(J));
    }

    static Integer Inteiro(String J) {
        return Integer.parseInt(JOptionPane.showInputDialog(J));
    }

    static Long Longa(String J) {
        return Long.parseLong(JOptionPane.showInputDialog(J));
    }

    static Usuario escolherUsuario(ArrayList<Usuario> usuarios) {
        String mensagem = "Escolha usuario" + "\n";
        for (int i = 0; i < usuarios.size(); i++) {
            mensagem += (i + 1) + " - " + usuarios.get(i).getNome() + "\n";
        }
        int posicao = Inteiro(mensagem);
        return usuarios.get(posicao - 1);
    }

    static Categoria escolherCategoria(ArrayList<Categoria> categorias) {
        String mensagem = "Escolha categoria" + "\n";
        for (int i = 0; i < categorias.size(); i++) {
            mensagem += (i + 1) + " - " + categorias.get(i).getNome() + "\n";
        }
        int posicao = Inteiro(mensagem);
        return categorias.get(posicao - 1);
    }

    static Trecho escolherTrecho(ArrayList<Trecho> trechos, String titulo) {
        String mensagem = titulo + "\n";
        for (int i = 0; i < trechos.size(); i++) {
            mensagem += (i + 1) + " - " + trechos.get(i).getLogradouro() + "\n";
        }
        int posicao = Inteiro(mensagem);
        return trechos.get(posicao - 1);
    }

    static Relato escolherRelato(ArrayList<Relato> relatos) {
        String mensagem = "Escolha relato" + "\n";
        for (int i = 0; i < relatos.size(); i++) {
            mensagem += (i + 1) + " - " + relatos.get(i).getDescricao() + "\n";
        }
        int posicao = Inteiro(mensagem);
        return relatos.get(posicao - 1);
    }

    public static void main(String[] args) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Categoria> categorias = new ArrayList<>();
        ArrayList<Trecho> trechos = new ArrayList<>();
        ArrayList<Relato> relatos = new ArrayList<>();
        ArrayList<Rota> rotas = new ArrayList<>();
        ArrayList<Vldc_Relato> validacoes = new ArrayList<>();

        String[] menu = {"Usuarios", "Categorias", "Trechos", "Relatos", "Rotas", "Validacoes", "Sair"};
        String[] acoes = {"Cadastrar", "Listar", "Voltar"};

        while (true) {
            int opcao = JOptionPane.showOptionDialog(null, "Escolha uma opcao", "Waypoint",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, menu, menu[0]);
            if (opcao == 6 || opcao == -1) break;

            int acao = JOptionPane.showOptionDialog(null, "O que deseja fazer?", menu[opcao],
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, acoes, acoes[0]);

            if (acao == 0) {
                if (opcao == 0) {
                    Usuario usuario = new Usuario();
                    usuario.setId(Longa("ID do usuario"));
                    usuario.setNome(texto("Nome"));
                    usuario.setEmail(texto("E-mail"));
                    usuario.setSenha(texto("Senha de teste"));
                    usuario.setAcessibilidade(texto("Acessibilidade (s ou n)"));
                    usuarios.add(usuario);
                }
                else if (opcao == 1) {
                    Categoria categoria = new Categoria();
                    categoria.setIdCategoria(Longa("ID da categoria"));
                    categoria.setNome(texto("Nome da categoria"));
                    categoria.setDescricao(texto("Descricao"));
                    categorias.add(categoria);
                }
                else if (opcao == 2) {
                    Trecho trecho = new Trecho();
                    trecho.setIdTrecho(Longa("ID do trecho"));
                    trecho.setLogradouro(texto("Logradouro"));
                    trecho.setBairro(texto("Bairro"));
                    trecho.setLatitude(Real("Latitude (use ponto decimal)"));
                    trecho.setLongitude(Real("Longitude (use ponto decimal)"));
                    trechos.add(trecho);
                }
                else if (opcao == 3) {
                    Relato relato = new Relato();
                    relato.setIdRelato(Longa("ID do relato"));
                    relato.setUsuario(escolherUsuario(usuarios));
                    relato.setTrecho(escolherTrecho(trechos, "Trecho do relato"));
                    relato.setCategoria(escolherCategoria(categorias));
                    relato.setDescricao(texto("Descricao do relato"));
                    relato.setDataRelato(texto("Data do relato (dia/mes/ano)"));
                    relato.setStatus(texto("Status"));
                    relatos.add(relato);
                }
                else if (opcao == 4) {
                    Rota rota = new Rota();
                    rota.setIdRota(Longa("ID da rota"));
                    rota.setUsuario(escolherUsuario(usuarios));
                    rota.setTrechoInicio(escolherTrecho(trechos, "Trecho inicial"));
                    rota.setTrechoFinal(escolherTrecho(trechos, "Trecho final"));
                    rota.setDistancia(Real("Distancia (use ponto decimal)"));
                    rotas.add(rota);
                }
                else if (opcao == 5) {
                    Vldc_Relato validacao = new Vldc_Relato();
                    validacao.setIdVoto(Longa("ID do voto"));
                    validacao.setUsuario(escolherUsuario(usuarios));
                    validacao.setRelato(escolherRelato(relatos));
                    validacao.setVoto(texto("Confirma o relato? (s ou n)"));
                    validacoes.add(validacao);
                }
                JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
            } else if (acao == 1) {
                String mensagem = "";
                if (opcao == 0) {
                    for (Usuario item : usuarios) {
                        mensagem += item.toString() + "\n\n";
                    }
                }
                else if (opcao == 1) {
                    for (Categoria item : categorias) {
                        mensagem += item.toString() + "\n\n";
                    }
                }
                else if (opcao == 2) {
                    for (Trecho item : trechos) {
                        mensagem += item.toString() + "\n\n";
                    }
                }
                else if (opcao == 3) {
                    for (Relato item : relatos) {
                        mensagem += item.toString() + "\n\n";
                    }
                }
                else if (opcao == 4) {
                    for (Rota item : rotas) {
                        mensagem += item.toString() + "\n\n";
                    }
                }
                else if (opcao == 5) {
                    for (Vldc_Relato item : validacoes) {
                        mensagem += item.toString() + "\n\n";
                    }
                }
                JOptionPane.showMessageDialog(null, mensagem, "Cadastros", JOptionPane.PLAIN_MESSAGE);
            }
            // Voltar ou fechar a janela de acoes leva novamente ao menu.
        }
    }
}
