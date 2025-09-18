package com.imobiliaria.infrastructure.outbound.messaging;

import com.imobiliaria.application.port.out.NotificacaoPort;
import com.imobiliaria.domain.event.EventImovelCriado;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

public class NotificacaoKafkaAdapter implements NotificacaoPort {

    @Inject
    @Channel("notificacoes-imoveis") // O nome do canal configurado no application.properties
    Emitter<EventImovelCriado> emitter;

    @Override
    public void notificarImovelCriado(EventImovelCriado evento) {
        emitter.send(evento);
    }
}
