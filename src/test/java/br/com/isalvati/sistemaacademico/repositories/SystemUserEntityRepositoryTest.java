package br.com.isalvati.sistemaacademico.repositories;

import br.com.isalvati.sistemaacademico.PostgresqlContainer;
import br.com.isalvati.sistemaacademico.UtilTest;
import org.junit.Assert;
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
public class SystemUserEntityRepositoryTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgresqlContainer.getInstance();

    @Autowired
    private SystemUserRepository repository;

    @Test
    @Transactional
    public void findByUsername() {
        repository.save(UtilTest.createSystemUser());
        Assert.assertNotNull(repository.findByUsername("SA_ADMIN"));
    }


}
