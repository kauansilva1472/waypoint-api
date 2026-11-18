package br.com.fiap.waypoint.entities;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Usuario {

    private Long id;
    private String nome;
    // JsonProperty e uma anotacao do Jackson que controla os campos do JSON.
    // WRITE_ONLY recebe a senha no cadastro/atualizacao, mas nao a envia nas consultas.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private String email;
    private String acessibilidade;

    public Usuario() {
    }

    public Usuario(Long id, String nome, String senha, String email, String acessibilidade) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.acessibilidade = acessibilidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAcessibilidade() {
        return acessibilidade;
    }

    public void setAcessibilidade(String acessibilidade) {
        this.acessibilidade = acessibilidade;
    }

    @Override
    public String toString() {
        return "Usuario {"
                + " id=" + id
                + " nome=" + nome
                + " email=" + email
                + " acessibilidade=" + acessibilidade
                + " }";
    }
}
