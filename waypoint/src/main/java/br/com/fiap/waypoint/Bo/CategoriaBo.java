package br.com.fiap.waypoint.Bo;

import br.com.fiap.waypoint.dao.CategoriaDao;
import br.com.fiap.waypoint.entities.Categoria;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriaBo {

    // Selecionar
    public ArrayList<Categoria> SelecionarBo(Categoria categoria) throws SQLException, ClassNotFoundException {

        CategoriaDao categoriaDao = new CategoriaDao();

        try {
            return categoriaDao.Selecionar(categoria);
        } finally {
            categoriaDao.fechar();
        }
    }

    // Inserir
    public String InserirBo(Categoria categoria) throws SQLException, ClassNotFoundException {

        validarDados(categoria);

        CategoriaDao categoriaDao = new CategoriaDao();

        try {
            return categoriaDao.inserir(categoria);
        } finally {
            categoriaDao.fechar();
        }
    }

    // Atualizar
    public String AtualizarBo(Categoria categoria) throws SQLException, ClassNotFoundException {

        validarDados(categoria);

        CategoriaDao categoriaDao = new CategoriaDao();

        try {
            return categoriaDao.Atualizar(categoria);
        } finally {
            categoriaDao.fechar();
        }
    }

    // Deletar pelo ID
    public String deletarBo(long id) throws SQLException, ClassNotFoundException {
        CategoriaDao categoriaDao = new CategoriaDao();
        try {
            return categoriaDao.deletar(id);
        } finally {
            categoriaDao.fechar();
        }
    }

    // Buscar por ID
    public Categoria buscarPorid(long id) throws SQLException, ClassNotFoundException {
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(id);
        ArrayList<Categoria> resultado = SelecionarBo(categoria);
        if (resultado.isEmpty()) {
            return null;
        }
        return resultado.get(0);
    }

    // Confere os dados antes de abrir uma conexao com o banco.
    public void validarDados(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("Informe os dados de Categoria.");
        }
        if (categoria.getNome() == null || categoria.getNome().isBlank() || categoria.getNome().length() > 20) {
            throw new IllegalArgumentException("Nome: preencha de 1 a 20 caracteres.");
        }
        if (categoria.getDescricao() == null || categoria.getDescricao().isBlank() || categoria.getDescricao().length() > 100) {
            throw new IllegalArgumentException("Descricao: preencha de 1 a 100 caracteres.");
        }
    }
}
