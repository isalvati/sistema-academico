package br.com.isalvati.sistemaacademico.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "response_code")
public class ResponseCodeEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "message", nullable = false)
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ResponseCodeEntity that = (ResponseCodeEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(code, that.code) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, code, message);
    }

    @Override
    public String toString() {
        return "WhatsMoneyResponseCodeEntity{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

}
