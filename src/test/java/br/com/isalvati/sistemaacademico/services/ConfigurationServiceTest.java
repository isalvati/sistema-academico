package br.com.isalvati.sistemaacademico.services;

import br.com.isalvati.sistemaacademico.PostgresqlContainer;
import br.com.isalvati.sistemaacademico.UtilTest;
import br.com.isalvati.sistemaacademico.exception.BaseException;
import br.com.isalvati.sistemaacademico.entities.ConfigurationEntity;
import br.com.isalvati.sistemaacademico.type.ConfigurationName;
import br.com.isalvati.sistemaacademico.type.Environment;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {PostgresqlContainer.Initializer.class})
public class ConfigurationServiceTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgresqlContainer.getInstance();

    @Autowired
    private ConfigurationService configurationService;

    @Before
    public void setUp() throws BaseException {
        ConfigurationEntity config = UtilTest.createConfig();
        configurationService.save(config);
    }

    @Test
    @Transactional
    public void findByName() {
        Assert.assertEquals("testeValue", configurationService.findByName(ConfigurationName.TEST.getName()).getValue());
    }

    @Test
    @Transactional
    public void findByValue() {
        Assert.assertNotNull(configurationService.findByValue("testeValue"));
    }

    @Test
    @Transactional
    public void findByEnvironment() {
        Assert.assertNotNull(configurationService.findByEnvironment(Environment.DEV));
    }

}
