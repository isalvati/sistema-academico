package br.com.isalvati.sistemaacademico.security.controller;

import br.com.isalvati.sistemaacademico.exception.ForbiddenException;
import br.com.isalvati.sistemaacademico.exception.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("google")
    public ResponseEntity googleLogin(HttpServletRequest request) {
        try {

            //Todo check token and create sailor

            return ResponseEntity.ok("");
        } catch (UnauthorizedException | ForbiddenException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao processar solicitação");
        }
    }

}
