package br.com.isalvati.sistemaacademico.security.services.impl;

import br.com.isalvati.sistemaacademico.entities.SystemUserEntity;
import br.com.isalvati.sistemaacademico.security.util.UserFactory;
import br.com.isalvati.sistemaacademico.services.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SystemUserService systemUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUserEntity user = systemUserService.findByUsername(username);
        return UserFactory.createUser(user.getId(), user.getUsername(), "", "ROLE_" + user.getUsername().toUpperCase());
    }

}
