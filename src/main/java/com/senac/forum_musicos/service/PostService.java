package com.senac.forum_musicos.service;

import com.senac.forum_musicos.DTO.request.PostDTORequest;
import com.senac.forum_musicos.DTO.request.PostDTOUpdateRequest;
import com.senac.forum_musicos.DTO.request.UsuarioDTORequest;
import com.senac.forum_musicos.DTO.request.UsuarioDTOUpdateRequest;
import com.senac.forum_musicos.DTO.response.PostDTOResponse;
import com.senac.forum_musicos.DTO.response.PostDTOUpdateResponse;
import com.senac.forum_musicos.DTO.response.UsuarioDTOResponse;
import com.senac.forum_musicos.DTO.response.UsuarioDTOUpdateResponse;
import com.senac.forum_musicos.entity.Post;
import com.senac.forum_musicos.entity.Topico;
import com.senac.forum_musicos.entity.Usuario;
import com.senac.forum_musicos.repository.PostRepository;
import com.senac.forum_musicos.repository.TopicoRepository;
import com.senac.forum_musicos.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;
    private UsuarioRepository usuarioRepository;
    private TopicoRepository topicoRepository;

    public PostService(PostRepository postRepository,UsuarioRepository usuarioRepository, TopicoRepository topicoRepository){
        this.postRepository = postRepository;
        this.usuarioRepository = usuarioRepository;
        this.topicoRepository = topicoRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    public List<Post> listarPosts(){
        return this.postRepository.listarPosts();
    }
    public Post listarPostPorId(Integer postId){
        return this.postRepository.listarPostPorId(postId);
    }

    public List<Post> listarPostsUsuario(Integer usuarioId){ return this.postRepository.listarPostsUsuadio(usuarioId);

    }


    public PostDTOResponse criarPost(PostDTORequest postDTORequest){
//        Usuario usuario = usuarioRepository.listarUsuarioPorId((postDTORequest.getUsuario()));
        Topico topico = topicoRepository.listarTopicoPorId(postDTORequest.getTopico());

        Post post = new Post();
        post.setTexto(postDTORequest.getTexto());
        post.setData(postDTORequest.getData());
        post.setStatus(postDTORequest.getStatus());
        post.setUsuario(usuarioRepository.listarUsuarioPorId(postDTORequest.getUsuario()));
        if (topico !=null){
            post.setTopico(topico);}

        Post postSave = this.postRepository.save(post);
//        PostDTOResponse postDTOResponse = new PostDTOResponse();
        PostDTOResponse postDTOResponse = modelMapper.map(postSave, PostDTOResponse.class);
//        postDTOResponse.setTexto(postSave.getTexto());
//        postDTOResponse.setData(postSave.getData());
//        postDTOResponse.setId(postSave.getId());
//        postDTOResponse.setStatus(postSave.getStatus());
//        postDTOResponse.setArquivo(postSave.getArquivo());
//        postDTOResponse.setTopico(postSave.getTopico().getId());
//        postDTOResponse.setUsuario(postSave.getUsuario().getId());
        return postDTOResponse;}

    public PostDTOResponse atualizarPost(Integer postId, PostDTORequest postDTORequest){
        Post post = this.listarPostPorId(postId);
        if(post!=null){
            modelMapper.map(postDTORequest,post);
            Post postSave = this.postRepository.save(post);
            PostDTOResponse postDTOResponse = modelMapper.map(postSave,PostDTOResponse.class);
            return postDTOResponse;
        }
        else {return null;}
    }

    public PostDTOUpdateResponse atualizarStatusPost(Integer postId, PostDTOUpdateRequest postDTOUpdateRequest) {
        Post post = this.listarPostPorId(postId);
        if (post != null) {
            modelMapper.map(postDTOUpdateRequest, post);
            Post postSave = this.postRepository.save(post);

            PostDTOUpdateResponse postDTOUpdateResponse = modelMapper.map(postSave, PostDTOUpdateResponse.class);
            return postDTOUpdateResponse;
        } else
            return null;
    }

    public void apagarPost(Integer postId){
        this.postRepository.apagarPost(postId);
    }
}
    
