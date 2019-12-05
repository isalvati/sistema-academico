package br.com.isalvati.sistemaacademico.services;

import br.com.isalvati.sistemaacademico.dto.student.StudentRegisterResponse;
import br.com.isalvati.sistemaacademico.dto.student.StudentRequest;
import br.com.isalvati.sistemaacademico.entities.StudentEntity;
import br.com.isalvati.sistemaacademico.entities.SystemUserEntity;
import br.com.isalvati.sistemaacademico.exception.SistemaAcademicoException;
import br.com.isalvati.sistemaacademico.repositories.StudentRepository;
import br.com.isalvati.sistemaacademico.type.UserProfile;
import br.com.isalvati.sistemaacademico.util.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends BaseService<StudentEntity> {

    private final StudentRepository studentRepository;

    private final SystemUserService systemUserService;

    private static final String DEFAULT_REGISTER_PASSWORD = "123";

    public StudentService(StudentRepository studentRepository, SystemUserService systemUserService) {
        this.studentRepository = studentRepository;
        this.systemUserService = systemUserService;
    }

    public StudentRegisterResponse registerStudent(StudentRequest request) throws SistemaAcademicoException {
        ModelMapper mapper = new ModelMapper();
        StudentEntity student = mapper.map(request, StudentEntity.class);

        SystemUserEntity user = systemUserService.buildSystemUser(student.getEmail(), UserProfile.STUDENT, DEFAULT_REGISTER_PASSWORD);
        systemUserService.save(user);

        student.setSystemUser(user);
        save(student);

        StudentRegisterResponse studentRegisterResponse = new StudentRegisterResponse();
        studentRegisterResponse.setUsername(student.getEmail());
        studentRegisterResponse.setPassword(DEFAULT_REGISTER_PASSWORD);
        return studentRegisterResponse;
    }

    public StudentEntity findBySystemUser(SystemUserEntity systemUser) {
        try {
            StudentEntity studentEntity = this.studentRepository.findBySystemUser(systemUser);
            return studentEntity;
        } catch (Exception e) {
            throw e;
        }


    }
}
