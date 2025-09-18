package com.imobiliaria.infrastructure.inbound.api;

import com.imobiliaria.domain.model.Imovel;
import com.imobiliaria.application.port.in.ImovelServicePort;
import com.imobiliaria.infrastructure.dto.ImovelDto;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/imoveis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImovelResource {
    @Inject
    ImovelServicePort imovelServicePort;

    @POST
    public Response criar(ImovelDto dto) {
        Imovel imovel = dto.toDomain();
        Imovel novoImovel = imovelServicePort.criarImovel(imovel);
        return Response.created(URI.create("/imoveis/" + novoImovel.getId())).build();
    }

    @GET
    public List<ImovelDto> listar() {
        return imovelServicePort.listarTodosImoveis().stream()
                .map(ImovelDto::fromDomain)
                .collect(Collectors.toList());
    }
}
