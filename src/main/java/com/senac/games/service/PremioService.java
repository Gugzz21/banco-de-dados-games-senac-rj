package com.senac.games.service;


import com.senac.games.dto.request.PremioDTORequest;
import com.senac.games.dto.request.PremioDTORequest;
import com.senac.games.dto.response.PremioDTOResponse;
import com.senac.games.dto.response.PremioDTOUpdateResponse;
import com.senac.games.dto.response.PremioDTOResponse;
import com.senac.games.dto.response.PremioDTOUpdateResponse;
import com.senac.games.entity.Categoria; // Essa importação pode ser removida se a classe Categoria não for usada.
import com.senac.games.entity.Premio;
import com.senac.games.entity.Premio;
import com.senac.games.repository.PremioRepository;
import com.senac.games.repository.PremioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PremioService {

    private PremioRepository premioRepository;

    @Autowired
    private ModelMapper modelmapper;

    public PremioService(PremioRepository premioRepository) {
        this.premioRepository = premioRepository;
    }

    public PremioService() {
    }

    public List<Premio> listarPremios() {
        return this.premioRepository.listarPremios();
    }

    public Premio obterpremioPeloId(Integer premioId) {
        return this.premioRepository.obterPremioPeloId(premioId);
    }

    public PremioDTOResponse criarPremio(PremioDTORequest premioDTORequest) {
        Premio premio = modelmapper.map(premioDTORequest, Premio.class);
        Premio premioSave = this.premioRepository.save(premio);
        PremioDTOResponse premioDTOResponse = modelmapper.map(premioSave, PremioDTOResponse.class);
        return premioDTOResponse;

    }

    public PremioDTOResponse atualizarPremio(Integer premioId, PremioDTORequest premioDTORequest) {
        Premio premio = this.obterpremioPeloId(premioId);
        if (premio != null) {
            modelmapper.map(premioDTORequest, Premio.class);
            Premio tempResponse = premioRepository.save(premio);
            return modelmapper.map(tempResponse, PremioDTOResponse.class);
        } else {
            return null;
        }

    }

    public PremioDTOUpdateResponse atualizarStatusPremio(Integer premioId, PremioDTORequest premioDTOUpdateRequest) {
        Premio premio = this.obterpremioPeloId(premioId);
        if(premio != null) {
            premio.setStatus(premioDTOUpdateRequest.getStatus());

            Premio tempReponse = premioRepository.save(premio);

            return modelmapper.map(tempReponse, PremioDTOUpdateResponse.class);
        }
        else {return null;}

    }


    public void apagarPremio(Integer premioId) {
        premioRepository.apagadoLogicoPremio(premioId);
    }
}