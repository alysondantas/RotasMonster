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
