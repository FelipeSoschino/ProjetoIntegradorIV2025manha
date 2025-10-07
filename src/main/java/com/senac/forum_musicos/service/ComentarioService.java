package com.senac.forum_musicos.service;
import com.senac.forum_musicos.DTO.request.ComentarioDTORequest;
import com.senac.forum_musicos.DTO.request.ComentarioDTOUpdateRequest;
import com.senac.forum_musicos.DTO.response.ComentarioDTOResponse;
import com.senac.forum_musicos.DTO.response.ComentarioDTOUpdateResponse;
import com.senac.forum_musicos.entity.Comentario;
import com.senac.forum_musicos.repository.ComentarioRepository;
import com.senac.forum_musicos.repository.PostRepository;
import com.senac.forum_musicos.repository.TopicoRepository;
import com.senac.forum_musicos.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;
    private final PostRepository postRepository;
    private final TopicoRepository topicoRepository;

    public ComentarioService(ComentarioRepository comentarioRepository,UsuarioRepository usuarioRepository, PostRepository postRepository,
                             TopicoRepository topicoRepository){
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
        this.postRepository = postRepository;
        this.topicoRepository = topicoRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    public List<Comentario> listarComentarios(){
        return this.comentarioRepository.listarComentarios();
    }

    public Comentario listarComentarioPorId(Integer cometarioId){
        return this.comentarioRepository.listarComentarioPorId(cometarioId);

    }


    public ComentarioDTOResponse criarComentario(ComentarioDTORequest comentarioDTORequest){


        Comentario comentario = new Comentario();
        comentario.setStatus(comentarioDTORequest.getStatus());
        comentario.setTexto(comentarioDTORequest.getTexto());
        comentario.setUsuario(this.usuarioRepository.listarUsuarioPorId(comentarioDTORequest.getUsuario()));
        comentario.setPost(this.postRepository.listarPostPorId(comentarioDTORequest.getPost()));
        comentario.setTopico(this.topicoRepository.listarTopicoPorId(comentarioDTORequest.getTopico()));
        if (comentario.getTopico() != null){
            comentario.setComentario(this.comentarioRepository.findById(comentarioDTORequest.getResposta()).orElse(null));}

        Comentario comentarioSave = this.comentarioRepository.save(comentario);
//
//        ComentarioDTOResponse comentarioDTOResponse = new ComentarioDTOResponse();
//        comentarioDTOResponse.setId(comentarioSave.getId());
//        comentarioDTOResponse.setStatus(comentarioSave.getStatus());
//        comentarioDTOResponse.setTexto(comentarioSave.getTexto());
//        comentarioDTOResponse.setUsuario(comentarioSave.getIdUsuario());
//        comentarioDTOResponse.setPost(comentarioSave.getIdPost());
//        comentarioDTOResponse.setTopico(comentarioSave.getIdTopico());
//        comentarioDTOResponse.setResposta(comentarioSave.getIdResposta());
        ComentarioDTOResponse comentarioDTOResponse = modelMapper.map(comentarioSave, ComentarioDTOResponse.class);
        return comentarioDTOResponse;}

    public ComentarioDTOResponse atualizarComentario(Integer comentarioId, ComentarioDTORequest comentarioDTORequest){
        Comentario comentario = this.listarComentarioPorId(comentarioId);
        if(comentario!=null){
            modelMapper.map(comentarioDTORequest,comentario);
            Comentario comentarioSave = this.comentarioRepository.save(comentario);
            ComentarioDTOResponse comentarioDTOResponse = modelMapper.map(comentarioSave,ComentarioDTOResponse.class);
            return comentarioDTOResponse;
        }
        else {return null;}
    }

    public ComentarioDTOUpdateResponse atualizarStatusComentario(Integer comentarioId, ComentarioDTOUpdateRequest comentarioDTOUpdateRequest) {
        Comentario comentario = this.listarComentarioPorId(comentarioId);
        if (comentario != null) {
            modelMapper.map(comentarioDTOUpdateRequest, comentario);
            Comentario comentarioSave = this.comentarioRepository.save(comentario);

            ComentarioDTOUpdateResponse comentarioDTOUpdateResponse = modelMapper.map(comentarioSave, ComentarioDTOUpdateResponse.class);
            return comentarioDTOUpdateResponse;
        } else
            return null;
    }

    public void apagarComentario(Integer comentarioId){
        this.comentarioRepository.apagarComentario(comentarioId);
    }


}
