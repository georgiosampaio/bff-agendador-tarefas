package com.gens.bffagendadortarefas.infraestructure.client;


import com.gens.bffagendadortarefas.business.dto.in.TarefasRequestDTO;
import com.gens.bffagendadortarefas.business.dto.out.TarefasResponseDTO;
import com.gens.bffagendadortarefas.infraestructure.enuns.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefasClient {

    //verificar se o statusNotificacaoEnum é necessário aqui

    StatusNotificacaoEnum statusNotificacaoEnum = StatusNotificacaoEnum.PENDENTE;

    @PostMapping
    TarefasResponseDTO criarTarefa(@RequestHeader("Authorization") String token,
                                   @RequestBody TarefasRequestDTO tarefasRequestDTO);

    @GetMapping("/eventos")
    List<TarefasResponseDTO> buscarTarefasPorPeriodo(@RequestParam @DateTimeFormat(
                                                     iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim,
                                                    @RequestHeader("Authorization") String token);


    @GetMapping
    List<TarefasResponseDTO> buscarTarefasPorEmail(@RequestHeader("Authorization") String token);


    @DeleteMapping
    void deletaTarefasPorId(@RequestParam String id,
                            @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasResponseDTO alteraStatus(@RequestParam String id,
                                   @RequestParam("novoStatus") StatusNotificacaoEnum novoStatus,
                                   @RequestHeader("Authorization") String token);


    @PutMapping
    TarefasResponseDTO atualizarTaresfaId(@RequestParam("id") String id,
                                         @RequestBody TarefasRequestDTO tarefasRequestDTO,
                                         @RequestHeader("Authorization") String token);
}


