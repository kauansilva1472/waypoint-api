package br.com.fiap.waypoint.entities;

public class Rota {

    private Long idRota;
    private Double distancia;
    private Usuario usuario;
    private Trecho trechoInicio;
    private Trecho trechoFinal;

    public Rota() {
    }

    public Rota(Long idRota, Double distancia, Usuario usuario, Trecho trechoInicio, Trecho trechoFinal) {
        this.idRota = idRota;
        this.distancia = distancia;
        this.usuario = usuario;
        this.trechoInicio = trechoInicio;
        this.trechoFinal = trechoFinal;
    }

    public Long getIdRota() {
        return idRota;
    }

    public void setIdRota(Long idRota) {
        this.idRota = idRota;
    }

    public Double getDistancia() {
        return distancia;
    }

    public void setDistancia(Double distancia) {
        this.distancia = distancia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Trecho getTrechoInicio() {
        return trechoInicio;
    }

    public void setTrechoInicio(Trecho trechoInicio) {
        this.trechoInicio = trechoInicio;
    }

    public Trecho getTrechoFinal() {
        return trechoFinal;
    }

    public void setTrechoFinal(Trecho trechoFinal) {
        this.trechoFinal = trechoFinal;
    }

    @Override
    public String toString() {
        return "Rota {"
                + " idRota=" + idRota
                + " distancia=" + distancia
                + " usuario=" + (usuario == null ? null : usuario.getId())
                + " trechoInicio=" + (trechoInicio == null ? null : trechoInicio.getIdTrecho())
                + " trechoFinal=" + (trechoFinal == null ? null : trechoFinal.getIdTrecho())
                + " }";
    }
}
