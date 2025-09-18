package com.imobiliaria.infrastructure.inbound.api;

import com.imobiliaria.infrastructure.dto.ClienteDto;
import com.imobiliaria.domain.model.Cliente;
import com.imobiliaria.application.port.in.ClienteServicePort;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    ClienteServicePort clienteServicePort;

    @POST
    public Response criar(ClienteDto dto) {
        Cliente cliente = dto.toDomain();
        Cliente novoCliente = clienteServicePort.criarCliente(cliente);
        return Response.created(URI.create("/clientes/" + novoCliente.getId())).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, ClienteDto dto) {
        // Assegura que o ID da URL e o ID do corpo da requisição sejam consistentes
        if (dto.getId() == null || !dto.getId().equals(id)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("ID do corpo e da URL não correspondem.").build();
        }
        Cliente cliente = dto.toDomain();
        Cliente clienteAtualizado = clienteServicePort.atualizarCliente(cliente);
        return Response.ok(clienteAtualizado).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        return clienteServicePort.buscarClientePorId(id)
                .map(ClienteDto::fromDomain)
                .map(clienteDTO -> Response.ok(clienteDTO).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @GET
    public List<ClienteDto> listar() {
        return clienteServicePort.listarTodosClientes().stream()
                .map(ClienteDto::fromDomain)
                .collect(Collectors.toList());
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        clienteServicePort.deletarCliente(id);
        return Response.noContent().build();
    }
}