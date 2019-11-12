package br.com.isalvati.sistemaacademico.security.controller;

import br.com.isalvati.sistemaacademico.PostgresqlContainer;
import br.com.isalvati.sistemaacademico.UtilTest;
import br.com.isalvati.sistemaacademico.services.SystemUserService;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(initializers = {PostgresqlContainer.Initializer.class})
public class TokenControllerTest {

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = PostgresqlContainer.getInstance();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SystemUserService systemUserService;

    @Test
    @Transactional
    public void getToken() throws Exception {
        systemUserService.save(UtilTest.createSystemUser());
        HttpHeaders headers = new HttpHeaders();
        headers.add("key", "SA_ADMIN");
        headers.add("secret", "123");
        mockMvc.perform(MockMvcRequestBuilders.get("/token").headers(headers).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorization").isNotEmpty());
    }

    @Test
    @Transactional
    public void getTokenUna() throws Exception {
        systemUserService.save(UtilTest.createSystemUser());
        HttpHeaders headers = new HttpHeaders();
        headers.add("key", "SA_ADMIN");
        headers.add("secret", "123456");

        mockMvc.perform(MockMvcRequestBuilders.get("/token").headers(headers).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

}
