package br.com.isalvati.sistemaacademico.security.util;

import br.com.isalvati.sistemaacademico.PostgresqlContainer;
import br.com.isalvati.sistemaacademico.UtilTest;
import br.com.isalvati.sistemaacademico.exception.BaseException;
import br.com.isalvati.sistemaacademico.services.SystemUserService;
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
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(initializers = {PostgresqlContainer.Initializer.class})
public class JwtTokenTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgresqlContainer.getInstance();

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private SystemUserService systemUserService;

    private String token = "";

    @Before
    public void setUp() throws Exception {
        systemUserService.save(UtilTest.createSystemUser());
        token = jwtToken.obterToken(UserFactory.createUser(2L, "SA_ADMIN", "123", "ROLE_ADMIN"));
    }

    @Test
    @Transactional
    public void obterToken() throws BaseException {
        System.out.println(token);
        Assert.assertTrue(jwtToken.validToken(token));
    }

    @Test
    @Transactional
    public void getUsername() {
        Assert.assertEquals("SA_ADMIN", jwtToken.getUsername(token));
    }

    @Test
    @Transactional
    public void getAudience() {
        Assert.assertEquals("SA_ADMIN", jwtToken.getAudience(token));
    }

    @Test
    @Transactional
    public void getIssuer() {
        Assert.assertEquals("PP_ADMIN", jwtToken.getIssuer(token));
    }

    @Test
    @Transactional
    public void getId() {
        Assert.assertNotNull(jwtToken.getId(token));
    }

    @Test
    @Transactional
    public void getExpirationDate() {
        Assert.assertEquals(jwtToken.getExpirationDate(token).getDate(), new Date(System.currentTimeMillis() + (1440 * 60 * 1000)).getDate());
    }

    @Test
    @Transactional
    public void refeshToken() {
        jwtToken.refreshToken(token);
        Assert.assertEquals(jwtToken.getExpirationDate(token).getDate(), new Date(System.currentTimeMillis() + (1440 * 60 * 1000)).getDate());
    }
}
