package com.senac.forum_musicos.controller;

import com.senac.forum_musicos.DTO.request.InstrumentoDTORequest;
import com.senac.forum_musicos.DTO.response.InstrumentoDTOResponse;
import com.senac.forum_musicos.entity.Instrumento;
import com.senac.forum_musicos.service.InstrumentoService;
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

    public InstrumentoController(InstrumentoService instrumentoService){
        this.instrumentoService = instrumentoService;
    }

    @PostMapping
    public ResponseEntity<InstrumentoDTOResponse> criarInstrumento(@Valid @RequestBody InstrumentoDTORequest instrumentoDTORequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.instrumentoService.criarInstrumento(instrumentoDTORequest));
    }

    @GetMapping
    public ResponseEntity<List<Instrumento>> listarInsturmentos(){
        return ResponseEntity.ok(this.instrumentoService.listarInstrumentos());
    }
}
