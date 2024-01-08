package br.com.alura.alurator.reflexao;

public class Reflexao {

    public ManipuladorClasse refleteClasse(String fullyQualifiedName) {
        Class<?> classe = getClasse(fullyQualifiedName);
        return new ManipuladorClasse(classe);
    }

    public Class<?> getClasse(String fullyQualifiedName) {
        try {
            return Class.forName(fullyQualifiedName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
