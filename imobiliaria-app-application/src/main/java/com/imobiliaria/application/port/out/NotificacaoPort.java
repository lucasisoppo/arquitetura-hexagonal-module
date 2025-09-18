package com.imobiliaria.application.port.out;

import com.imobiliaria.domain.event.EventImovelCriado;

public interface NotificacaoPort {
    void notificarImovelCriado(EventImovelCriado evento);
}
