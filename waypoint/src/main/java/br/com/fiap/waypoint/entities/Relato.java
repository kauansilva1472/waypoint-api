package br.com.fiap.waypoint.entities;

public class Relato {

    private Long idRelato;
    private String dataRelato;
    private String descricao;
    private String status;
    private Usuario usuario;
    private Trecho trecho;
    private Categoria categoria;

    public Relato() {
    }

    public Relato(Long idRelato, String dataRelato, String descricao, String status, Usuario usuario, Trecho trecho, Categoria categoria) {
        this.idRelato = idRelato;
        this.dataRelato = dataRelato;
        this.descricao = descricao;
        this.status = status;
        this.usuario = usuario;
        this.trecho = trecho;
        this.categoria = categoria;
    }

    public Long getIdRelato() {
        return idRelato;
    }

    public void setIdRelato(Long idRelato) {
        this.idRelato = idRelato;
    }

    public String getDataRelato() {
        return dataRelato;
    }

    public void setDataRelato(String dataRelato) {
        this.dataRelato = dataRelato;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Trecho getTrecho() {
        return trecho;
    }

    public void setTrecho(Trecho trecho) {
        this.trecho = trecho;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Relato {"
                + " idRelato=" + idRelato
                + " dataRelato=" + dataRelato
                + " descricao=" + descricao
                + " status=" + status
                + " usuario=" + (usuario == null ? null : usuario.getId())
                + " trecho=" + (trecho == null ? null : trecho.getIdTrecho())
                + " categoria=" + (categoria == null ? null : categoria.getIdCategoria())
                + " }";
    }
}
