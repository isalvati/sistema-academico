package br.com.isalvati.sistemaacademico.exception;

public class SistemaAcademicoException extends Exception {

    private ErrorCode code;

    public SistemaAcademicoException(ErrorCode code) {
        super(code.getName());
        this.code = code;
    }

    @Deprecated
    public SistemaAcademicoException(String exception) {
        super(exception);
    }

    public ErrorCode getCode() {
        return code;
    }

}
