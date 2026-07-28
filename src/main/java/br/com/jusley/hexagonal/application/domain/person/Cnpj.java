package br.com.jusley.hexagonal.application.domain.person;

import br.com.jusley.hexagonal.application.exceptions.ValidationException;

//value object
public record Cnpj(String value) {

    public Cnpj{
        if(value == null || !value.matches("^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$")){
            throw new ValidationException("Invalid value for Cnpj");
        }
    }
}
