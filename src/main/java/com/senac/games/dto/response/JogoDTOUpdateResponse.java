package com.senac.games.dto.response;

public class JogoDTOUpdateResponse {
    private Integer id;
    private Integer status;

    private Integer idCategoria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIdCategoria() {

        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        idCategoria = idCategoria;
    }
}
