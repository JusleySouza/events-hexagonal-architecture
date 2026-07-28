package br.com.jusley.hexagonal.application.domain.person;

import br.com.jusley.hexagonal.application.exceptions.ValidationException;

//value object
public record Cpf(String value) {

    public Cpf{
        if(value == null || !value.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")){
            throw new ValidationException("Invalid value for Cpf");
        }
    }
}
