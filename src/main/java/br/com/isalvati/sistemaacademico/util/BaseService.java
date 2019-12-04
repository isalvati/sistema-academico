package br.com.isalvati.sistemaacademico.util;

import br.com.isalvati.sistemaacademico.exception.SistemaAcademicoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T> {

    @Autowired
    private JpaRepository<T, Long> repository;

    /**
     * Metodos do JpaRepository
     */
    public List<T> findAll() {
        return repository.findAll();
    }

    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Page<T> findAll(Example<T> example, Pageable pageable) {
        return repository.findAll(example, pageable);
    }

    public Optional<T> findById(Long id) {
        return repository.findById(id);
    }

    public T save(T entity) throws SistemaAcademicoException {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new SistemaAcademicoException(e.getMessage());
        }
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

}

