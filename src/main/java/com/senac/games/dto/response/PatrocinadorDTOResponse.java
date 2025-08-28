package com.senac.games.dto.response;

public class PatrocinadorDTOResponse {

    private int id;

    private String nome;

    private String representanteNome;

    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRepresentanteNome() {
        return representanteNome;
    }

    public void setRepresentanteNome(String representanteNome) {
        this.representanteNome = representanteNome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
