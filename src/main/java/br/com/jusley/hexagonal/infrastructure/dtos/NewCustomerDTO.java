package br.com.jusley.hexagonal.infrastructure.dtos;

public record NewCustomerDTO(
        String name,
        String cpf,
        String email
) {}
