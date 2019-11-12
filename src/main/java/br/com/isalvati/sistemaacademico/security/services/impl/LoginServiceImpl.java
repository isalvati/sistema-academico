package br.com.isalvati.sistemaacademico.security.services.impl;

import br.com.isalvati.sistemaacademico.entities.SystemUserEntity;
import br.com.isalvati.sistemaacademico.exception.UnauthorizedException;
import br.com.isalvati.sistemaacademico.security.cryptography.Passwords;
import br.com.isalvati.sistemaacademico.services.SystemUserService;
import br.com.isalvati.sistemaacademico.security.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SystemUserService systemUserService;

    public SystemUserEntity login(String userName, String secret) {
        SystemUserEntity systemUser = systemUserService.findByUsername(userName);
        if (systemUser != null) {
            if (Passwords.isExpectedPassword(secret.toCharArray(), systemUser.getAppKey(), systemUser.getPassword())) {
                return systemUser;
            }
        }
        throw new UnauthorizedException();
    }

}
