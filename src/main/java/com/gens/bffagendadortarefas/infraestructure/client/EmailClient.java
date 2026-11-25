package com.gens.bffagendadortarefas.infraestructure.client;


import com.gens.bffagendadortarefas.business.dto.in.TarefasRequestDTO;
import com.gens.bffagendadortarefas.business.dto.out.TarefasResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url = "${notificacao.url}")
public interface EmailClient {

    void enviarEmail(@RequestBody TarefasResponseDTO  tarefasResponseDTO);
}


