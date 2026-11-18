package br.com.fiap.waypoint.entities;

public class Vldc_Relato {

    private Long idVoto;
    private String voto;
    private Usuario usuario;
    private Relato relato;

    public Vldc_Relato() {
    }

    public Vldc_Relato(Long idVoto, String voto, Usuario usuario, Relato relato) {
        this.idVoto = idVoto;
        this.voto = voto;
        this.usuario = usuario;
        this.relato = relato;
    }

    public Long getIdVoto() {
        return idVoto;
    }

    public void setIdVoto(Long idVoto) {
        this.idVoto = idVoto;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Relato getRelato() {
        return relato;
    }

    public void setRelato(Relato relato) {
        this.relato = relato;
    }

    @Override
    public String toString() {
        return "Vldc_Relato {"
                + " idVoto=" + idVoto
                + " voto=" + voto
                + " usuario=" + (usuario == null ? null : usuario.getId())
                + " relato=" + (relato == null ? null : relato.getIdRelato())
                + " }";
    }
}
