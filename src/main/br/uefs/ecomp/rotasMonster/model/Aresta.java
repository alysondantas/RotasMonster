package br.uefs.ecomp.rotasMonster.model;

public class Aresta {
	private Ponto pontoDestino; //ponto para o destino
	private double tempo = 0; // tempo ate o destino
	/**
	 * Construtor da classe
	 * @param pontoDest
	 * @param tempo
	 */
	public Aresta(Ponto pontoDest, double tempo){
		this.pontoDestino = pontoDest;
		this.tempo = tempo;
	}
	
	/**
	 * @return the pontoDestino
	 */
	public Ponto getPontoDestino() {
		return pontoDestino;
	}
	/**
	 * @param pontoDestino the pontoDestino to set
	 */
	public void setPontoDestino(Ponto pontoDestino) {
		this.pontoDestino = pontoDestino;
	}
	/**
	 * @return the tempo
	 */
	public double gettempo() {
		return tempo;
	}
	/**
	 * @param tempo the tempo to set
	 */
	public void settempo(double tempo) {
		this.tempo = tempo;
	}
}
