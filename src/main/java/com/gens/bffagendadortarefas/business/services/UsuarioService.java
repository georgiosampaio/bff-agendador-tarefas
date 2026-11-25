package com.gens.bffagendadortarefas.business.services;

import com.gens.bffagendadortarefas.business.dto.in.EnderecoRequestDTO;
import com.gens.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.gens.bffagendadortarefas.business.dto.in.TelefoneRequestDTO;
import com.gens.bffagendadortarefas.business.dto.in.UsuarioRequestDTO;
import com.gens.bffagendadortarefas.business.dto.out.EnderecoResponseDTO;
import com.gens.bffagendadortarefas.business.dto.out.TelefoneResponseDTO;
import com.gens.bffagendadortarefas.business.dto.out.UsuarioResponseDTO;
import com.gens.bffagendadortarefas.infraestructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient usuarioClient;


    public UsuarioResponseDTO salvarUsuario(UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioClient.salvarUsuario(usuarioRequestDTO);
    }

    public String login(LoginRequestDTO loginRequestDTO) {
        return usuarioClient.login(loginRequestDTO);
    }

    public UsuarioResponseDTO buscarUsuarioPorEmail(String email, String token) {
        return usuarioClient.buscarUsuarioPorEmail(email, token);
    }

    public void deletarUsuarioPorEmail(String email, String token) {
        usuarioClient.deletarUsuarioPorEmail(email, token);
    }

    public UsuarioResponseDTO atualizarDadosUsuario(String token, UsuarioRequestDTO usuarioRequestDTO) {
        return usuarioClient.atualizarDadosUsuario(usuarioRequestDTO, token);
    }

    public EnderecoResponseDTO atualizaEndereco(Long idEndereco, EnderecoRequestDTO enderecoRequestDTO, String token) {
        return usuarioClient.atualizaEndereco(enderecoRequestDTO, idEndereco, token);
    }

    public TelefoneResponseDTO atualizaTelefone(Long idTelefone, TelefoneRequestDTO telefoneRequestDTO, String token) {
        return usuarioClient.atualizaTelefone(telefoneRequestDTO, idTelefone, token);
    }

    public EnderecoResponseDTO cadastraEndereco(String token, EnderecoRequestDTO enderecoRequestDTO) {
        return usuarioClient.cadastraEndereco(enderecoRequestDTO, token);
    }

    public TelefoneResponseDTO cadastraTelefone(String token, TelefoneRequestDTO telefoneRequestDTO) {
        return usuarioClient.cadastraTelefone(telefoneRequestDTO, token);
    }


}
