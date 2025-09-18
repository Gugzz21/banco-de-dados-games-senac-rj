package com.senac.games.service;

import com.senac.games.dto.request.PatrocinadorDTORequest;
import com.senac.games.dto.response.PatrocinadorDTOResponse;
import com.senac.games.dto.response.PatrocinadorDTOUpdateResponse;
import com.senac.games.entity.Patrocinador;
import com.senac.games.repository.PatrocinadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatrocinadorService {

    private final PatrocinadorRepository patrocinadorRepository;

    @Autowired
    private ModelMapper modelmapper;

    public PatrocinadorService(PatrocinadorRepository patrocinadorRepository, ModelMapper modelmapper) {
        this.patrocinadorRepository = patrocinadorRepository;
        this.modelmapper = modelmapper;
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
        return modelmapper.map(patrocinadorSave, PatrocinadorDTOResponse.class);
    }

    public PatrocinadorDTOResponse atualizarPatrocinador(Integer patrocinadorId, PatrocinadorDTORequest patrocinadorDTORequest) {
        Patrocinador patrocinador = this.obterPatrocinadorPeloId(patrocinadorId);
        if (patrocinador != null) {
            modelmapper.map(patrocinadorDTORequest, patrocinador);
            Patrocinador tempResponse = patrocinadorRepository.save(patrocinador);
            return modelmapper.map(tempResponse, PatrocinadorDTOResponse.class);
        }
        return null;
    }

    public PatrocinadorDTOUpdateResponse atualizarStatusPatrocinador(Integer patrocinadorId, PatrocinadorDTORequest patrocinadorDTOUpdateRequest) {
        Patrocinador patrocinador = this.obterPatrocinadorPeloId(patrocinadorId);
        if (patrocinador != null) {
            patrocinador.setStatus(patrocinadorDTOUpdateRequest.getStatus());
            Patrocinador tempResponse = patrocinadorRepository.save(patrocinador);
            return modelmapper.map(tempResponse, PatrocinadorDTOUpdateResponse.class);
        }
        return null;
    }

    public void apagarPatrocinador(Integer patrocinadorId) {
        patrocinadorRepository.apagadoLogicoPatrocinador(patrocinadorId);
    }
}