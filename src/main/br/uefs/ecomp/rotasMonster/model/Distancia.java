package br.uefs.ecomp.rotasMonster.model;

import br.uefs.ecomp.rotasMonster.util.Lista;

public class Distancia {
	private Ponto p;
	private double tempo;
	private Lista pontos;
	
	public Distancia(Ponto p, double temp){
		this.p = p;
		this.tempo = temp;
		setPontos(new Lista());
	}
	
	public Ponto getP() {
		return p;
	}
	public void setP(Ponto p) {
		this.p = p;
	}
	public double getTempo() {
		return tempo;
	}
	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

	public Lista getPontos() {
		return pontos;
	}

	public void setPontos(Lista pontos) {
		this.pontos = pontos;
	}
}
