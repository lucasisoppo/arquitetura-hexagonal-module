package com.imobiliaria.infrastructure.inbound.api.exeption;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider // Anotação que registra a classe como um provedor JAX-RS
public class ValidationExceptionHandler implements ExceptionMapper<RuntimeException> {

    private static final Logger LOG = LoggerFactory.getLogger(ValidationExceptionHandler.class);

    @Override
    public Response toResponse(RuntimeException exception) {

        LOG.error("Imobiliaria ERROR: " + exception.getMessage(), exception);

        // Mapeia exceções de validação para status de erro específicos
        if (exception instanceof IllegalArgumentException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(exception.getMessage())
                    .build();
        }

        if (exception instanceof IllegalStateException) {
            // Em alguns casos, como cliente inativo, CONFLICT (409) pode ser mais adequado que BAD_REQUEST
            return Response.status(Response.Status.CONFLICT)
                    .entity(exception.getMessage())
                    .build();
        }

        // Se a exceção não for de validação, retorna um erro genérico
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro interno do servidor.")
                .build();
    }
}