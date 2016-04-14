package br.uefs.ecomp.rotasMonster.util;

public class Aresta {
	private Ponto pontoDestino;
	private int distancia = 0;
	
	public Aresta(Ponto pontoDest, int distancia){
		this.pontoDestino = pontoDest;
		this.distancia = distancia;
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
	 * @return the distancia
	 */
	public int getDistancia() {
		return distancia;
	}
	/**
	 * @param distancia the distancia to set
	 */
	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}
}
