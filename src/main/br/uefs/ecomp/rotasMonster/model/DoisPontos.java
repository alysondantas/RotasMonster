package br.uefs.ecomp.rotasMonster.model;

public class DoisPontos {
	private Ponto pontoA;
	private Ponto pontoB;
	private boolean este = false;
	
	public DoisPontos(Ponto a, Ponto b){
		this.setPontoA(a);
		this.setPontoB(b);
	}

	public Ponto getPontoA() {
		return pontoA;
	}

	public void setPontoA(Ponto pontoA) {
		this.pontoA = pontoA;
	}

	public Ponto getPontoB() {
		return pontoB;
	}

	public void setPontoB(Ponto pontoB) {
		this.pontoB = pontoB;
	}

	public boolean isEste() {
		return este;
	}

	public void setEste(boolean este) {
		this.este = este;
	}
	
}
