package br.com.isalvati.sistemaacademico.services;

import br.com.isalvati.sistemaacademico.entities.SystemUserEntity;
import br.com.isalvati.sistemaacademico.repositories.SystemUserRepository;
import br.com.isalvati.sistemaacademico.security.cryptography.Passwords;
import br.com.isalvati.sistemaacademico.type.Environment;
import br.com.isalvati.sistemaacademico.type.UserProfile;
import br.com.isalvati.sistemaacademico.util.BaseService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SystemUserService extends BaseService<SystemUserEntity> {

    private final SystemUserRepository systemUserRepository;

    public SystemUserService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    public SystemUserEntity findByUsername(String username) {
        return systemUserRepository.findByUsername(username);
    }

    public Optional<SystemUserEntity> findById(Long id) {
        return systemUserRepository.findById(id);
    }

    public SystemUserEntity buildSystemUser(String username, UserProfile profile, String password) {
        SystemUserEntity usr = new SystemUserEntity();
        usr.setDescription("");
        usr.setEnvironment(Environment.DEV);
        byte[] salt = Passwords.getNextSalt();
        usr.setAppKey(salt);
        byte[] pass = Passwords.hash((password).toCharArray(), salt);
        usr.setPassword(pass);
        usr.setUsername(username);
        usr.setProfile(profile);
        usr.setCreatedBy(0L);
        return usr;
    }

}
