package br.com.isalvati.sistemaacademico.controller;

import br.com.isalvati.sistemaacademico.dto.student.StudentRequest;
import br.com.isalvati.sistemaacademico.entities.StudentEntity;
import br.com.isalvati.sistemaacademico.exception.BaseException;
import br.com.isalvati.sistemaacademico.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<Object> registerStudent(@Valid @RequestBody StudentRequest studentRequest,
                                                  HttpServletRequest request) throws BaseException {
        StudentEntity response = service.registerStudent(studentRequest);
        return ResponseEntity.ok(response);
    }

}
