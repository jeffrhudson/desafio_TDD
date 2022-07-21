package com.ciandt.desafio.tdd.ingredientes;

public interface Ingrediente <T> extends Comparable<Ingrediente<T>> {
    T obterTipo();

    @Override
    default int compareTo(Ingrediente<T> ingrediente) {
        return this.obterTipo().toString().compareToIgnoreCase(ingrediente.obterTipo().toString());
    }
}
