package br.com.jusley.hexagonal.application.domain.person;

import br.com.jusley.hexagonal.application.exceptions.ValidationException;

//value object
public record Name(String value) {

    public Name{
        if(value == null){
            throw new ValidationException("Invalid value for Name");
        }
    }
}
