package br.com.fiap.waypoint.entities;

public class Trecho {

    private Long idTrecho;
    private String logradouro;
    private Double latitude;
    private Double longitude;
    private String bairro;

    public Trecho() {
    }

    public Trecho(Long idTrecho, String logradouro, Double latitude, Double longitude, String bairro) {
        this.idTrecho = idTrecho;
        this.logradouro = logradouro;
        this.latitude = latitude;
        this.longitude = longitude;
        this.bairro = bairro;
    }

    public Long getIdTrecho() {
        return idTrecho;
    }

    public void setIdTrecho(Long idTrecho) {
        this.idTrecho = idTrecho;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Override
    public String toString() {
        return "Trecho {"
                + " idTrecho=" + idTrecho
                + " logradouro=" + logradouro
                + " latitude=" + latitude
                + " longitude=" + longitude
                + " bairro=" + bairro
                + " }";
    }
}
