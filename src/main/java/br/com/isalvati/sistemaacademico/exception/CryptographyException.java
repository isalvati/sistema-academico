package br.com.isalvati.sistemaacademico.exception;

/**
 * Exceptions Unchecked support class.
 */
public class CryptographyException extends Exception {

    private static final long serialVersionUID = 8295789675138093116L;

    /**
     * Default constructor.
     * A causa da exception não é definida, ou seja, fica como null.
     */
    public CryptographyException() {
        super();
    }

    /**
     * Defined message and cause constructor.
     *
     * @param message exception details.
     * @param cause   Exception cause.
     */
    public CryptographyException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Defined message constructor.
     *
     * @param message exception details.
     */
    public CryptographyException(String message) {
        super(message);
    }

    /**
     * Defined cause constructor.
     *
     * @param cause Exception cause.
     */
    public CryptographyException(Throwable cause) {
        super(cause);
    }


}
