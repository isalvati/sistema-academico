package br.com.isalvati.sistemaacademico.exception;

public enum ErrorCode {

    REGISTERED_USER("REGISTERED_USER", "R12");

    private String name;
    private String code;

    ErrorCode(String name) {
        this.name = name;
    }

    ErrorCode(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

}
