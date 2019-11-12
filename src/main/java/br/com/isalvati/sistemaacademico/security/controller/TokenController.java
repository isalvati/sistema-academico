package br.com.isalvati.sistemaacademico.security.controller;

import br.com.isalvati.sistemaacademico.entities.SystemUserEntity;
import br.com.isalvati.sistemaacademico.exception.ForbiddenException;
import br.com.isalvati.sistemaacademico.exception.UnauthorizedException;
import br.com.isalvati.sistemaacademico.security.services.LoginService;
import br.com.isalvati.sistemaacademico.security.util.JwtToken;
import br.com.isalvati.sistemaacademico.security.util.UserFactory;
import br.com.isalvati.sistemaacademico.services.ResponseCodeService;
import br.com.isalvati.sistemaacademico.services.SystemUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("token")
@CrossOrigin(origins = "*")
public class TokenController {

    private static final Logger log = LoggerFactory.getLogger(TokenController.class);
    private static final String CONSUMER_KEY = "key";
    private static final String CONSUMER_SECRET = "secret";
    private static final String AUTH_HEADER = "AUTHORIZATION";
    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private ResponseCodeService logService;

    @Autowired
    private LoginService loginService;

    @GetMapping
    public ResponseEntity login(HttpServletRequest request) {
        try {
            String key = request.getHeader(CONSUMER_KEY);
            String secret = request.getHeader(CONSUMER_SECRET);

            SystemUserEntity systemUser = loginService.login(key, secret);
            Map<String, String> ret = new HashMap<>();
            ret.put("authorization", jwtToken.obterToken(UserFactory.createUser(systemUser.getId(), systemUser.getUsername(),
                    Arrays.toString(systemUser.getPassword()), "ROLE_" + systemUser.getUsername())));

            return ResponseEntity.ok(ret);
        } catch (UnauthorizedException | ForbiddenException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao processar solicitação");
        }
    }

    @GetMapping(value = "refresh")
    public ResponseEntity refreshToken(HttpServletRequest request) {
        try {
            String token = request.getHeader(AUTH_HEADER);
            if (token != null && token.startsWith(BEARER_PREFIX)) {
                token = token.substring(7);
                String key = jwtToken.getUsername(token);
                if (key != null && jwtToken.validToken(token)) {
                    SystemUserEntity systemUser = systemUserService.findByUsername(key);
                    Map<String, String> ret = new HashMap<>();
                    ret.put("authorization", jwtToken.refreshToken(token));
                    return ResponseEntity.ok(ret);
                }
            }
            log.error("Error refreshToken invalid token");
            throw new UnauthorizedException();
        } catch (UnauthorizedException | ForbiddenException e) {
            log.error(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Erro ao processar solicitação");
        }
    }
}
