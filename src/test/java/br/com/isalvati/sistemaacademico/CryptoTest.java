package br.com.isalvati.sistemaacademico;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootTest
public class CryptoTest {

    @Test
    public void createCrypto() {
        // criptografia usada na tabela de configuracao para tipos password
        String security = "iKW5+rNHZGHKauFd1gCcUyla0nwLQ3TFa15xoEkH";
        String encodedString = Base64.getEncoder().encodeToString("adminzen".getBytes(StandardCharsets.UTF_8));
        System.out.println("secret =[ " + encodedString);

    }

}
