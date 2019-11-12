package br.com.isalvati.sistemaacademico;

import br.com.isalvati.sistemaacademico.entities.ConfigurationEntity;
import br.com.isalvati.sistemaacademico.type.ConfigurationType;
import br.com.isalvati.sistemaacademico.type.Environment;
import br.com.isalvati.sistemaacademico.entities.SystemUserEntity;
import br.com.isalvati.sistemaacademico.security.cryptography.Passwords;
import br.com.isalvati.sistemaacademico.type.ConfigurationName;

public class UtilTest {

    public static SystemUserEntity createSystemUser() {
        SystemUserEntity usr = new SystemUserEntity();
        usr.setDescription("Usuario para servico de cobranca");
        usr.setEnvironment(Environment.DEV);
        byte[] salt = Passwords.getNextSalt();
        usr.setAppKey(salt);
        byte[] pass = Passwords.hash(("123").toCharArray(), salt);
        usr.setPassword(pass);
        usr.setUsername("SA_ADMIN");
        usr.setCreatedBy(0L);
        return usr;
    }

    public static ConfigurationEntity createConfig() {
        ConfigurationEntity config = new ConfigurationEntity();
        config.setName(ConfigurationName.TEST.getName());
        config.setEnvironment(Environment.DEV);
        config.setLabel("testeLabel");
        config.setType(ConfigurationType.TEXT);
        config.setValue("testeValue");
        config.setCreatedBy(0L);
        return config;
    }

}
