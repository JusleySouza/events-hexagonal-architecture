package br.com.jusley.hexagonal.application.domain.person;

import br.com.jusley.hexagonal.application.exceptions.ValidationException;

//value object
public record Email(String value) {

    public Email{
        if(value == null || !value.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")){
            throw new ValidationException("Invalid value for Email");
        }
    }
}
