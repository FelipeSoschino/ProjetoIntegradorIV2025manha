package com.senac.forum_musicos.controller;

import com.senac.forum_musicos.DTO.request.InstrumentoDTORequest;
import com.senac.forum_musicos.DTO.request.InstrumentoDTOUpdateRequest;
import com.senac.forum_musicos.DTO.response.InstrumentoDTOResponse;
import com.senac.forum_musicos.DTO.response.InstrumentoDTOUpdateResponse;
import com.senac.forum_musicos.entity.Instrumento;
import com.senac.forum_musicos.entity.Usuario;
import com.senac.forum_musicos.service.InstrumentoService;
import com.senac.forum_musicos.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/instrumento")
@Tag(name="Instrumento",description = "Api para gerenciamento dos intrsumentos")
public class InstrumentoController {

    private InstrumentoService instrumentoService;
    private UsuarioService usuarioService;

    public InstrumentoController(InstrumentoService instrumentoService,UsuarioService usuarioService){
        this.instrumentoService = instrumentoService;
        this.usuarioService = usuarioService;
    }

    @PostMapping("/criar")
    public ResponseEntity<InstrumentoDTOResponse> criarInstrumento(@Valid @RequestBody InstrumentoDTORequest instrumentoDTORequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.instrumentoService.criarInstrumento(instrumentoDTORequest));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Instrumento>> listarInsturmentos(){
        return ResponseEntity.ok(this.instrumentoService.listarInstrumentos());
    }

    @GetMapping("/listarPorId/{instrumentoId}")
    public ResponseEntity<Instrumento> listarInstrumentoPorId(@PathVariable("instrumentoId")Integer instrumentoId){
        Instrumento instrumento = instrumentoService.listarInstrumentoPorId(instrumentoId);
        if(instrumento != null){
            return ResponseEntity.ok(instrumento);
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/listarPorUsuario/{usuarioId}")
    public ResponseEntity<List<Instrumento>> listarInstrumentoUsuario(@PathVariable("usuarioId")Integer usuarioId){
        Usuario usuario = usuarioService.listarUsuarioPorId(usuarioId);
        if(usuario != null){
            return ResponseEntity.ok(instrumentoService.listarInstrumentosUsuario(usuarioId));
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/atualizarInstrumento/{instrumentoId}")
    public ResponseEntity<InstrumentoDTOResponse> atualizaInstrumento(@PathVariable("instrumentoId") Integer instrumentoId,
                                                                      @RequestBody InstrumentoDTORequest instrumentoDTORequest){
        return ResponseEntity.ok(this.instrumentoService.atualizarInstrumento(instrumentoId,instrumentoDTORequest));
    }

    @PatchMapping("/atualizarInstrumentoStatus/{instrumentoId}")
    public ResponseEntity<InstrumentoDTOUpdateResponse> atualizaInstrumentoStatus(@PathVariable("instrumentoId") Integer instrumentoId,
                                                                            @RequestBody InstrumentoDTOUpdateRequest instrumentoDTOUpdateRequest){
        return ResponseEntity.ok(this.instrumentoService.atualizarStatusInstrumento(instrumentoId,instrumentoDTOUpdateRequest));
    }

    @DeleteMapping("/deletarInstrumento/{instrumentoId}")
    public void deletarInstrumento(@PathVariable("instrumentoId")Integer instrumentoId){
        this.instrumentoService.apagarInstrumento(instrumentoId);
    }


}
