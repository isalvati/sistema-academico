package br.com.isalvati.sistemaacademico.security.services;

import br.com.isalvati.sistemaacademico.entities.SystemUserEntity;

/*
 * @author barney
 * @since 05/04/19 16:57
 */
public interface LoginService {

    SystemUserEntity login(String userName, String secret);

}
