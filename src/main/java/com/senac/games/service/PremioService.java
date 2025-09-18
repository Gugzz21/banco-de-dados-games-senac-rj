package com.senac.games.service;

import com.senac.games.dto.request.PremioDTORequest;
import com.senac.games.dto.response.PremioDTOResponse;
import com.senac.games.dto.response.PremioDTOUpdateResponse;
import com.senac.games.entity.Premio;
import com.senac.games.repository.PremioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PremioService {

    private final PremioRepository premioRepository;

    @Autowired
    private ModelMapper modelmapper;

    public PremioService(PremioRepository premioRepository, ModelMapper modelmapper) {
        this.premioRepository = premioRepository;
        this.modelmapper = modelmapper;
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
        return modelmapper.map(premioSave, PremioDTOResponse.class);
    }

    public PremioDTOResponse atualizarPremio(Integer premioId, PremioDTORequest premioDTORequest) {
        Premio premio = this.obterpremioPeloId(premioId);
        if (premio != null) {
            modelmapper.map(premioDTORequest, premio);
            Premio tempResponse = premioRepository.save(premio);
            return modelmapper.map(tempResponse, PremioDTOResponse.class);
        }
        return null;
    }

    public PremioDTOUpdateResponse atualizarStatusPremio(Integer premioId, PremioDTORequest premioDTOUpdateRequest) {
        Premio premio = this.obterpremioPeloId(premioId);
        if (premio != null) {
            premio.setStatus(premioDTOUpdateRequest.getStatus());
            Premio tempResponse = premioRepository.save(premio);
            return modelmapper.map(tempResponse, PremioDTOUpdateResponse.class);
        }
        return null;
    }

    public void apagarPremio(Integer premioId) {
        premioRepository.apagadoLogicoPremio(premioId);
    }
}