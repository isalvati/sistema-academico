package br.com.isalvati.sistemaacademico.repositories;

import br.com.isalvati.sistemaacademico.entities.SystemUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemUserRepository extends JpaRepository<SystemUserEntity, Long> {

    SystemUserEntity findByUsername(String username);

}
