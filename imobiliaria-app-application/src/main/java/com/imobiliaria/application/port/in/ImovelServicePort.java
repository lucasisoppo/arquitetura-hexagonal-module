package com.imobiliaria.application.port.in;

import com.imobiliaria.domain.model.Imovel;

import java.util.List;

public interface ImovelServicePort {
    List<Imovel> listarTodosImoveis();
    Imovel criarImovel(Imovel imovel);
}
