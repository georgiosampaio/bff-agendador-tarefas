package com.gens.bffagendadortarefas.business.services;

import com.gens.bffagendadortarefas.business.dto.in.TarefasRequestDTO;
import com.gens.bffagendadortarefas.business.dto.out.TarefasResponseDTO;
import com.gens.bffagendadortarefas.infraestructure.client.TarefasClient;
import com.gens.bffagendadortarefas.infraestructure.enuns.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasResponseDTO criarTarefa(String token, TarefasRequestDTO tarefasRequestDTO) {
        return tarefasClient.criarTarefa(token, tarefasRequestDTO);
    }

    public List<TarefasResponseDTO> buscarTarefasPorPeriodo(LocalDateTime dataInicio, LocalDateTime dataFim, String token) {
        return tarefasClient.buscarTarefasPorPeriodo(dataInicio, dataFim, token);
    }

    public List<TarefasResponseDTO> buscasTarefasEmail(String token) {
        return tarefasClient.buscarTarefasPorEmail(token);
    }

    public void deletaTarefasPorId(String id, String token) {
        tarefasClient.deletaTarefasPorId(id, token);
    }

    public TarefasResponseDTO alteraStatus(String id, StatusNotificacaoEnum novoStatus, String token) {
        return tarefasClient.alteraStatus(id, novoStatus, token);
    }

    public TarefasResponseDTO updateTarefasId(String id, TarefasRequestDTO tarefasRequestDTO, String token) {
        return tarefasClient.atualizarTaresfaId(id, tarefasRequestDTO, token);
    }


}


