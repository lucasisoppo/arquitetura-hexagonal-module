package com.imobiliaria.application.service;


import com.imobiliaria.domain.event.EventImovelCriado;
import com.imobiliaria.application.port.out.CepServicePort;
import com.imobiliaria.domain.model.CepInfo;
import com.imobiliaria.domain.model.Imovel;
import com.imobiliaria.application.port.in.ImovelServicePort;
import com.imobiliaria.application.port.out.ImovelRepositoryPort;
import com.imobiliaria.application.port.out.NotificacaoPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ImovelService implements ImovelServicePort {

    private final ImovelRepositoryPort imovelRepositoryPort;
    private final NotificacaoPort notificacaoPort;
    private final CepServicePort cepServicePort;

    @Inject
    public ImovelService(ImovelRepositoryPort imovelRepositoryPort, NotificacaoPort notificacaoPort, CepServicePort cepServicePort) {
        this.imovelRepositoryPort = imovelRepositoryPort;
        this.notificacaoPort = notificacaoPort;
        this.cepServicePort = cepServicePort;
    }

    @Override
    @Transactional
    public Imovel criarImovel(Imovel imovel) {
        // Busca informações do CEP
        Optional<CepInfo> cepInfo = cepServicePort.buscarCep(imovel.getCep());
        if (cepInfo.isEmpty()) {
            throw new IllegalArgumentException("CEP inválido ou não encontrado: " + imovel.getCep());
        }

        // Enriquecer o modelo de domínio com os dados do CEP
        imovel.setCidade(cepInfo.get().getLocalidade());
        imovel.setUf(cepInfo.get().getUf());
        Imovel novoImovel = imovelRepositoryPort.salvar(imovel);

        // Notifica a criação do imóvel
        EventImovelCriado evento = new EventImovelCriado(
                novoImovel.getId(),
                novoImovel.getEndereco(),
                novoImovel.getCliente().getId()
        );
        notificacaoPort.notificarImovelCriado(evento);
        return novoImovel;
    }

    @Override
    public List<Imovel> listarTodosImoveis() {
        return imovelRepositoryPort.buscarTodos();
    }

}
