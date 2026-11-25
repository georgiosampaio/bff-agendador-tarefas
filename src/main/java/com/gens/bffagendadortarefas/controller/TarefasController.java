package com.gens.bffagendadortarefas.controller;


import com.gens.bffagendadortarefas.business.dto.out.TarefasResponseDTO;
import com.gens.bffagendadortarefas.business.services.TarefasService;
import com.gens.bffagendadortarefas.business.dto.in.TarefasRequestDTO;
import com.gens.bffagendadortarefas.infraestructure.enuns.StatusNotificacaoEnum;
import com.gens.bffagendadortarefas.infraestructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro e gerenciamento de tarefas")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @Operation(summary = "Criar tarefa", description = "Cria uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa criada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PostMapping
    public ResponseEntity<TarefasResponseDTO> criarTarefa(@RequestHeader(name = "authorization", required = false) String token,
                                                          @RequestBody TarefasRequestDTO tarefasRequestDTO) {

        return ResponseEntity.ok(tarefasService.criarTarefa(token, tarefasRequestDTO));
    }

    @Operation(summary = "Buscar tarefas por período", description = "Busca tarefas dentro de um determinado período")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasResponseDTO>> buscarTarefasPorPeriodo(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                                                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
                                                                           @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscarTarefasPorPeriodo(dataInicio, dataFim, token));
    }

    @Operation(summary = "Buscar tarefas por email", description = "Busca tarefas por email")
    @ApiResponse(responseCode = "200", description = "Tarefa encontrada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @GetMapping
    public ResponseEntity<List<TarefasResponseDTO>> buscarTarefasPorEmail(@RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.buscasTarefasEmail(token));
    }

    @Operation(summary = "Deletar tarefa por ID", description = "Deleta uma tarefa pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @DeleteMapping
    public ResponseEntity<Void> deletaTarefasPorId(@RequestParam String id,
                                                   @RequestHeader(name = "Authorization", required = false) String token) {
        tarefasService.deletaTarefasPorId(id, token);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Alterar status da tarefa", description = "Altera o status de uma tarefa")
    @ApiResponse(responseCode = "200", description = "Status da tarefa alterado com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PatchMapping
    public ResponseEntity<TarefasResponseDTO> alteraStatus(@RequestParam String id,
                                                          @RequestHeader(name = "Authorization", required = false) String token,
                                                          @RequestParam("novoStatus") StatusNotificacaoEnum novoStatus) {
        return ResponseEntity.ok(tarefasService.alteraStatus(id, novoStatus, token));
    }

    @Operation(summary = "Atualizar tarefa por ID", description = "Atualiza os dados de uma tarefa pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    @PutMapping
    public ResponseEntity<TarefasResponseDTO> atualizarTaresfaId(@RequestParam("id") String id,
                                                                @RequestBody TarefasRequestDTO tarefasRequestDTO,
                                                                @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(tarefasService.updateTarefasId(id, tarefasRequestDTO, token));
    }


}
