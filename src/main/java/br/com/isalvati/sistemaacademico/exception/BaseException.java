package br.com.isalvati.sistemaacademico.exception;

public class BaseException extends Exception {

    private ErrorCode code;

    public BaseException(ErrorCode code) {
        super(code.getName());
        this.code = code;
    }

    @Deprecated
    public BaseException(String exception) {
        super(exception);
    }

    public ErrorCode getCode() {
        return code;
    }

}
