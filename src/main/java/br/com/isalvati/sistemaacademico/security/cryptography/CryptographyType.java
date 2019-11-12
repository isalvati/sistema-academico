package br.com.isalvati.sistemaacademico.security.cryptography;

/**
 * <p>
 *     Cryptography types.
 * </p>
 *
 */
public enum CryptographyType {

    SHA512( "SHA-512" );

    final String value;

    CryptographyType( final String value ) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
