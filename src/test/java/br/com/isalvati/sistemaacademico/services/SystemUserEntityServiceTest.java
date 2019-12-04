package br.com.isalvati.sistemaacademico.services;

import br.com.isalvati.sistemaacademico.PostgresqlContainer;
import br.com.isalvati.sistemaacademico.UtilTest;
import br.com.isalvati.sistemaacademico.entities.SystemUserEntity;
import br.com.isalvati.sistemaacademico.exception.SistemaAcademicoException;
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
public class SystemUserEntityServiceTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgresqlContainer.getInstance();

    @Autowired
    private SystemUserService systemUserService;

    private SystemUserEntity systemUser;

    @Before
    public void setUp() {
        systemUser = UtilTest.createSystemUser();
    }

    @Test
    @Transactional
    public void findByUsername() throws SistemaAcademicoException {
        systemUserService.save(systemUser);
        SystemUserEntity user = systemUserService.findByUsername("SA-ADMIN");
        Assert.assertNotNull(user);
    }

    @Test
    @Transactional
    public void create() throws SistemaAcademicoException {
        SystemUserEntity usr = systemUserService.save(systemUser);
        Assert.assertNotNull(usr);

        System.out.println(" INSERT INTO academico.system_user (username, password, app_key, description, environment)\n" +
                "VALUES ('" + usr.getUsername() + "', " +
                "    decode('" + bytesToHex(usr.getPassword()) + "', 'hex'),\n" +
                "    decode('" + bytesToHex(usr.getAppKey()) + "',  'hex'),\n" +
                " '" + usr.getDescription() + "', '" + usr.getEnvironment().name() + "');\n");

    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}
