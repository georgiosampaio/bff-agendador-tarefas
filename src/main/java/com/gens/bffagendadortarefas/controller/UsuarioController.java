package com.gens.bffagendadortarefas.controller;


import com.gens.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.gens.bffagendadortarefas.business.dto.out.EnderecoResponseDTO;
import com.gens.bffagendadortarefas.business.dto.out.TelefoneResponseDTO;
import com.gens.bffagendadortarefas.business.dto.out.UsuarioResponseDTO;
import com.gens.bffagendadortarefas.business.services.UsuarioService;
import com.gens.bffagendadortarefas.business.dto.in.EnderecoRequestDTO;
import com.gens.bffagendadortarefas.business.dto.in.TelefoneRequestDTO;
import com.gens.bffagendadortarefas.business.dto.in.UsuarioRequestDTO;
import com.gens.bffagendadortarefas.infraestructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro e login de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class UsuarioController {


    private final UsuarioService usuarioService;

    @Operation(summary = "Salva um novo usuário", description = "Salva um novo usuário no sistema")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "409", description = "Usuário já existe")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> salvarUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO) {
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioRequestDTO));
    }

    @Operation(summary = "Realiza o login do usuário", description = "Realiza o login do usuário e retorna um token de autenticação")
    @ApiResponse(responseCode = "200", description = "Login realizado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return usuarioService.login(loginRequestDTO);
    }

    @Operation(summary = "Busca usuário por email", description = "Busca um usuário pelo email")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @GetMapping
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPorEmail(@RequestParam("email") String email,
                                                                    @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }

    @Operation(summary = "Deleta usuário por email", description = "Deleta um usuário pelo email")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deletarUsuarioPorEmail(@PathVariable String email,
                                                       @RequestHeader(name = "Authorization", required = false) String token) {
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Atualiza dados do usuário", description = "Atualiza os dados de um usuário")
    @ApiResponse(responseCode = "200", description = "Dados do usuário atualizados com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PutMapping
    public ResponseEntity<UsuarioResponseDTO> atualizarDadosUsuario(@RequestBody UsuarioRequestDTO usuarioRequestDTO,
                                                                    @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(token, usuarioRequestDTO));
    }

    @Operation(summary = "Atualiza endereço do usuário", description = "Atualiza o endereço de um usuário")
    @ApiResponse(responseCode = "200", description = "Endereço do usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "409", description = "Endereço já existe")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PutMapping("/endereco")
    public ResponseEntity<EnderecoResponseDTO> atualizaEndereco(@RequestBody EnderecoRequestDTO enderecoRequestDTO,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, enderecoRequestDTO, token));
    }

    @Operation(summary = "Atualiza telefone do usuário", description = "Atualiza o telefone de um usuário")
    @ApiResponse(responseCode = "200", description = "Telefone do usuário atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "409", description = "Telefone já existe")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PutMapping("/telefone")
    public ResponseEntity<TelefoneResponseDTO> atualizaTelefone(@RequestBody TelefoneRequestDTO telefoneRequestDTO,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, telefoneRequestDTO, token));
    }

    @Operation(summary = "Cadastra endereço do usuário", description = "Cadastra o endereço de um usuário")
    @ApiResponse(responseCode = "200", description = "Endereço do usuário cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "409", description = "Endereço já existe")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PostMapping("/endereco")
    public ResponseEntity<EnderecoResponseDTO> cadastraEndereco(@RequestBody EnderecoRequestDTO enderecoRequestDTO,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, enderecoRequestDTO));
    }

    @Operation(summary = "Cadastra telefone do usuário", description = "Cadastra o telefone de um usuário")
    @ApiResponse(responseCode = "200", description = "Telefone do usuário cadastrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "409", description = "Telefone já existe")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PostMapping("/telefone")
    public ResponseEntity<TelefoneResponseDTO> cadastraTelefone(@RequestBody TelefoneRequestDTO telefoneRequestDTO,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, telefoneRequestDTO));
    }

}
