package com.senac.games.service;

import com.senac.games.entity.Patrocinador;
import com.senac.games.repository.PatrocinadorRepository;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PatrocinadorService {

    private PatrocinadorRepository patrocinadorRepository;

    public PatrocinadorService(PatrocinadorRepository patrocinadorRepository) {
        this.patrocinadorRepository = patrocinadorRepository;
    }

    public List<Patrocinador> listarPatrocinadores(){
       return this.patrocinadorRepository.findAll();


    }
}
