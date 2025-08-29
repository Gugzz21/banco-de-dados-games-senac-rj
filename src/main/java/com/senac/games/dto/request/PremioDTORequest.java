package com.senac.games.dto.request;

public class PremioDTORequest {

    private String descricao;

    private int ordem_premiacao;

    private int categoria;

    private int status;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getOrdem_premiacao() {
        return ordem_premiacao;
    }

    public void setOrdem_premiacao(int ordem_premiacao) {
        this.ordem_premiacao = ordem_premiacao;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
