package br.com.alura.alurator.playground.reflexao;

import br.com.alura.alurator.playground.controle.Controle;
import br.com.alura.alurator.playground.controle.SubControle;

import java.lang.reflect.InvocationTargetException;

public class TesteInstanciaObjeto {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        /**
         * Obtenção de instâncias de Class<type>
         */
        Class<Controle> controleClasse1 = Controle.class;

        /**
         * o <? extends Controle> indica que qualquer classe que estenda de controle é aceita
         */
        Controle controle = new Controle();
        Class<? extends Controle> controleClasse2 = controle.getClass();
        SubControle subControle = new SubControle();
        controleClasse2 = subControle.getClass();

        /**
         * Class parametrizado por ? indica que o Class pode receber qualquer class type
         */
        Class<?> controleClasse3 = Class.forName("br.com.alura.alurator.playground.controle.Controle");

        /**
         * Criando um objeto da classe controle
         *
         * o método new instance foi depreciado desde a versão 9 do java
         */
        Object objetoControle = controleClasse1.newInstance();

        System.out.println(objetoControle instanceof Controle);

        /**
         * Forma correta de se instanciar um objeto após a depreciação do newInstance
         */
        Object objetoControle2 = controleClasse1.getDeclaredConstructor().newInstance();

        System.out.println(objetoControle2 instanceof Controle);

        /**
         * Como no controleClasse1 nós passamos o tipo de classe para a classe Class
         * é possível instanciar diretamente o new Instance para um objeto do tipo Controle
         */
        Controle controleInstanciadoDeClass = controleClasse1.getDeclaredConstructor().newInstance();

        /**
         * O mesmo não pode ser feito para o controleClasse3 por que o parametro de Class é uma ?
         * ou seja, qualquer tipo de classe.
         *
         * A tentativa causará um erro informando que os tipos são incompativeis.
         *
         * Para instânciar devemos passar um Object, que será, de qualquer forma, um objeto do tipo controle.
         */
        //Controle controleInstanciadoDeClass2 = controleClasse3.getDeclaredConstructor().newInstance();
        Object controleInstanciadoDeClass2 = controleClasse3.getDeclaredConstructor().newInstance();

        System.out.println(controleInstanciadoDeClass2 instanceof Controle);
    }

}
