package com.ciandt.desafio.tdd.ingredientes;

public enum TipoTopping {
    AVEIA("AVEIA"), MEL("MEL"), CHOCOLATE("CHOCOLATE");

    private final String valor;

    TipoTopping(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
