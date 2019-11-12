package br.com.isalvati.sistemaacademico.entities;

import br.com.isalvati.sistemaacademico.type.Environment;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "system_user")
public class SystemUserEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Basic
    @Column(name = "password")
    private byte[] password;

    @Basic
    @Column(name = "app_key")
    private byte[] appKey;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "environment", nullable = false)
    @Enumerated(EnumType.STRING)
    private Environment environment;

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

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public byte[] getAppKey() {
        return appKey;
    }

    public void setAppKey(byte[] appKey) {
        this.appKey = appKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemUserEntity that = (SystemUserEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(username, that.username) &&
                Arrays.equals(password, that.password) &&
                Arrays.equals(appKey, that.appKey) &&
                Objects.equals(description, that.description) &&
                environment == that.environment;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, username, description, environment);
        result = 31 * result + Arrays.hashCode(password);
        result = 31 * result + Arrays.hashCode(appKey);
        return result;
    }

    @Override
    public String toString() {
        return "SystemUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password=" + Arrays.toString(password) +
                ", appKey=" + Arrays.toString(appKey) +
                ", description='" + description + '\'' +
                ", environment=" + environment +
                '}';
    }
}
