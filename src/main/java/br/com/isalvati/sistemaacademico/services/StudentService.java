package br.com.isalvati.sistemaacademico.services;

import br.com.isalvati.sistemaacademico.dto.student.StudentRequest;
import br.com.isalvati.sistemaacademico.dto.user.SystemUser;
import br.com.isalvati.sistemaacademico.entities.StudentEntity;
import br.com.isalvati.sistemaacademico.entities.SystemUserEntity;
import br.com.isalvati.sistemaacademico.exception.BaseException;
import br.com.isalvati.sistemaacademico.repositories.StudentRepository;
import br.com.isalvati.sistemaacademico.type.UserProfile;
import br.com.isalvati.sistemaacademico.util.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends BaseService<StudentEntity> {

    private final StudentRepository studentRepository;

    private final SystemUserService systemUserService;

    public StudentService(StudentRepository studentRepository, SystemUserService systemUserService) {
        this.studentRepository = studentRepository;
        this.systemUserService = systemUserService;
    }

    public StudentEntity registerStudent(StudentRequest request) throws BaseException {
        ModelMapper mapper = new ModelMapper();
        StudentEntity student = mapper.map(request, StudentEntity.class);

        SystemUserEntity user = systemUserService.buildSystemUser(student.getEmail(), UserProfile.STUDENT);
        systemUserService.save(user);

        student.setSystemUser(user);
        save(student);
        return student;
    }
}
