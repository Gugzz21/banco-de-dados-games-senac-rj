package com.senac.games.service;

import com.senac.games.dto.request.PatrocinadorDTORequest;
import com.senac.games.dto.response.PatrocinadorDTOResponse;
import com.senac.games.entity.Patrocinador;
import com.senac.games.repository.PatrocinadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PatrocinadorService {

    private final PatrocinadorRepository patrocinadorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PatrocinadorService(PatrocinadorRepository patrocinadorRepository) {
        this.patrocinadorRepository = patrocinadorRepository;
    }

    public List<Patrocinador> listarPatrocinadores(){
       return this.patrocinadorRepository.findAll();
    }
    public PatrocinadorDTOResponse criarPatrocinador(PatrocinadorDTORequest patrocinadorDTORequest){
        Patrocinador patrocinador = modelMapper.map(patrocinadorDTORequest, Patrocinador.class);
        Patrocinador patrocinadorSave = this.patrocinadorRepository.save(patrocinador);
        PatrocinadorDTOResponse patrocinadorDTOResponse = modelMapper.map(patrocinadorSave, PatrocinadorDTOResponse.class);
        return patrocinadorDTOResponse;
    }
}
