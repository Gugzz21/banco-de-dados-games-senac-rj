package com.senac.games.service;

import com.senac.games.dto.JogoDTO;
import com.senac.games.dto.request.JogoDTORequest;
import com.senac.games.dto.response.JogoDTOResponse;
import com.senac.games.dto.response.JogoDTOUpdateResponse;
import com.senac.games.entity.Categoria;
import com.senac.games.entity.Jogo;
import com.senac.games.repository.CategoriaRepository;
import com.senac.games.repository.JogoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ModelMapper modelmapper;

    @Autowired
    public JogoService(JogoRepository jogoRepository, ModelMapper modelmapper, CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
        this.jogoRepository = jogoRepository;
        this.modelmapper = modelmapper;
    }

    public List<Jogo> listarJogos() {
        return this.jogoRepository.listarJogos();
    }

    public Jogo obterjogoPeloId(Integer jogoId) {
        return this.jogoRepository.obterJogoPeloId(jogoId);
    }

    public JogoDTOResponse criarJogo(JogoDTORequest jogoDTORequest) {
        Jogo jogo = modelmapper.map(jogoDTORequest, Jogo.class);

        Categoria categoria = categoriaRepository.findById(jogoDTORequest.getCategoriaId())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com o ID: " + jogoDTORequest.getCategoriaId()));

        jogo.setCategoria(categoria);

        Jogo jogoSalvo = this.jogoRepository.save(jogo);

        JogoDTOResponse jogoDTOResponse = modelmapper.map(jogoSalvo, JogoDTOResponse.class);
        return jogoDTOResponse;
    }


//    public JogoDTOResponse atualizarJogo(Integer jogoId, JogoDTORequest jogoDTORequest) {
//        Jogo jogo = this.obterjogoPeloId(jogoId);
//        if (jogo != null) {
//            modelmapper.map(jogoDTORequest, jogo);
//            Jogo tempResponse = jogoRepository.save(jogo);
//            return modelmapper.map(tempResponse, JogoDTOResponse.class);
//        } else {
//            return null;
//        }
//    }

    public JogoDTOUpdateResponse atualizarStatusJogo(Integer jogoId, JogoDTORequest jogoDTOUpdateRequest) {
        Jogo jogo = this.obterjogoPeloId(jogoId);
        if (jogo != null) {
            jogo.setStatus(jogoDTOUpdateRequest.getStatus());

            Jogo tempReponse = jogoRepository.save(jogo);

            return modelmapper.map(tempReponse, JogoDTOUpdateResponse.class);
        } else {
            return null;
        }
    }

    public void apagarJogo(Integer jogoId) {
        jogoRepository.apagadoLogicoJogo(jogoId);
    }

    @Transactional
    public JogoDTOResponse atualizarJogo(Integer id, JogoDTORequest jogoDTORequest) {
        Jogo jogo = jogoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jogo com id: " + id + " não encontrado"));

        jogo.setNome(jogoDTORequest.getNome());
        jogo.setStatus(jogoDTORequest.getStatus());

        if (jogoDTORequest.getCategoriaId() != 0) {
            Categoria categoria = categoriaRepository.findById(jogoDTORequest.getCategoriaId())
                    .orElseThrow(() -> new IllegalArgumentException("Categoria não encontrada com id: " + jogoDTORequest.getCategoriaId()));
            jogo.setCategoria(categoria);
        }

        Jogo jogoAtualizado = jogoRepository.save(jogo);

        return modelmapper.map(jogoAtualizado, JogoDTOResponse.class);
    }
}