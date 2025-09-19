package com.senac.games.service;

import com.senac.games.dto.request.InscricaoDTORequest;
import com.senac.games.dto.response.InscricaoDTOResponse;
import com.senac.games.dto.response.InscricaoDTOUpdateResponse;
import com.senac.games.entity.Inscricao;
import com.senac.games.entity.Jogo;
import com.senac.games.entity.Participante;
import com.senac.games.repository.InscricaoRepository;
import com.senac.games.repository.JogoRepository;
import com.senac.games.repository.ParticipanteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InscricaoService {

    private final InscricaoRepository inscricaoRepository;
    private final ParticipanteRepository participanteRepository;
    private final JogoRepository jogoRepository;

    @Autowired
    private ModelMapper modelmapper;

    public InscricaoService(InscricaoRepository inscricaoRepository, ModelMapper modelmapper, ParticipanteRepository participanteRepository, JogoRepository jogoRepository) {
        this.inscricaoRepository = inscricaoRepository;
        this.modelmapper = modelmapper;
        this.participanteRepository = participanteRepository;
        this.jogoRepository = jogoRepository;
    }

    public List<Inscricao> listarInscricoes() {
        return this.inscricaoRepository.listarInscricoes();
    }

    public Inscricao obterinscricaoPeloId(Integer inscricaoId) {
        return this.inscricaoRepository.obterInscricaoPeloId(inscricaoId);
    }

    public InscricaoDTOResponse criarInscricao(InscricaoDTORequest inscricaoDTORequest) {
        Inscricao inscricao = modelmapper.map(inscricaoDTORequest, Inscricao.class);
        inscricao.setId(null);

        // Busca e associa o Participante
        Integer participanteId = inscricaoDTORequest.getIdParticipante();
        Participante participante = participanteRepository.findById(participanteId)
                .orElseThrow(() -> new EntityNotFoundException("Participante com ID " + participanteId + " não encontrado."));
        inscricao.setParticipante(participante);

        // Busca e associa o Jogo
        Integer jogoId = inscricaoDTORequest.getJogoId(); // Use o novo campo do DTO
        Jogo jogo = jogoRepository.findById(jogoId)
                .orElseThrow(() -> new EntityNotFoundException("Jogo com ID " + jogoId + " não encontrado."));
        inscricao.setJogo(jogo); // Defina o Jogo na sua entidade Inscricao

        Inscricao inscricaoSalva = this.inscricaoRepository.save(inscricao);
        return modelmapper.map(inscricaoSalva, InscricaoDTOResponse.class);
    }

    public InscricaoDTOResponse atualizarInscricao(Integer inscricaoId, InscricaoDTORequest inscricaoDTORequest) {
        // 1. Obter a entidade Inscricao existente pelo ID
        Inscricao inscricaoExistente = this.inscricaoRepository.obterInscricaoPeloId(inscricaoId);

        if (inscricaoExistente != null) {
            // 2. ATUALIZE APENAS OS CAMPOS PERMITIDOS.
            // EVITE USAR modelmapper.map(inscricaoDTORequest, inscricaoExistente);
            inscricaoExistente.setData(inscricaoDTORequest.getData()); // Se aplicável
            inscricaoExistente.setStatus(inscricaoDTORequest.getStatus());

            // 3. Gerencie os relacionamentos com Participante e Jogo manualmente, se necessário.
            if (inscricaoDTORequest.getIdParticipante() != null) {
                Participante participante = participanteRepository.findById(inscricaoDTORequest.getIdParticipante())
                        .orElseThrow(() -> new EntityNotFoundException("Participante não encontrado com o ID: " + inscricaoDTORequest.getIdParticipante()));
                inscricaoExistente.setParticipante(participante);
            }

            if (inscricaoDTORequest.getJogoId() != null) {
                Jogo jogo = jogoRepository.findById(inscricaoDTORequest.getJogoId())
                        .orElseThrow(() -> new EntityNotFoundException("Jogo não encontrado com o ID: " + inscricaoDTORequest.getJogoId()));
                inscricaoExistente.setJogo(jogo);
            }

            // 4. Salve a entidade atualizada
            Inscricao tempResponse = inscricaoRepository.save(inscricaoExistente);

            return modelmapper.map(tempResponse, InscricaoDTOResponse.class);
        }
        return null; // ou lance uma exceção apropriada como EntityNotFoundException
    }

    public InscricaoDTOUpdateResponse atualizarStatusInscricao(Integer inscricaoId, InscricaoDTORequest inscricaoDTOUpdateRequest) {
        Inscricao inscricao = this.obterinscricaoPeloId(inscricaoId);
        if (inscricao != null) {
            inscricao.setStatus(inscricaoDTOUpdateRequest.getStatus());
            Inscricao tempResponse = inscricaoRepository.save(inscricao);
            return modelmapper.map(tempResponse, InscricaoDTOUpdateResponse.class);
        }
        return null;
    }

    public void apagarInscricao(Integer inscricaoId) {
        inscricaoRepository.apagadoLogicoInscricao(inscricaoId);
    }
}