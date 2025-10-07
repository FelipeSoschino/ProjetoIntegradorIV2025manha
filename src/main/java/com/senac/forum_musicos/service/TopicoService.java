package com.senac.forum_musicos.service;

import com.senac.forum_musicos.DTO.request.*;
import com.senac.forum_musicos.DTO.response.TopicoDTOResponse;
import com.senac.forum_musicos.DTO.response.TopicoDTOUpdateResponse;
import com.senac.forum_musicos.DTO.response.UsuarioDTOResponse;
import com.senac.forum_musicos.DTO.response.UsuarioDTOUpdateResponse;
import com.senac.forum_musicos.entity.Topico;
import com.senac.forum_musicos.entity.Usuario;
import com.senac.forum_musicos.repository.ParticipaRepository;
import com.senac.forum_musicos.repository.TopicoRepository;
import com.senac.forum_musicos.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoService {

    private final TopicoRepository topicoRepository;

    private final UsuarioRepository usuarioRepository;
    private final ParticipaService participaService;

    public TopicoService(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository, ParticipaService participaService){
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.participaService = participaService;
    }
    @Autowired
    private ModelMapper modelMapper;

    public List<Topico> listarTopicos(){
        return this.topicoRepository.listarTopicos();
    }

    public List<Topico> listarTopicosUsuario(Integer usuarioId){return this.topicoRepository.listarTopicosUsuario(usuarioId);}

    public Topico listarTopicoPorId(Integer topicoId) {
        return this.topicoRepository.listarTopicoPorId(topicoId);
    }


    public TopicoDTOResponse criarTopico(TopicoDTORequest topicoDTORequest){
//        Usuario usuario = usuarioRepository.findById(topicoDTORequest.getUsuario())
//                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Topico topico = new Topico();
        modelMapper.map(topicoDTORequest, topico);


        // Setar o usuário no tópico
        topico.setUsuario(usuarioRepository.listarUsuarioPorId(topicoDTORequest.getUsuario()));


        // Salvar
        Topico topicoSave = this.topicoRepository.save(topico);

        // Mapear para DTO de resposta
        ParticipaDTORequest participaDTORequest = new ParticipaDTORequest();
        participaDTORequest.setTopicoId(topicoSave.getId());
        participaDTORequest.setUsuarioId(topicoSave.getIdUsuario());
        participaDTORequest.setStatus(1);
        this.participaService.criarParticipa(participaDTORequest);
        return modelMapper.map(topicoSave, TopicoDTOResponse.class);
    }
//public TopicoDTOResponse criarTopico(Integer usuarioId,TopicoDTORequest topicoDTORequest){
//    Usuario usuario = usuarioRepository.findById(usuarioId)
//            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
//
//    Topico topico = modelMapper.map(topicoDTORequest, Topico.class);
//
//    // Setar o usuário no tópico
//    topico.setUsuario(usuario);
//
//
//    // Salvar
//    Topico topicoSave = this.topicoRepository.save(topico);
//
//    // Mapear para DTO de resposta
//    return modelMapper.map(topicoSave, TopicoDTOResponse.class);
//}

    public TopicoDTOResponse atualizarTopico(Integer topicoId, TopicoDTORequest topicoDTORequest){
        Topico topico = this.listarTopicoPorId(topicoId);
        if(topico!=null){
            modelMapper.map(topicoDTORequest,topico);
            Topico topicoSave = this.topicoRepository.save(topico);
            TopicoDTOResponse topicoDTOResponse = modelMapper.map(topicoSave,TopicoDTOResponse.class);
            return topicoDTOResponse;
        }
        else {return null;}
    }

    public TopicoDTOUpdateResponse atualizarStatusTopico(Integer topicoId, TopicoDTOUpdataRequest topicoDTOUpdateRequest) {
        Topico topico = this.listarTopicoPorId(topicoId);
        if (topico != null) {
            modelMapper.map(topicoDTOUpdateRequest, topico);
            Topico topicoSave = this.topicoRepository.save(topico);

            TopicoDTOUpdateResponse topicoDTOUpdateResponse = modelMapper.map(topicoSave, TopicoDTOUpdateResponse.class);
            return topicoDTOUpdateResponse;
        } else
            return null;
    }

    public void apagarTopico(Integer topicoId){
        this.topicoRepository.apagarTopico(topicoId);
    }
}
