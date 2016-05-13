/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas e Bruno Menezes de Lima

Componente Curricular: MI - Algoritmos II

Concluido em: 03/05/2016

Declaro que este c�digo foi elaborado por esta dupla e n�o cont�m nenhum

trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo

de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte

do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.

 ******************************************************************************************/
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
