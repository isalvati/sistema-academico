package br.com.isalvati.sistemaacademico.controller;

import br.com.isalvati.sistemaacademico.dto.student.StudentRegisterResponse;
import br.com.isalvati.sistemaacademico.dto.student.StudentRequest;
import br.com.isalvati.sistemaacademico.dto.student.StudentUpdateRequest;
import br.com.isalvati.sistemaacademico.entities.StudentEntity;
import br.com.isalvati.sistemaacademico.entities.SystemUserEntity;
import br.com.isalvati.sistemaacademico.exception.SistemaAcademicoException;
import br.com.isalvati.sistemaacademico.services.StudentService;
import br.com.isalvati.sistemaacademico.services.SystemUserService;
import br.com.isalvati.sistemaacademico.type.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController extends BaseController {

    @Autowired
    private StudentService service;

    @Autowired
    private SystemUserService systemUserService;

    @PostMapping("register")
    public ResponseEntity<Object> registerStudent(@Valid @RequestBody StudentRequest studentRequest) throws SistemaAcademicoException {
        StudentRegisterResponse response = service.registerStudent(studentRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@Valid @RequestBody StudentUpdateRequest studentUpdateRequest, @PathVariable Long id,
                                         HttpServletRequest request) throws SistemaAcademicoException {
        String profileCredential = getProfile(request);
        if (profileCredential.equals(UserProfile.STUDENT.name())) {
            Integer userId = getId(request);
            try {
                Optional<SystemUserEntity> systemUserEntity = systemUserService.findById(userId.longValue());
                StudentEntity studentEntity = service.findBySystemUser(systemUserEntity.get());
                if (studentEntity.getId().equals(id)) {
                    studentEntity.setAddress(studentUpdateRequest.getAddress());
                    studentEntity.setPhone(studentUpdateRequest.getPhone());
                    service.save(studentEntity);
                    return ResponseEntity.ok("{\"success\":\"true\"}");
                }
            } catch (Exception e){
                throw e;
            }
            throw new SistemaAcademicoException("Usuário de perfil estudante não ter permissão para alterar dados de outro estudante");
        } else if (profileCredential.equals(UserProfile.SECRETARY.name())) {
            //TODO: perfil secretaria
            throw new SistemaAcademicoException("");
        }
        //TODO: UNAUTHORIZED
        throw new SistemaAcademicoException("");
    }

    @PutMapping("lock/{id}")
    public ResponseEntity<Object> lock(@PathVariable Long id,
                                         HttpServletRequest request) throws SistemaAcademicoException {
        String profileCredential = getProfile(request);
        if (profileCredential.equals(UserProfile.STUDENT.name())) {
            Integer userId = getId(request);
            try {
                Optional<SystemUserEntity> systemUserEntity = systemUserService.findById(userId.longValue());
                StudentEntity studentEntity = service.findBySystemUser(systemUserEntity.get());
                if (studentEntity.getId().equals(id)) {
                    studentEntity.setLocked(true);
                    service.save(studentEntity);
                    return ResponseEntity.ok("{\"success\":\"true\"}");
                }
            } catch (Exception e){
                throw e;
            }
            throw new SistemaAcademicoException("Usuário de perfil estudante não ter permissão para trancar matrícula outro estudante");
        } else if (profileCredential.equals(UserProfile.SECRETARY.name())) {
            //TODO: perfil secretaria
            throw new SistemaAcademicoException("");
        }
        //TODO: UNAUTHORIZED
        throw new SistemaAcademicoException("");
    }

    @PutMapping("renew/{id}")
    public ResponseEntity<Object> renew(@PathVariable Long id,
                                       HttpServletRequest request) throws SistemaAcademicoException {
        String profileCredential = getProfile(request);
        if (profileCredential.equals(UserProfile.STUDENT.name())) {
            Integer userId = getId(request);
            try {
                Optional<SystemUserEntity> systemUserEntity = systemUserService.findById(userId.longValue());
                StudentEntity studentEntity = service.findBySystemUser(systemUserEntity.get());
                if (studentEntity.getId().equals(id)) {
                    studentEntity.setRenewed(true);
                    service.save(studentEntity);
                    return ResponseEntity.ok("{\"success\":\"true\"}");
                }
            } catch (Exception e){
                throw e;
            }
            throw new SistemaAcademicoException("Usuário de perfil estudante não ter permissão para renovar matrícula outro estudante");
        }
        //TODO: UNAUTHORIZED
        throw new SistemaAcademicoException("");
    }


}
