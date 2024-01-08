package br.com.alura.alurator.reflexao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class ManipuladorMetodo {

    private Object instancia;
    private Method metodo;
    private final Map<String, Object> params;

    private BiFunction<Method, InvocationTargetException, Object> tratamentoExcessao;

    public ManipuladorMetodo(Object instancia, Method metodo, Map<String, Object> params) {
        this.instancia = instancia;
        this.metodo = metodo;
        this.params = params;
    }

    public Object invoca() {
        try {
            List<Object> parametros = new ArrayList<>();
            Stream.of(metodo.getParameters())
                    .forEach(p -> parametros.add(params.get(p.getName())));
            return metodo.invoke(instancia,parametros.toArray());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            if(this.tratamentoExcessao != null)
            {
                return this.tratamentoExcessao.apply(metodo,e);
            }
            else
            {
                e.printStackTrace();
                throw new RuntimeException("Erro ao invocar método: ", e.getTargetException());
            }
        }
    }

    public ManipuladorMetodo comTratamentoDeExcecao(BiFunction<Method,InvocationTargetException,Object> tratamentoExcessao) {
        this.tratamentoExcessao = tratamentoExcessao;
        return this;
    }
}
