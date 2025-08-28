package com.senac.games.dto.request;

public class CategoriaDTORequest {
    private String nome;

    private int status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
