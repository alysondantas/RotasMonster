package br.uefs.ecomp.rotasMonster.model;

public class Aresta {
	private Ponto pontoDestino;
	private int tempo = 0;
	
	public Aresta(Ponto pontoDest, int tempo){
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
	public int gettempo() {
		return tempo;
	}
	/**
	 * @param tempo the tempo to set
	 */
	public void settempo(int tempo) {
		this.tempo = tempo;
	}
}
