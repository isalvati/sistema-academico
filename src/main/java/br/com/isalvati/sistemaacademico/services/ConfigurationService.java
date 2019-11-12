package br.com.isalvati.sistemaacademico.services;

import br.com.isalvati.sistemaacademico.entities.ConfigurationEntity;
import br.com.isalvati.sistemaacademico.type.Environment;
import br.com.isalvati.sistemaacademico.util.BaseService;
import br.com.isalvati.sistemaacademico.repositories.ConfigurationRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigurationService extends BaseService<ConfigurationEntity> {

    private final ConfigurationRepository configurationRepository;

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @Cacheable("configNamo")
    public ConfigurationEntity findByName(String name) {
        return configurationRepository.findByName(name);
    }

    public ConfigurationEntity findByValue(String value) {
        return configurationRepository.findByValue(value);
    }

    public List<ConfigurationEntity> findByEnvironment(Environment environment) {
        return configurationRepository.findByEnvironment(environment);
    }

}
