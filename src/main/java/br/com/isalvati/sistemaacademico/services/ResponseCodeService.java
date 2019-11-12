package br.com.isalvati.sistemaacademico.services;

import br.com.isalvati.sistemaacademico.entities.ResponseCodeEntity;
import br.com.isalvati.sistemaacademico.util.BaseService;
import br.com.isalvati.sistemaacademico.repositories.ResponseCodeRepository;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponseCodeService extends BaseService<ResponseCodeEntity> {

    private static final Logger logger = Logger.getLogger(ResponseCodeService.class);

    private final ResponseCodeRepository repository;

    public ResponseCodeService(ResponseCodeRepository repository) {
        this.repository = repository;
    }

    public Optional<ResponseCodeEntity> findByName(String name) {
        logger.info("findByName");
        return repository.findByName(name);
    }

}
