package br.com.isalvati.sistemaacademico.entities;

import br.com.isalvati.sistemaacademico.type.Environment;
import br.com.isalvati.sistemaacademico.type.ConfigurationType;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

@Entity
@Table(name = "configuration")
public class ConfigurationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ConfigurationType type;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "environment", nullable = false)
    @Enumerated(EnumType.STRING)
    private Environment environment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        if (ConfigurationType.PASSWORD.equals(this.type)) {
            byte[] decodedString = Base64.getDecoder().decode(value.getBytes(StandardCharsets.UTF_8));
            return new String(decodedString, StandardCharsets.UTF_8);
        }
        return value;
    }

    public void setValue(String value) {
        if (ConfigurationType.PASSWORD.equals(this.type)) {
            value = Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
        }
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public ConfigurationType getType() {
        return type;
    }

    public void setType(ConfigurationType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfigurationEntity that = (ConfigurationEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(value, that.value) &&
                type == that.type &&
                Objects.equals(label, that.label) &&
                environment == that.environment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, value, type, label, environment);
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", type=" + type +
                ", label='" + label + '\'' +
                ", environment=" + environment +
                '}';
    }

}
