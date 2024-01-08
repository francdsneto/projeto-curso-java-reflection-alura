package br.com.alura.alurator.playground.anotacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Retention diz em que momento a anotação será levada em consideração
 * @Target informa onde a anotação poderá ser usada
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.TYPE, ElementType.FIELD})
public @interface NomeTagXML {

    /**
     * Por convenção, td atributo único deve receber o nome 'value'
     * public String value();
     */
    public String instanciaNome();
    public String collectionNome() default "";
}
