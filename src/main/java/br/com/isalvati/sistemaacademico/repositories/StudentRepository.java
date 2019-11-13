package br.com.isalvati.sistemaacademico.repositories;

import br.com.isalvati.sistemaacademico.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
}
