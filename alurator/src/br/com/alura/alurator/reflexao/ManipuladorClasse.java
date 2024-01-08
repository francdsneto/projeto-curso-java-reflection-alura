package br.com.alura.alurator.reflexao;

import java.lang.reflect.Constructor;

public class ManipuladorClasse {

    private final Class<?> classe;

    public ManipuladorClasse(Class<?> classe) {
        this.classe = classe;
    }

    public ManipuladorConstrutor getConstrutorPadrao() {
        try {
            Constructor<?> construtorPadrao = classe.getDeclaredConstructor();
            return new ManipuladorConstrutor(construtorPadrao);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public ManipuladorObjeto criaInstancia() {
        Object instancia = this.getConstrutorPadrao().invoca();
        return new ManipuladorObjeto(instancia);
    }
}
