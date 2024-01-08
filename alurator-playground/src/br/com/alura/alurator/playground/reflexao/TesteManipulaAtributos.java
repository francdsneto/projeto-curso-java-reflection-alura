package br.com.alura.alurator.playground.reflexao;

import br.com.alura.alurator.playground.modelo.Produto;

import java.lang.reflect.Field;

public class TesteManipulaAtributos {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Object produto = new Produto("Produto 1",20.0,"Marca 1");
        Class<?> classe = produto.getClass();

//        System.out.println(classe.getField("id"));

        for(Field f : classe.getDeclaredFields())
        {
            f.setAccessible(true);
            System.out.println(f.getName() + " : " + f.get(produto) + " : " +f.getType().getName());
        }

    }

}
