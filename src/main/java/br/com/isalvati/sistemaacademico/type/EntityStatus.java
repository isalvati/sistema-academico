package br.com.isalvati.sistemaacademico.type;

public enum EntityStatus {

    ACTIVE("active"),
    INACTIVE("inactive");

    public final String status;

    EntityStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
