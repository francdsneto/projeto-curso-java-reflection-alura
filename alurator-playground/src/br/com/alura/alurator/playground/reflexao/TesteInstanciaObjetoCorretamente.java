package br.com.alura.alurator.playground.reflexao;

import br.com.alura.alurator.playground.controle.SubControle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class TesteInstanciaObjetoCorretamente {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<SubControle> subControleClass1 = SubControle.class;

        Class<?> subControleClass2 = Class.forName("br.com.alura.alurator.playground.controle.SubControle");

        Class<?> subControleClass3 = Class.forName("br.com.alura.alurator.playground.controle.Controle");


        /**
         * Obtendo o construtor público e sem parametro
         */
        Constructor<SubControle> constructorSubControle = subControleClass1.getConstructor();

        System.out.println(constructorSubControle);

        /**
         * Obtendo o construtor com parametro
         *
         * Essa chamada abaixo gerará um NoSuchMethodException, pois o getConstructor só retorna
         * construtores públicos
         *
         * Para obter o constructor privado, precisamos usar o getDeclaredConstructor
         */
        //Constructor<SubControle> constructorSubControle2 = subControleClass1.getConstructor(String.class);

        Constructor<SubControle> constructorSubControle2 = subControleClass1.getDeclaredConstructor(String.class);

        System.out.println(constructorSubControle2);

        /**
         * Ao tentar executar o newInstance recebemos um IllegalAcessException pois
         * este é um construtor privado.
         *
         * Para conseguir criar o objeto utilizando o construtor privado utilizamos o
         * setAccessible como true
         */
        //Object subControle = constructorSubControle2.newInstance("oi");

        constructorSubControle2.setAccessible(true);
        Object subControle = constructorSubControle2.newInstance("oi");

        System.out.println(subControle);

    }

}

