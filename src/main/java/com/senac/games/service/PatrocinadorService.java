package com.senac.games.service;

import com.senac.games.dto.request.PatrocinadorDTORequest;
import com.senac.games.dto.response.PatrocinadorDTOResponse;
import com.senac.games.dto.response.PatrocinadorDTOUpdateResponse;
import com.senac.games.entity.Patrocinador;
import com.senac.games.repository.PatrocinadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PatrocinadorService {

    private PatrocinadorRepository patrocinadorRepository;

    @Autowired
    private ModelMapper modelmapper;

    public PatrocinadorService(PatrocinadorRepository patrocinadorRepository) {
        this.patrocinadorRepository = patrocinadorRepository;
    }

    public PatrocinadorService() {
    }

    public List<Patrocinador> listarPatrocinadores() {
        return this.patrocinadorRepository.listarPatrocinadores();
    }

    public Patrocinador obterPatrocinadorPeloId(Integer patrocinadorId) {
        return this.patrocinadorRepository.obterPatrocinadorPeloId(patrocinadorId);
    }

    public PatrocinadorDTOResponse criarPatrocinador(PatrocinadorDTORequest patrocinadorDTORequest) {

        Patrocinador patrocinador = modelmapper.map(patrocinadorDTORequest, Patrocinador.class);
        Patrocinador patrocinadorSave = this.patrocinadorRepository.save(patrocinador);
        PatrocinadorDTOResponse patrocinadorDTOResponse = modelmapper.map(patrocinadorSave, PatrocinadorDTOResponse.class);
        return patrocinadorDTOResponse;

    }

    public PatrocinadorDTOResponse atualizarPatrocinador(Integer patrocinadorId, PatrocinadorDTORequest patrocinadorDTORequest) {
        //Antes de atualizar busca se existe o registro a ser atualizado
        Patrocinador patrocinador = this.obterPatrocinadorPeloId(patrocinadorId);
        //Se encontra o registro a ser atualizado
        if (patrocinador != null) {
            //copia os dados a serem atualizados do DTO de entrada para um objeto do tipo participante
            //que é compatível com o repository para atualizar
            modelmapper.map(patrocinadorDTORequest, Patrocinador.class);

            //Com o objeto no formato correto tipo "participante"  o comando "save" salva
            //no banco de dados o objeto atualizado
            Patrocinador tempResponse = patrocinadorRepository.save(patrocinador);
            return modelmapper.map(tempResponse, PatrocinadorDTOResponse.class);
        } else {
            return null;
        }

    }

    public PatrocinadorDTOUpdateResponse atualizarStatusPartrocinador(Integer patrocinadorId, PatrocinadorDTORequest patrocinadorDTOUpdateRequest) {
        Patrocinador patrocinador = this.obterPatrocinadorPeloId(patrocinadorId);
        if(patrocinador != null) {
            patrocinador.setStatus(patrocinadorDTOUpdateRequest.getStatus());

            Patrocinador tempReponse = patrocinadorRepository.save(patrocinador);

            return modelmapper.map(tempReponse, PatrocinadorDTOUpdateResponse.class);
        }
        else {return null;}

    }


    public void apagarPatrocinador(Integer patrocinadorId) {
        patrocinadorRepository.apagadoLogicoPatrocinador(patrocinadorId);
    }
}
