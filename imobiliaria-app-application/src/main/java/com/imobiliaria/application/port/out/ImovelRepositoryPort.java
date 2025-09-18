package com.imobiliaria.application.port.out;

import com.imobiliaria.domain.model.Imovel;

import java.util.List;

public interface ImovelRepositoryPort {
    List<Imovel> buscarImoveisPorCliente(Long clienteId);
    List<Imovel> buscarTodos();
    Imovel salvar(Imovel imovel);
}
