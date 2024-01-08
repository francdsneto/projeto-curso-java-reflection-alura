package br.com.alura.alurator.reflexao.gerador;

import br.com.alura.alurator.reflexao.gerador.anotacao.NomeTagXML;

import java.lang.reflect.Field;
import java.util.Collection;

public class ObjetoParaXML {

    public String converter(Object objeto) {

        StringBuilder xmlBuilder = new StringBuilder();

        if(objeto instanceof Collection)
        {
            xmlBuilder.append(this.converteArray(objeto));
        }
        else
        {
            xmlBuilder.append(this.converteObjeto(objeto));
        }

        return xmlBuilder.toString();

    }

    public String converteArray(Object objeto) {

        Class<?> classeDosObjetos = null;

        StringBuilder xmlBuilder = new StringBuilder();

        if(objeto instanceof Collection obj) {

            var nomeClasse = "";

            for(Object elemento : obj) {

                if(classeDosObjetos == null)
                {
                    classeDosObjetos = elemento.getClass();

                    NomeTagXML annotationClass = classeDosObjetos.getDeclaredAnnotation(NomeTagXML.class);

                    nomeClasse = annotationClass != null ? annotationClass.nome() : classeDosObjetos.getSimpleName();
                    xmlBuilder.append(this.gerarTag(nomeClasse.concat("s"),false));
                }
                xmlBuilder.append(this.converteObjeto(elemento));
            }

            xmlBuilder.append(this.gerarTag(nomeClasse.concat("s"),true));
        }

        return xmlBuilder.toString();

    }

    public String converteObjeto(Object objeto) {

        Class<?> classe = objeto.getClass();

        StringBuilder xmlBuilder = new StringBuilder();

        NomeTagXML classAnnotation = classe.getDeclaredAnnotation(NomeTagXML.class);

        var nomeClasse = classAnnotation != null ? classAnnotation.nome() : classe.getSimpleName();

        xmlBuilder.append(this.gerarTag(nomeClasse,false));

        for(Field field : classe.getDeclaredFields()) {
            field.setAccessible(true);

            NomeTagXML fieldAnnotation = field.getDeclaredAnnotation(NomeTagXML.class);
            var fieldName = fieldAnnotation != null ? fieldAnnotation.nome() : field.getName();

            xmlBuilder.append(gerarTag(fieldName,false));
            try {
                xmlBuilder.append(field.get(objeto));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            xmlBuilder.append(gerarTag(fieldName,true));
        }

        xmlBuilder.append(this.gerarTag(nomeClasse,true));

        return xmlBuilder.toString();
    }

    private String gerarTag(String nome, boolean tagFinal) {
        StringBuilder tagBuilder = new StringBuilder();

        if(tagFinal)
            tagBuilder.append("</");
        else
            tagBuilder.append("<");

        tagBuilder.append(nome.toLowerCase());
        tagBuilder.append(">");
        return tagBuilder.toString();
    }

}
