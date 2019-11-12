package br.com.isalvati.sistemaacademico.dto.user;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class SystemUser {

    @NotNull
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemUser that = (SystemUser) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, description);
    }

    @Override
    public String toString() {
        return "SystemUserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
