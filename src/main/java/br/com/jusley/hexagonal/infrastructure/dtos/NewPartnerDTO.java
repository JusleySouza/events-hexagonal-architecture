package br.com.jusley.hexagonal.infrastructure.dtos;

public record NewPartnerDTO(
        String name,
        String cnpj,
        String email)
{}
