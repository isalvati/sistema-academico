package br.com.isalvati.sistemaacademico.security.util;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        if (response.getStatus() == 403) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN,
                    "Acesso negado. Você nao tem permissao para acessar a URL solicitada");

        } else if (response.getStatus() == 401) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Acesso negado. Você deve estar autenticado no sistema para acessar a URL solicitada.");

        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                    "Erro ao executar solicitação.");

        }
    }
}
