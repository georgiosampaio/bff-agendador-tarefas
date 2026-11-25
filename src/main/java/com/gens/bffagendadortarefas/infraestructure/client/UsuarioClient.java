package com.gens.bffagendadortarefas.infraestructure.client;


import com.gens.bffagendadortarefas.business.dto.in.EnderecoRequestDTO;
import com.gens.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.gens.bffagendadortarefas.business.dto.in.TelefoneRequestDTO;
import com.gens.bffagendadortarefas.business.dto.in.UsuarioRequestDTO;
import com.gens.bffagendadortarefas.business.dto.out.EnderecoResponseDTO;
import com.gens.bffagendadortarefas.business.dto.out.TelefoneResponseDTO;
import com.gens.bffagendadortarefas.business.dto.out.UsuarioResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioResponseDTO buscarUsuarioPorEmail(@RequestParam("email") String email,
                                             @RequestHeader("Authorization") String token);

    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO loginRequestDTO);


    @PostMapping
    UsuarioResponseDTO salvarUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO);


    @DeleteMapping("/{email}")
    void deletarUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioResponseDTO atualizarDadosUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO,
                                            @RequestHeader("Authorization") String token);


    @PutMapping("/endereco")
    EnderecoResponseDTO atualizaEndereco(@RequestBody EnderecoRequestDTO enderecoRequestDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);


    @PutMapping("/telefone")
    TelefoneResponseDTO atualizaTelefone(@RequestBody TelefoneRequestDTO telefoneRequestDTO,
                                         @RequestParam("id") Long id,
                                         @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoResponseDTO cadastraEndereco(@RequestBody EnderecoRequestDTO enderecoRequestDTO,
                                        @RequestHeader("Authorization") String token);


    @PostMapping("/telefone")
    TelefoneResponseDTO cadastraTelefone(@RequestBody TelefoneRequestDTO telefoneRequestDTO,
                                        @RequestHeader("Authorization") String token);
}


