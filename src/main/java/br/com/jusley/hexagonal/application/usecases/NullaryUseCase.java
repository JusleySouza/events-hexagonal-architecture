package br.com.jusley.hexagonal.application.usecases;

public abstract class NullaryUseCase<OUTPUT> {

    //Esse caso de uso retorna apenas um output proprio. Não retorna a entidade, o agregado ou o objeto de valor.
    public abstract OUTPUT execute();

}
