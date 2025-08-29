package com.senac.games.service;

import com.senac.games.dto.request.ParticipanteDTORequest;
import com.senac.games.dto.response.ParticipanteDTOResponse;
import com.senac.games.dto.response.ParticipanteDTOUpdateResponse;
import com.senac.games.entity.Participante;
import com.senac.games.repository.ParticipanteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    @Autowired
    private ModelMapper modelmapper;

    public ParticipanteService(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    public List<Participante> listarParticipantes() {
        return this.participanteRepository.findAll();


    }

    public Participante listarPorParticipantId(Integer participanteId) {
        return this.participanteRepository.findById(participanteId).orElse(null);
    }

    public ParticipanteDTOResponse criarParticipante(ParticipanteDTORequest participanteDTORequest) {

        Participante participante = modelmapper.map(participanteDTORequest, Participante.class);
        Participante participanteSave = this.participanteRepository.save(participante);
        ParticipanteDTOResponse participanteDTOResponse = modelmapper.map(participanteSave, ParticipanteDTOResponse.class);
        return participanteDTOResponse;

        /* participante.setNome(participanteDTO.getNome());
        participante.setEmail(participanteDTO.getEmail());
        participante.setIdentificacao(participanteDTO.getIdentificacao());
        participante.setEndereco(participanteDTO.getEndereco());
        participante.setStatus(participanteDTO.getStatus());

        Participante participanteSave = this.participanteRepository.save(participante);

        ParticipanteDTOResponse participanteDTOResponse = new ParticipanteDTOResponse();
        participanteDTOResponse.setId(participanteSave.getId());
        participanteDTOResponse.setNome(participanteSave.getNome());
        participanteDTOResponse.setEmail(participanteSave.getEmail());
        participanteDTOResponse.setIdentificacao(participanteSave.getIdentificacao());
        participanteDTOResponse.setEndereco(participanteSave.getEndereco());
        participanteDTOResponse.setStatus(participanteSave.getStatus());*/


    }

    public ParticipanteDTOResponse atualizarParticipante(Integer participanteId, ParticipanteDTORequest participanteDTORequest) {
        //Antes de atualizar busca se existe o registro a ser atualizado
        Participante participante = this.listarPorParticipantId(participanteId);
        //Se encontra o registro a ser atualizado
        if (participante != null) {
            //copia os dados a serem atualizados do DTO de entrada para um objeto do tipo participante
            //que é compatível com o repository para atualizar
            modelmapper.map(participanteDTORequest, Participante.class);

            //Com o objeto no formato correto tipo "participante"  o comando "save" salva
            //no banco de dados o objeto atualizado
            Participante tempResponse = participanteRepository.save(participante);
            return modelmapper.map(tempResponse, ParticipanteDTOResponse.class);
        } else {
            return null;
        }

    }

    public ParticipanteDTOUpdateResponse atualizarStatusParticipante(Integer participanteId, ParticipanteDTORequest participanteDTOUpdateRequest) {
        Participante participante = this.listarPorParticipantId(participanteId);
        if(participante != null) {
            participante.setStatus(participanteDTOUpdateRequest.getStatus());

            Participante tempReponse = participanteRepository.save(participante);

            return modelmapper.map(tempReponse, ParticipanteDTOUpdateResponse.class);
        }
        else {return null;}



    }

}
