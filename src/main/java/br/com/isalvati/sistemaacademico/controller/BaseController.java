package br.com.isalvati.sistemaacademico.controller;

import br.com.isalvati.sistemaacademico.security.util.JwtToken;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    private static final String BEARER_PREFIX = "Bearer";
    private static final String AUTH_HEADER = "Authorization";

    @Autowired
    private JwtToken jwtToken;

    public String getUsername(HttpServletRequest request) {
        return jwtToken.getUsername(request.getHeader(AUTH_HEADER).split(" ")[1]);
    }

    public Integer getId(HttpServletRequest request) {
        return jwtToken.getId(request.getHeader(AUTH_HEADER).split(" ")[1]);
    }

    public String getProfile(HttpServletRequest request) {
        return jwtToken.getIssuer(request.getHeader(AUTH_HEADER).split(" ")[1]);
    }
}
