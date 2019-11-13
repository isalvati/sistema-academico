package br.com.isalvati.sistemaacademico.entities;

import br.com.isalvati.sistemaacademico.dto.user.SystemUser;
import br.com.isalvati.sistemaacademico.type.UserProfile;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student")
public class StudentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "birthdate", nullable = false)
    private String birthdate;

    @Column(name = "profile")
    @Enumerated(EnumType.STRING)
    private UserProfile profile;

    @OneToOne(fetch = FetchType.EAGER)
    private SystemUserEntity systemUser;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public SystemUserEntity getSystemUser() {
        return systemUser;
    }

    public void setSystemUser(SystemUserEntity systemUser) {
        this.systemUser = systemUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StudentEntity that = (StudentEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(document, that.document) &&
                Objects.equals(birthdate, that.birthdate) &&
                profile == that.profile &&
                Objects.equals(systemUser, that.systemUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, name, document, birthdate, profile, systemUser);
    }
}
