package br.com.alura.alurator.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ManipuladorConstrutor {
    private final Constructor<?> construtor;

    public ManipuladorConstrutor(Constructor<?> construtor) {
        this.construtor = construtor;
    }

    public Object invoca() {
        try {
            return this.construtor.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao instanciar objeto construtor! "+e.getTargetException());
        }
    }
}
