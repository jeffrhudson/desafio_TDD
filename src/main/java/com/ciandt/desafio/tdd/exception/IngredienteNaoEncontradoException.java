package com.ciandt.desafio.tdd.exception;

public class IngredienteNaoEncontradoException extends IllegalArgumentException {
    static final String MESSAGE = "_Ingrediente não encontrado_";

    public IngredienteNaoEncontradoException(){
        super(MESSAGE);
    }
}
