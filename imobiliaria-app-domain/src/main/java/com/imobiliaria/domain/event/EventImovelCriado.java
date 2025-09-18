package com.imobiliaria.domain.event;

import java.io.Serializable;

public class EventImovelCriado implements Serializable{
    public Long imovelId;
    public String endereco;
    public Long clienteId;

    public EventImovelCriado(Long imovelId, String endereco, Long clienteId) {
        this.imovelId = imovelId;
        this.endereco = endereco;
        this.clienteId = clienteId;
    }
}
