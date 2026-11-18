package br.com.fiap.waypoint.entities;

public class Categoria {

    private Long idCategoria;
    private String nome;
    private String descricao;

    public Categoria() {
    }

    public Categoria(Long idCategoria, String nome, String descricao) {
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Categoria {"
                + " idCategoria=" + idCategoria
                + " nome=" + nome
                + " descricao=" + descricao
                + " }";
    }
}
