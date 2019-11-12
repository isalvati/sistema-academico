package br.com.isalvati.sistemaacademico.repositories;

import br.com.isalvati.sistemaacademico.entities.ResponseCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResponseCodeRepository extends JpaRepository<ResponseCodeEntity, Long> {

    Optional<ResponseCodeEntity> findByName(String name);
}
