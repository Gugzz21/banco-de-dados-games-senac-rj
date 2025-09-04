package com.senac.games.service;

import com.senac.games.dto.request.CategoriaDTORequest;
import com.senac.games.dto.response.CategoriaDTOResponse;
import com.senac.games.dto.response.CategoriaDTOUpdateResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.entity.Categoria;
import com.senac.games.repository.CategoriaRepository;
import com.senac.games.repository.CategoriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    @Autowired
    private ModelMapper modelmapper;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaService() {
    }

    public List<Categoria> listarCategorias() {
        return this.categoriaRepository.listarCategorias();
    }

    public Categoria obtercategoriaPeloId(Integer categoriaId) {
        return this.categoriaRepository.obterCategoriaPeloId(categoriaId);
    }

    public CategoriaDTOResponse criarCategoria(CategoriaDTORequest categoriaDTORequest) {
        Categoria categoria = modelmapper.map(categoriaDTORequest, Categoria.class);
        Categoria categoriaSave = this.categoriaRepository.save(categoria);
        CategoriaDTOResponse categoriaDTOResponse = modelmapper.map(categoriaSave, CategoriaDTOResponse.class);
        return categoriaDTOResponse;

    }

    public CategoriaDTOResponse atualizarCategoria(Integer categoriaId, CategoriaDTORequest categoriaDTORequest) {
        Categoria categoria = this.obtercategoriaPeloId(categoriaId);
        if (categoria != null) {
            modelmapper.map(categoriaDTORequest, Categoria.class);
            Categoria tempResponse = categoriaRepository.save(categoria);
            return modelmapper.map(tempResponse, CategoriaDTOResponse.class);
        } else {
            return null;
        }

    }

    public CategoriaDTOUpdateResponse atualizarStatusCategoria(Integer categoriaId, CategoriaDTORequest categoriaDTOUpdateRequest) {
        Categoria categoria = this.obtercategoriaPeloId(categoriaId);
        if(categoria != null) {
            categoria.setStatus(categoriaDTOUpdateRequest.getStatus());

            Categoria tempReponse = categoriaRepository.save(categoria);

            return modelmapper.map(tempReponse, CategoriaDTOUpdateResponse.class);
        }
        else {return null;}

    }


    public void apagarCategoria(Integer categoriaId) {
        categoriaRepository.apagadoLogicoCategoria(categoriaId);
    }
}
