package br.com.isalvati.sistemaacademico.security.cryptography;

import br.com.isalvati.sistemaacademico.exception.CryptographyException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * Contains cryptography implementation.
 * <p/>
 * </p>
 */
public abstract class Cryptography {

    /**
     * Encrypt string
     * @param cryptographyType
     * @param value
     * @return
     * @throws CryptographyException
     */
    public static String encrypt(CryptographyType cryptographyType, final String value) throws CryptographyException {

        StringBuilder out = new StringBuilder();

        if ( value == null ) {
            throw new IllegalArgumentException( "value cannot be null" );
        }

        MessageDigest messageDigest;
		try {
			messageDigest = MessageDigest.getInstance( cryptographyType.getValue() );
		} catch (NoSuchAlgorithmException e) {
			throw new CryptographyException("Cryptography provider not found.", e);
		}
        messageDigest.update( value.getBytes() );

        byte[] mb = messageDigest.digest();

        for ( byte temp : mb ) {
            String s = Integer.toHexString( temp );
            while (s.length() < 2) {
                s = "0" + s;
            }
            s = s.substring( s.length() - 2 );
            out.append( s );
        }
        return out.toString();
    }
}
