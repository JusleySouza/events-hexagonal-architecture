package br.com.jusley.hexagonal.infrastructure.dtos;

public record SubscribeDTO(
        String customerId,
        String eventId)
{}
