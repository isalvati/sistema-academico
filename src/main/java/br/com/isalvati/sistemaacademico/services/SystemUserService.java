package br.com.isalvati.sistemaacademico.services;

import br.com.isalvati.sistemaacademico.entities.SystemUserEntity;
import br.com.isalvati.sistemaacademico.repositories.SystemUserRepository;
import br.com.isalvati.sistemaacademico.security.cryptography.Passwords;
import br.com.isalvati.sistemaacademico.type.Environment;
import br.com.isalvati.sistemaacademico.type.UserProfile;
import br.com.isalvati.sistemaacademico.util.BaseService;
import org.springframework.stereotype.Service;

@Service
public class SystemUserService extends BaseService<SystemUserEntity> {

    private final SystemUserRepository systemUserRepository;

    public SystemUserService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    public SystemUserEntity findByUsername(String username) {
        return systemUserRepository.findByUsername(username);
    }

    public SystemUserEntity buildSystemUser(String username, UserProfile profile) {
        SystemUserEntity usr = new SystemUserEntity();
        usr.setDescription("");
        usr.setEnvironment(Environment.DEV);
        byte[] salt = Passwords.getNextSalt();
        usr.setAppKey(salt);
        byte[] pass = Passwords.hash(("123").toCharArray(), salt);
        usr.setPassword(pass);
        usr.setUsername(username);
        usr.setProfile(profile);
        usr.setCreatedBy(0L);
        return usr;
    }

}
