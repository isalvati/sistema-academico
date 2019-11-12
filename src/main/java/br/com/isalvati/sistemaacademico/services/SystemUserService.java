package br.com.isalvati.sistemaacademico.services;

import br.com.isalvati.sistemaacademico.entities.SystemUserEntity;
import br.com.isalvati.sistemaacademico.repositories.SystemUserRepository;
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

}
