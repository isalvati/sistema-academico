package br.com.isalvati.sistemaacademico.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;

public class Response<T> extends ResourceSupport {

    private List<T> content;
    private LocalDateTime timestamp;

    public Response(List<T> content) {
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
