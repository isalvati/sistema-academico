package br.com.isalvati.sistemaacademico.repositories;

import br.com.isalvati.sistemaacademico.entities.ConfigurationEntity;
import br.com.isalvati.sistemaacademico.type.Environment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigurationRepository extends JpaRepository<ConfigurationEntity, Long> {

    ConfigurationEntity findByName(String name);

    ConfigurationEntity findByValue(String value);

    List<ConfigurationEntity> findByEnvironment(Environment environment);

}
