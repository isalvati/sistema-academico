package br.com.isalvati.sistemaacademico.security.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {

    private UserFactory() {
    }

    public static User createUser(Long id, String username, String secret, String role) {
        return new User(id, username, secret,  mapToGrantedAuthorities(new SimpleGrantedAuthority(role)));
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(SimpleGrantedAuthority authority) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return authorities;
    }
}
