package br.com.alura.alurator.playground.reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TesteInvocaMetodosSemParametro {

    public static void main(String[] args) throws Exception {

        Class<?> subControleClasse = Class.forName("br.com.alura.alurator.playground.controle.SubControle");

        Constructor<?> construtorPadrao = subControleClasse.getDeclaredConstructor();

        Object subControle = construtorPadrao.newInstance();

//        for (Method m:
//             subControleClasse.getMethods()) {
//            System.out.println(m);
//        }

//        for (Method m:
//                subControleClasse.getDeclaredMethods()) {
//            System.out.println(m);
//        }

        Method m = subControleClasse.getDeclaredMethod("metodoSubControle1");
        m.setAccessible(true);

        Object retorno = m.invoke(subControle);

        System.out.println(retorno);

    }

}
