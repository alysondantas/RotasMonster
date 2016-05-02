package br.uefs.ecomp.rotasMonster.model;

public class Caminho {
	private Distancia origemColeta;
	private Distancia coletaDestino;
	
	public Caminho(Distancia um, Distancia dois){
		this.setOrigemColeta(um);
		this.setColetaDestino(dois);
	}

	public Distancia getOrigemColeta() {
		return origemColeta;
	}

	public void setOrigemColeta(Distancia origemColeta) {
		this.origemColeta = origemColeta;
	}

	public Distancia getColetaDestino() {
		return coletaDestino;
	}

	public void setColetaDestino(Distancia coletaDestino) {
		this.coletaDestino = coletaDestino;
	}
}
