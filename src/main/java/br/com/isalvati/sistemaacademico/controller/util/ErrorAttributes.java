package br.com.isalvati.sistemaacademico.controller.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class ErrorAttributes extends DefaultErrorAttributes {

    private static final Logger logger = LoggerFactory.getLogger(ErrorAttributes.class);

    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, false);
        try {

            // todo preciso usar aki

            errorAttributes.put("code", errorAttributes.get("message"));
            errorAttributes.remove("message");
        } catch (Exception e) {
            logger.error("Erro ao converter exception");
        }
        return errorAttributes;
    }

}

