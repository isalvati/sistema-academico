package br.com.isalvati.sistemaacademico.security.cryptography.impl;

import br.com.isalvati.sistemaacademico.exception.CryptographyException;
import br.com.isalvati.sistemaacademico.security.cryptography.Cryptography;
import br.com.isalvati.sistemaacademico.security.cryptography.CryptographyType;

/**
 * Generate SHA512 cryptography.
 */
public class CryptographySHA512 extends Cryptography {

    /**
     * Encrypts given string
     * @param value string to encrypt
     * @return string encrypted
     * @throws CryptographyException
     */
    public static String encrypt(final String value) throws CryptographyException {
        return encrypt(CryptographyType.SHA512, value);
    }

}
