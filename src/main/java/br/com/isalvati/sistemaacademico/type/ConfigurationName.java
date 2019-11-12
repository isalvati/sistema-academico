package br.com.isalvati.sistemaacademico.type;

public enum ConfigurationName {

    TEST("test");

    private final String name;

    ConfigurationName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
