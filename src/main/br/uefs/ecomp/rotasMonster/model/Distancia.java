/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas e Bruno Menezes de Lima

Componente Curricular: MI - Algoritmos II

Concluido em: 03/05/2016

Declaro que este código foi elaborado por esta dupla e não contém nenhum

trecho de código de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código

de outra autoria que não a minha está destacado com uma citação para o autor e a fonte

do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

 ******************************************************************************************/
package br.uefs.ecomp.rotasMonster.model;

import br.uefs.ecomp.rotasMonster.util.Lista;

public class Distancia {
	private Ponto p; //ponto que é o distino atual
	private double tempo; //tempo ate esse ponto
	private Lista pontos; // lista com pontos ate esse ponto
	/**
	 * Construtor da classe
	 * @param p
	 * @param temp
	 */
	public Distancia(Ponto p, double temp){
		this.p = p;
		this.tempo = temp;
		setPontos(new Lista());
	}
	/**
	 * 
	 * @return o ponto
	 */
	public Ponto getP() {
		return p;
	}
	/**
	 * Modifica o ponto
	 * @param p
	 */
	public void setP(Ponto p) {
		this.p = p;
	}
	/**
	 * 
	 * @return o tempo
	 */
	public double getTempo() {
		return tempo;
	}
	/**
	 * Modifica o tempo
	 * @param tempo
	 */
	public void setTempo(double tempo) {
		this.tempo = tempo;
	}
	/**
	 * 
	 * @return a lista de pontos
	 */
	public Lista getPontos() {
		return pontos;
	}
	/**
	 * Modifica a lista de pontos
	 * @param pontos
	 */
	public void setPontos(Lista pontos) {
		this.pontos = pontos;
	}
}
