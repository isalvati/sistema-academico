package br.com.isalvati.sistemaacademico.services;

import br.com.isalvati.sistemaacademico.dto.student.StudentRequest;
import br.com.isalvati.sistemaacademico.dto.user.SystemUser;
import br.com.isalvati.sistemaacademico.entities.StudentEntity;
import br.com.isalvati.sistemaacademico.entities.SystemUserEntity;
import br.com.isalvati.sistemaacademico.repositories.StudentRepository;
import br.com.isalvati.sistemaacademico.type.UserProfile;
import br.com.isalvati.sistemaacademico.util.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends BaseService<StudentEntity> {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentEntity registerStudent(StudentRequest request){
        StudentEntity student = new StudentEntity();
        ModelMapper mapper = new ModelMapper();
        student = mapper.map(request, StudentEntity.class);
        student.setProfile(UserProfile.STUDENT);
        // TODO: criar system user para estudante
        SystemUserEntity user = new SystemUserEntity();
        user.setId(1L);
        // FIM TO DO

        student.setSystemUser(user);
        studentRepository.save(student);
        return student;

    }
}
