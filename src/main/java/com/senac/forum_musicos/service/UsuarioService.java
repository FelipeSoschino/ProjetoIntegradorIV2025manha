package com.senac.forum_musicos.service;

import com.senac.forum_musicos.DTO.request.UsuarioDTOLoginRequest;
import com.senac.forum_musicos.DTO.request.UsuarioDTORequest;
import com.senac.forum_musicos.DTO.request.UsuarioDTOUpdateRequest;
import com.senac.forum_musicos.DTO.response.UsuarioDTOLoginResponse;
import com.senac.forum_musicos.DTO.response.UsuarioDTOResponse;
import com.senac.forum_musicos.DTO.response.UsuarioDTOUpdateResponse;
import com.senac.forum_musicos.config.SecurityConfiguration;
import com.senac.forum_musicos.entity.Role;
import com.senac.forum_musicos.entity.RoleName;
import com.senac.forum_musicos.entity.Usuario;
import com.senac.forum_musicos.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    public List<Usuario> listarUsuarios(){
        return this.usuarioRepository.listarUsuarios();
    }

    public Usuario listarUsuarioPorId(Integer usuarioId){
        return this.usuarioRepository.listarUsuarioPorId(usuarioId);
    }

    public UsuarioDTOLoginResponse login(UsuarioDTOLoginRequest usuarioDTOLoginRequest){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(usuarioDTOLoginRequest.getEmail(), usuarioDTOLoginRequest.getSenha());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        UsuarioDTOLoginResponse usuarioDTOLoginResponse = new UsuarioDTOLoginResponse();
        usuarioDTOLoginResponse.setId(userDetails.getIdUsuario());
        usuarioDTOLoginResponse.setNome(userDetails.getNomeUsuario());
        usuarioDTOLoginResponse.setToken(jwtTokenService.generateToken(userDetails));
        return usuarioDTOLoginResponse;
    }

    public UsuarioDTOResponse criarUsuario(UsuarioDTORequest usuarioDTORequest){
        List<Role> roles = new ArrayList<Role>();
        for (int i=0; i<usuarioDTORequest.getRoleList().size(); i++){
            Role role = new Role();
            role.setName(RoleName.valueOf(usuarioDTORequest.getRoleList().get(i)));
            roles.add(role);
        }

        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTORequest.getEmail());
        usuario.setBio(usuarioDTORequest.getBio());
        usuario.setData(usuarioDTORequest.getData());
        usuario.setFoto(usuarioDTORequest.getFoto());
        usuario.setNome(usuarioDTORequest.getNome());
        usuario.setSenha(securityConfiguration.passwordEncoder().encode(usuarioDTORequest.getSenha()));
        usuario.setStatus(1);
        usuario.setRoles(roles);

        Usuario usuarioSave = this.usuarioRepository.save(usuario);
        UsuarioDTOResponse usuarioDTOResponse = new UsuarioDTOResponse();
        usuarioDTOResponse.setId(usuarioSave.getId());
        usuarioDTOResponse.setBio(usuarioSave.getBio());
        usuarioDTOResponse.setNome(usuarioSave.getNome());
        usuarioDTOResponse.setEmail(usuarioSave.getEmail());
        usuarioDTOResponse.setData(usuarioSave.getData());
        usuarioDTOResponse.setStatus(usuarioSave.getStatus());
        usuarioDTOResponse.setFoto(usuarioSave.getFoto());
        return usuarioDTOResponse;}

    public UsuarioDTOResponse atualizarUsuario(Integer usuarioId, UsuarioDTORequest usuarioDTORequest){
        Usuario usuario = this.listarUsuarioPorId(usuarioId);
        if(usuario!=null){
            modelMapper.map(usuarioDTORequest,usuario);
            Usuario usuarioSave = this.usuarioRepository.save(usuario);
            UsuarioDTOResponse usuarioDTOResponse = modelMapper.map(usuarioSave,UsuarioDTOResponse.class);
            return usuarioDTOResponse;
        }
        else {return null;}
    }

    public UsuarioDTOUpdateResponse atualizarStatusUsuario(Integer usuarioId, UsuarioDTOUpdateRequest usuarioDTOUpdateRequest) {
        Usuario usuario = this.listarUsuarioPorId(usuarioId);
        if (usuario != null) {
            modelMapper.map(usuarioDTOUpdateRequest, usuario);
            Usuario usuarioSave = this.usuarioRepository.save(usuario);

            UsuarioDTOUpdateResponse usuarioDTOUpdateResponse = modelMapper.map(usuarioSave, UsuarioDTOUpdateResponse.class);
            return usuarioDTOUpdateResponse;
        } else
            return null;
    }

    public void apagarUsuario(Integer usuarioId){
        this.usuarioRepository.apagarUsuario(usuarioId);
    }
}





