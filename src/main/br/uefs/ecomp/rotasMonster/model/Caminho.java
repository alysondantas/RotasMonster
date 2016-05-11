package br.uefs.ecomp.rotasMonster.model;

public class Caminho {
	private Distancia origemColeta; //distancia da origem para a coleta
	private Distancia coletaDestino; //distancia da coleta para o destino
	/**
	 * Construtor da classe
	 * @param um
	 * @param dois
	 */
	public Caminho(Distancia um, Distancia dois){
		this.setOrigemColeta(um);
		this.setColetaDestino(dois);
	}
	/**
	 * 
	 * @return distancia da origem para a coleta
	 */
	public Distancia getOrigemColeta() {
		return origemColeta;
	}
	/**
	 * Modifica a distancia da origem para a coleta
	 * @param origemColeta
	 */
	public void setOrigemColeta(Distancia origemColeta) {
		this.origemColeta = origemColeta;
	}
	/**
	 * 
	 * @return distancia da coleta para destino
	 */
	public Distancia getColetaDestino() {
		return coletaDestino;
	}
	/**
	 * Modifica distancia da coleta para destino
	 * @param coletaDestino
	 */
	public void setColetaDestino(Distancia coletaDestino) {
		this.coletaDestino = coletaDestino;
	}
}
