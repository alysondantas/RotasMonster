package br.uefs.ecomp.rotasMonster.model;

import br.uefs.ecomp.rotasMonster.util.IGrafo;
import br.uefs.ecomp.rotasMonster.util.Iterador;
import br.uefs.ecomp.rotasMonster.util.Lista;
import br.uefs.ecomp.rotasMonster.util.MeuIterador;

public class Grafo implements IGrafo {
	Lista pontos;
	/**
	 * Construtor do grafo, instancia uma lista de pontos
	 */
	public Grafo(){
		pontos = new Lista();
	}
	/**
	 * Obtem o tamanho do grafo com base no tamanho da lista de pontos
	 */
	@Override
	public int obterTamanho() {
		return this.pontos.obterTamanho();
	}
	/**
	 * Metodo de remover o ponto
	 */
	@Override
	public Object removePonto(Object ponto) {
		if(pontos.estaVazia()){
			return null;
		}
		Ponto auxP = null;
		ponto = (Ponto) ponto;
		MeuIterador iterador = (MeuIterador) pontos.iterador();
		int index = 0;
		while(iterador.temProximo()){
			auxP = (Ponto) iterador.obterProximo();
			if(auxP.equals(ponto)){
				pontos.remover(index);
			}
			index++;
		}
		return auxP;
	}
	/**
	 * Metodo de inserir o ponto
	 */
	@Override
	public void inserirPonto(Object ponto) {
		pontos.inserirInicio(ponto);
	}
	/**
	 * Metodo de retornar o iterador de pontos
	 */
	@Override
	public Iterador iterador() {
		return pontos.iterador();
	}
	/**
	 * Metodo que reseta todos os boolean dos pontos do grafo com false
	 */
	public void setPassouPontos(){
		MeuIterador iterador = (MeuIterador) pontos.iterador();
		Ponto p;
		while(iterador.temProximo()){
			p = (Ponto) iterador.obterProximo();
			p.setPassou(false);
		}
	}

}
