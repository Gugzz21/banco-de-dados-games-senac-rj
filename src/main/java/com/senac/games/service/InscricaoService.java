package com.senac.games.service;

import com.senac.games.dto.request.InscricaoDTORequest;
import com.senac.games.dto.response.InscricaoDTOResponse;
import com.senac.games.dto.response.InscricaoDTOUpdateResponse;
import com.senac.games.entity.Inscricao;
import com.senac.games.entity.Inscricao;
import com.senac.games.repository.InscricaoRepository;
import com.senac.games.repository.InscricaoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscricaoService {
    private InscricaoRepository inscricaoRepository;

    @Autowired
    private ModelMapper modelmapper;

    public InscricaoService(InscricaoRepository inscricaoRepository) {
        this.inscricaoRepository = inscricaoRepository;
    }

    public InscricaoService() {
    }

    public List<Inscricao> listarInscricoes() {
        return this.inscricaoRepository.listarInscricoes();
    }

    public Inscricao obterinscricaoPeloId(Integer inscricaoId) {
        return this.inscricaoRepository.obterInscricaoPeloId(inscricaoId);
    }

    public InscricaoDTOResponse criarInscricao(InscricaoDTORequest inscricaoDTORequest) {
        Inscricao inscricao = modelmapper.map(inscricaoDTORequest, Inscricao.class);
        Inscricao inscricaoSave = this.inscricaoRepository.save(inscricao);
        InscricaoDTOResponse inscricaoDTOResponse = modelmapper.map(inscricaoSave, InscricaoDTOResponse.class);
        return inscricaoDTOResponse;

    }

    public InscricaoDTOResponse atualizarInscricao(Integer inscricaoId, InscricaoDTORequest inscricaoDTORequest) {
        Inscricao inscricao = this.obterinscricaoPeloId(inscricaoId);
        if (inscricao != null) {
            modelmapper.map(inscricaoDTORequest, Inscricao.class);
            Inscricao tempResponse = inscricaoRepository.save(inscricao);
            return modelmapper.map(tempResponse, InscricaoDTOResponse.class);
        } else {
            return null;
        }

    }

    public InscricaoDTOUpdateResponse atualizarStatusInscricao(Integer inscricaoId, InscricaoDTORequest inscricaoDTOUpdateRequest) {
        Inscricao inscricao = this.obterinscricaoPeloId(inscricaoId);
        if(inscricao != null) {
            inscricao.setStatus(inscricaoDTOUpdateRequest.getStatus());

            Inscricao tempReponse = inscricaoRepository.save(inscricao);

            return modelmapper.map(tempReponse, InscricaoDTOUpdateResponse.class);
        }
        else {return null;}

    }


    public void apagarInscricao(Integer inscricaoId) {
        inscricaoRepository.apagadoLogicoInscricao(inscricaoId);
    }

}
