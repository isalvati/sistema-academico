package br.com.isalvati.sistemaacademico.controller.util;

import br.com.isalvati.sistemaacademico.entities.ResponseCodeEntity;
import br.com.isalvati.sistemaacademico.services.ResponseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private ResponseCodeService codeService;

    /**
     * Filtra os erros ao usar @Valid @RequestBody
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage());
        FieldError errors = ex.getBindingResult().getFieldErrors().iterator().next();
        String[] objectField = errors.getField().split("\\.");
        String field;
        if (objectField.length > 1) {
            field = objectField[objectField.length - 1];
        } else {
            field = objectField[0];
        }
        return getObjectResponseEntity(field, headers, status);
    }

    /**
     * Filtra os erros ao usar @pathParam
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage());
        return getObjectResponseEntity(ex.getParameterName(), headers, status);
    }

    /**
     * Filtra os erros ao usar @RequestHeader
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error(ex.getMessage());
        MissingRequestHeaderException headerException = (MissingRequestHeaderException) ex;
        return getObjectResponseEntity(headerException.getHeaderName(), headers, status);
    }

    private ResponseEntity<Object> getObjectResponseEntity(String field, HttpHeaders headers, HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        ResponseCodeEntity codeError = codeService.findByName(field)
                .orElse(getDefaultError());
        logger.error(codeError.toString());
        body.put("success", false);
        body.put("code", codeError.getCode());
        body.put("message", codeError.getMessage());
        return new ResponseEntity<>(body, headers, status);
    }

    private ResponseCodeEntity getDefaultError() {
        ResponseCodeEntity codeError = new ResponseCodeEntity();
        codeError.setCode("99");
        codeError.setMessage("Erro generico");
        return codeError;
    }

}
