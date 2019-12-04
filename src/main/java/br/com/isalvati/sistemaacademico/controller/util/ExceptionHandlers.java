package br.com.isalvati.sistemaacademico.controller.util;

import br.com.isalvati.sistemaacademico.exception.SistemaAcademicoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@RestControllerAdvice
public class ExceptionHandlers extends DefaultHandlerExceptionResolver {

    @ExceptionHandler(SistemaAcademicoException.class)
    public void handleSistemaAcademicoException(
            SistemaAcademicoException ex,
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException(
            ConstraintViolationException ex,
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

}
