package com.ciandt.desafio.tdd.exception;

public class IngredienteCadastradoException extends IllegalArgumentException {
    static final String MESSAGE = "_Ingrediente já cadastrado_";

    public IngredienteCadastradoException(){
        super(MESSAGE);
    }
}
