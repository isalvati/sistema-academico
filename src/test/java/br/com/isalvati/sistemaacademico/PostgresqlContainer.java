package br.com.isalvati.sistemaacademico;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresqlContainer extends PostgreSQLContainer<PostgresqlContainer> {
    private static final String IMAGE_VERSION = "postgres:10.6";
    private static PostgresqlContainer container;

    private PostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    public static PostgresqlContainer getInstance() {
        if (container == null) {
            container = new PostgresqlContainer()
                    .withDatabaseName("db_sistema_academico")
                    .withUsername("base_user")
                    .withPassword("admin");
        }
        return container;
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            System.out.println(BaseApplicationTests.postgreSQLContainer.getJdbcUrl());
            TestPropertyValues.of(
                    "spring.datasource.url=" + BaseApplicationTests.postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + BaseApplicationTests.postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + BaseApplicationTests.postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
