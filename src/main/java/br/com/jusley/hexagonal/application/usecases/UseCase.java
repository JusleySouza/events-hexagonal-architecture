package br.com.jusley.hexagonal.application.usecases;

public abstract class UseCase<INPUT, OUTPUT> {

    //1. Cada caso de uso tem um input e um output proprio. Não retorna a entidade, o agregado ou o objeto de valor.
    //2. O caso de uso implementa o padrao Command, ou seja, tem um metodo execute que recebe um input e retorna um output.
    public abstract OUTPUT execute(INPUT input);

}
