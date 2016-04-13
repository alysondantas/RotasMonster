package br.uefs.ecomp.rotasMonster.util;

public class Aresta {
	private Ponto pontoOrigem;
	private Ponto pontoDestino;
	private int distancia;
	
	public Aresta(Ponto pontoOrig, Ponto pontoDest, int distancia){
		this.pontoOrigem = pontoOrig;
		this.pontoDestino = pontoDest;
		this.distancia = distancia;
	}
	
	/**
	 * @return the pontoOrigem
	 */
	public Ponto getPontoOrigem() {
		return pontoOrigem;
	}
	/**
	 * @param pontoOrigem the pontoOrigem to set
	 */
	public void setPontoOrigem(Ponto pontoOrigem) {
		this.pontoOrigem = pontoOrigem;
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
