package com.senac.games.service;

import com.senac.games.entity.Categoria;
import com.senac.games.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    private CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;

    }

    public List<Categoria> listarCategorias(){
        return this.categoriaRepository.findAll();
    }
}
