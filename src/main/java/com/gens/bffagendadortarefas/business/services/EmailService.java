package com.gens.bffagendadortarefas.business.services;

import com.gens.bffagendadortarefas.business.dto.out.TarefasResponseDTO;
import com.gens.bffagendadortarefas.infraestructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;


    public void enviarEmail(TarefasResponseDTO tarefasResponseDTO) {

        emailClient.enviarEmail(tarefasResponseDTO);
    }

}
