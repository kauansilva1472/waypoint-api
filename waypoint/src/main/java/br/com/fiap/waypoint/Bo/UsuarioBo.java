package br.com.fiap.waypoint.Bo;

import br.com.fiap.waypoint.dao.UsuarioDao;
import br.com.fiap.waypoint.entities.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioBo {

    // Selecionar
    public ArrayList<Usuario> SelecionarBo(Usuario usuario) throws SQLException, ClassNotFoundException {

        UsuarioDao usuarioDao = new UsuarioDao();

        try {
            return usuarioDao.Selecionar(usuario);
        } finally {
            usuarioDao.fechar();
        }
    }

    // Inserir
    public String InserirBo(Usuario usuario) throws SQLException, ClassNotFoundException {

        validarDados(usuario);

        UsuarioDao usuarioDao = new UsuarioDao();

        try {
            return usuarioDao.inserir(usuario);
        } finally {
            usuarioDao.fechar();
        }
    }

    // Atualizar
    public String AtualizarBo(Usuario usuario) throws SQLException, ClassNotFoundException {

        validarDados(usuario);

        UsuarioDao usuarioDao = new UsuarioDao();

        try {
            return usuarioDao.Atualizar(usuario);
        } finally {
            usuarioDao.fechar();
        }
    }

    // Deletar somente usuario
    public String deletarBo(long id) throws SQLException, ClassNotFoundException {

        UsuarioDao usuarioDao = new UsuarioDao();

        try {
            return usuarioDao.deletar(id);
        } finally {
            usuarioDao.fechar();
        }
    }

    // Buscar por ID
    public Usuario buscarPorid(long id) throws SQLException, ClassNotFoundException {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        ArrayList<Usuario> resultado = SelecionarBo(usuario);
        if (resultado.isEmpty()) {
            return null;
        }
        return resultado.get(0);
    }


    // Confere os dados antes de abrir uma conexao com o banco.
    public void validarDados(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Informe os dados de Usuario.");
        } else if (usuario.getNome() == null || usuario.getNome().isBlank() || usuario.getNome().length() > 50) {
            throw new IllegalArgumentException("Nome: preencha de 1 a 50 caracteres.");
        } else if (usuario.getEmail() == null || usuario.getEmail().isBlank() || usuario.getEmail().length() > 40) {
            throw new IllegalArgumentException("Email: preencha de 1 a 40 caracteres.");
        } else if (usuario.getSenha() == null || usuario.getSenha().isBlank() || usuario.getSenha().length() > 20) {
            throw new IllegalArgumentException("Senha: preencha de 1 a 20 caracteres.");
        } else if (usuario.getAcessibilidade() == null || usuario.getAcessibilidade().isBlank() || usuario.getAcessibilidade().length() > 1) {
            throw new IllegalArgumentException("Acessibilidade: preencha de 1 a 1 caracteres.");
        } else if (!usuario.getEmail().contains("@")) {
            throw new IllegalArgumentException("Informe um email valido, como nome@exemplo.com.");
        } else if (!"s".equals(usuario.getAcessibilidade()) && !"n".equals(usuario.getAcessibilidade())) {
            throw new IllegalArgumentException("Acessibilidade deve ser s ou n.");
        }
    }
}
