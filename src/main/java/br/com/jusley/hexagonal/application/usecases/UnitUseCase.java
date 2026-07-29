package br.com.jusley.hexagonal.application.usecases;

public abstract class UnitUseCase<INPUT> {

    // Esse caso de uso tem apenas um input proprio.
    public abstract void execute(INPUT input);

}
