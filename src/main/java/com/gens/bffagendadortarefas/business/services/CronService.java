package com.gens.bffagendadortarefas.business.services;

import com.gens.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.gens.bffagendadortarefas.business.dto.out.TarefasResponseDTO;
import com.gens.bffagendadortarefas.infraestructure.enuns.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;


    @Value("${cronusuario.email}")
    private String email;

    @Value("${cronusuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.expression}")
    public void buscarTarefasPendentes() {

        String token = login(converterParaLoginRequestDTO());
        log.info("Iniciando a busca de tarefas pendentes para envio de notificações.");
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataFutura = LocalDateTime.now().plusHours(2);

        List<TarefasResponseDTO> listaTarefas = tarefasService.buscarTarefasPorPeriodo(dataAtual, dataFutura, token );;
        log.info("Tarefas econtradas" + listaTarefas);
        listaTarefas.forEach(tarefa -> {
            emailService.enviarEmail(tarefa);
            log.info("Email enviado para: " + tarefa.getEmailUsuario());
            tarefasService.alteraStatus(tarefa.getId(),StatusNotificacaoEnum.NOTIFICADO, token);});
        log.info("Finalizando a busca de tarefas pendentes para envio de email.");
    }

    public String login(LoginRequestDTO loginRequestDTO) {
        return usuarioService.login(loginRequestDTO);
    }

    public LoginRequestDTO converterParaLoginRequestDTO() {
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}
