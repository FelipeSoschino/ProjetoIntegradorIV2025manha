package com.senac.forum_musicos.controller;

import com.senac.forum_musicos.DTO.request.TopicoDTORequest;
import com.senac.forum_musicos.DTO.request.TopicoDTOUpdataRequest;
import com.senac.forum_musicos.DTO.response.TopicoDTOResponse;
import com.senac.forum_musicos.DTO.response.TopicoDTOUpdateResponse;
import com.senac.forum_musicos.entity.Post;
import com.senac.forum_musicos.entity.Topico;
import com.senac.forum_musicos.service.TopicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/topico")
@Tag(name="Topico", description = "API para gerenciamento dos Tópicos")
public class TopicoController {

    private TopicoService topicoService;

    public TopicoController(TopicoService topicoService){
        this.topicoService = topicoService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar Tópicos", description = "End point para listar todos os tópicos")
    public ResponseEntity<List<Topico>> listarTopicos(){
        return ResponseEntity.ok(this.topicoService.listarTopicos());
    }

    @GetMapping("/listarPorTopicoId/{topicoId}")
    @Operation(summary = "Listar Topico por Id", description = "End point para listar um topico dado seu ID")
    public ResponseEntity<Topico> listarTopicoPorId(@PathVariable("topicoId") Integer topicoId){
        Topico topico = topicoService.listarTopicoPorId(topicoId);
        if(topico != null){
            return ResponseEntity.ok(topico);
        }
        else{return ResponseEntity.noContent().build();}

    }

    @PostMapping("/criarTopico")
    @Operation(summary = "Criar Tópico",description = "End point para criação de um tópico")
    public ResponseEntity<TopicoDTOResponse> criarTopico(@Valid @RequestBody TopicoDTORequest topicoDTORequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.topicoService.criarTopico(topicoDTORequest));
    }

//    @PostMapping("/criarTopico/{usuarioId}")
//    @Operation(summary = "Criar Tópico",description = "End point para criação de um tópico")
//    public ResponseEntity<TopicoDTOResponse> criarTopico(@PathVariable("usuarioId")Integer usuarioId, @RequestBody TopicoDTORequest topicoDTORequest){
//        return ResponseEntity.status(HttpStatus.CREATED).body(this.topicoService.criarTopico(usuarioId,topicoDTORequest));
//    }


    @PutMapping("/atualizarTopico/{topicoId}")
    @Operation(summary = "Atualizar Tópico", description = "End point para atualizar um tópico já existente")
    public ResponseEntity<TopicoDTOResponse> atualizarTopico(@PathVariable("topicoId")Integer topicoId, @RequestBody TopicoDTORequest topicoDTORequest){
        Topico topico = topicoService.listarTopicoPorId(topicoId);
        if(topico!= null){
            return ResponseEntity.ok(this.topicoService.atualizarTopico(topicoId,topicoDTORequest));
        }
        else
            return ResponseEntity.noContent().build();

    }

    @GetMapping("/listarPorUsuarioId/{usuarioId}")
    @Operation(summary = "Listar Posts pelo Id de um Usuário", description = "End point para listar uma lista de posts de um usuário dado seu Id")
    public ResponseEntity<List<Topico>> listarPostsPorUsuarioId(@PathVariable("usuarioId")Integer usuarioId){
        List<Topico> listaTopico = topicoService.listarTopicosUsuario(usuarioId);
        if(listaTopico ==null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.ok(listaTopico);
    }

    @PatchMapping("/atualizarStatusTopico/{topicoId}")
    @Operation(summary = "Atualizar Status do Tópico", description = "End point para atualizar o status de um tópico já existente")
    public ResponseEntity<TopicoDTOUpdateResponse> atualizarTopicoStatus(@PathVariable("topicoId")Integer topicoId,
                                                                         @RequestBody TopicoDTOUpdataRequest topicoDTORequest){
        return ResponseEntity.ok(this.topicoService.atualizarStatusTopico(topicoId,topicoDTORequest));}

    @DeleteMapping("Deletar/{topicoId}")
    @Operation(summary = "Remover topico", description = "Endpoint para remover um topico dado seu ID")
    public void apagarTopico(@PathVariable("topicoId") Integer topicoId){
        this.topicoService.apagarTopico(topicoId);
    }




}
