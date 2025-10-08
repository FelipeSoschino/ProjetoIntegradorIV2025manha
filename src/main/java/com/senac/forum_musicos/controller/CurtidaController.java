package com.senac.forum_musicos.controller;

import com.senac.forum_musicos.DTO.request.CurtidaDTORequest;
import com.senac.forum_musicos.DTO.request.CurtidaDTOUpdateRequest;
import com.senac.forum_musicos.DTO.response.CurtidaDTOResponse;
import com.senac.forum_musicos.DTO.response.CurtidaDTOUpdateResponse;
import com.senac.forum_musicos.entity.Curtida;
import com.senac.forum_musicos.service.CurtidaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/curtida")
@Tag(name = "Curtida",description = "api para gerenciamento das curtidas")
public class CurtidaController {

    private final CurtidaService curtidaService;

    public CurtidaController(CurtidaService curtidaService){
        this.curtidaService = curtidaService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Listar Curtidas",description = "")
    public ResponseEntity<List<Curtida>> listarCurtidas(){
        return ResponseEntity.ok(this.curtidaService.listarCurtidas());
    }

    @PostMapping("/criar")
    public ResponseEntity<CurtidaDTOResponse> criarCurtida(@Valid @RequestBody CurtidaDTORequest curtidaDTORequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.curtidaService.criarCurtida(curtidaDTORequest));
    }
    @PatchMapping("atualizaStatus/{curtitaId}")
    public ResponseEntity<CurtidaDTOUpdateResponse> atualizaCurtidaStatus(@PathVariable("curtidaId")Integer curtidaId,
                                                                          @RequestBody CurtidaDTOUpdateRequest curtidaDTOUpdateRequest){
        return ResponseEntity.ok(this.curtidaService.atualizarStatusCurtida(curtidaId,curtidaDTOUpdateRequest));
    }
    @DeleteMapping("/deletarCurtida/{curtidaId}")
    public void deletarCurtida(@PathVariable("curtidaId")Integer curtidaId){
        this.curtidaService.apagarCurtida(curtidaId);
    }
}
