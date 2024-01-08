package br.com.alura.alurator.reflexao.ioc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.stream.Stream;

public class ContainerIoC {

    private Map<Class<?>, Class<?>> mapaDeTipos = new HashMap<>();

    public Object getInstancia(Class<?> tipoFonte) {

        Class<?> tipoDestino = mapaDeTipos.get(tipoFonte);

        if (tipoDestino != null) {
            return getInstancia(tipoDestino);
        }

        Stream<Constructor<?>> construtores = Stream.of(tipoFonte.getDeclaredConstructors());
        Optional<Constructor<?>> construtorPadrao = construtores.filter(constructor -> constructor.getParameterCount() == 0).findFirst();

        try {

            if (construtorPadrao.isPresent()) {
                return construtorPadrao.get().newInstance();
            } else {
                Constructor<?> constructor = tipoFonte.getDeclaredConstructors()[0];

                List<Object> params = new ArrayList<>();

                for (Parameter param : constructor.getParameters()) {
                    Class<?> parameterType = param.getType();
                    params.add(getInstancia(parameterType));
                }

                return constructor.newInstance(params.toArray());
            }

        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao instanciar objeto: " + e.getTargetException());
        }

    }

    public <T, K extends T> void registra(Class<T> tipoFonte, Class<K> tipoDestino) {

        if(tipoFonte.isAssignableFrom(tipoDestino))
            mapaDeTipos.put(tipoFonte, tipoDestino);
        else
            throw new ClassCastException("Não foi possível converter " + tipoFonte.getName() + " para " + tipoDestino.getName());

    }
}