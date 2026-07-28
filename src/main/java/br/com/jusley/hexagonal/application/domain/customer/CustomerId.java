package br.com.jusley.hexagonal.application.domain.customer;

import br.com.jusley.hexagonal.application.exceptions.ValidationException;
import java.util.UUID;

public record CustomerId(String value) {

    public CustomerId {
        if(value == null){
            throw new ValidationException("Invalid value for CustomerId");
        }
    }

    public static CustomerId unique() {
        return new CustomerId(UUID.randomUUID().toString());
    }

    public static CustomerId with(final String value) {
        try {
            return new CustomerId(UUID.fromString(value).toString());
        } catch (IllegalArgumentException e) {
            throw new ValidationException("Invalid value for CustomerId");
        }
    }

}
