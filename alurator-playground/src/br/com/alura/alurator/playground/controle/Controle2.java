package br.com.alura.alurator.playground.controle;

import java.io.IOException;
import java.util.List;

public class Controle2 {
	private List<String> lista = List.of("item 1", "item 2", "item 3");

	public Controle2() throws IOException {
		throw new IOException();
	}

	public Controle2(String s) {
	}

	private Controle2(String s, String t) {
	}

	public List<String> getLista() {
		return lista;
	}
}
