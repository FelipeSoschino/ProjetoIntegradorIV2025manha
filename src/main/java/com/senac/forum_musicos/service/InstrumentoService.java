package com.senac.forum_musicos.service;

import com.senac.forum_musicos.DTO.request.InstrumentoDTORequest;
import com.senac.forum_musicos.DTO.request.InstrumentoDTOUpdateRequest;
import com.senac.forum_musicos.DTO.request.PostDTORequest;
import com.senac.forum_musicos.DTO.request.PostDTOUpdateRequest;
import com.senac.forum_musicos.DTO.response.InstrumentoDTOResponse;
import com.senac.forum_musicos.DTO.response.InstrumentoDTOUpdateResponse;
import com.senac.forum_musicos.DTO.response.PostDTOResponse;
import com.senac.forum_musicos.DTO.response.PostDTOUpdateResponse;
import com.senac.forum_musicos.entity.Instrumento;
import com.senac.forum_musicos.entity.Post;
import com.senac.forum_musicos.repository.InstrumentoRepository;
import com.senac.forum_musicos.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstrumentoService {

    private final InstrumentoRepository instrumentoRepository;
    private final UsuarioRepository usuarioRepository;

    public InstrumentoService(InstrumentoRepository instrumentoRepository,UsuarioRepository usuarioRepository){
        this.instrumentoRepository = instrumentoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    public List<Instrumento> listarInstrumentos(){
        return this.instrumentoRepository.listarInstrumentos();
    }

    public List<Instrumento> listarInstrumentosUsuario(Integer usuarioId){return this.instrumentoRepository.listarInstrumentosUsuario(usuarioId);}

    public Instrumento listarInstrumentoPorId(Integer instrumentoId){
        return this.instrumentoRepository.listarInstrumentoPorId(instrumentoId);
    }


    public InstrumentoDTOResponse criarInstrumento(InstrumentoDTORequest instrumentoDTORequest){
        Instrumento instrumento = new Instrumento();
        instrumento.setNome(instrumentoDTORequest.getNome());
        instrumento.setUsuario(this.usuarioRepository.listarUsuarioPorId(instrumentoDTORequest.getIdUsuario()));
        instrumento.setStatus(1);

        Instrumento instrumentoSave = this.instrumentoRepository.save(instrumento);
        InstrumentoDTOResponse instrumentoDTOResponse = modelMapper.map(instrumentoSave, InstrumentoDTOResponse.class);

        return instrumentoDTOResponse;}

    public InstrumentoDTOResponse atualizarInstrumento(Integer instrumentoId, InstrumentoDTORequest instrumentoDTORequest){
        Instrumento instrumento = this.listarInstrumentoPorId(instrumentoId);
        if(instrumento!=null){
            modelMapper.map(instrumentoDTORequest,instrumento);
            Instrumento instrumentoSave = this.instrumentoRepository.save(instrumento);
            InstrumentoDTOResponse instrumentoDTOResponse = modelMapper.map(instrumentoSave,InstrumentoDTOResponse.class);
            return instrumentoDTOResponse;
        }
        else {return null;}
    }

    public InstrumentoDTOUpdateResponse atualizarStatusInstrumento(Integer instrumentoId, InstrumentoDTOUpdateRequest instrumentoDTOUpdateRequest) {
        Instrumento instrumento = this.listarInstrumentoPorId(instrumentoId);
        if (instrumento != null) {
            modelMapper.map(instrumentoDTOUpdateRequest, instrumento);
            Instrumento instrumentoSave = this.instrumentoRepository.save(instrumento);

            InstrumentoDTOUpdateResponse instrumentoDTOUpdateResponse = modelMapper.map(instrumentoSave, InstrumentoDTOUpdateResponse.class);
            return instrumentoDTOUpdateResponse;
        } else
            return null;
    }

    public void apagarInstrumento(Integer instrumentoId){
        this.instrumentoRepository.apagarInstrumento(instrumentoId);
    }
}
