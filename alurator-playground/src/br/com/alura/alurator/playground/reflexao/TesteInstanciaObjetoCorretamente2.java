package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.InvocationTargetException;

public class TesteInstanciaObjetoCorretamente2 {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        Class<?> controle2Class1 = Class.forName("br.com.alura.alurator.playground.controle.Controle2");

        /**
         * O newInstance da classe Class não checa a exception que o construtor padrão publico está lançando.
         *
         * No exemplo abaixo, receberiamos uma exception do tipo IOException
         */
        //controle2Class1.newInstance();

        /**
         * O newInstance do método getDeclaredConstructor, diferentemente do newInstance de class
         * informa sobre a exception, possibilitando ser tratada.
         */

        try {
            controle2Class1.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            System.out.println(e.getTargetException());
        }


    }

}

