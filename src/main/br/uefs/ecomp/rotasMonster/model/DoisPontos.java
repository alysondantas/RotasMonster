package br.uefs.ecomp.rotasMonster.model;

public class DoisPontos {
	private Ponto pontoA; // ponto A, o que esta antes
	private Ponto pontoB; // ponto B, o que esta depois
	private boolean este = false; // bolean para saber se é este o ponto atual
	/**
	 * Construtor da classe
	 * @param a
	 * @param b
	 */
	public DoisPontos(Ponto a, Ponto b){
		this.setPontoA(a);
		this.setPontoB(b);
	}
	/**
	 * 
	 * @return o ponto A
	 */
	public Ponto getPontoA() {
		return pontoA;
	}
	/**
	 * Modifica o ponto A
	 * @param pontoA
	 */
	public void setPontoA(Ponto pontoA) {
		this.pontoA = pontoA;
	}
	/**
	 * 
	 * @return o ponto B
	 */
	public Ponto getPontoB() {
		return pontoB;
	}
	/**
	 * Modifica o ponto B
	 * @param pontoB
	 */
	public void setPontoB(Ponto pontoB) {
		this.pontoB = pontoB;
	}
	/**
	 * 
	 * @return boolean
	 */
	public boolean isEste() {
		return este;
	}
	/**
	 * Modifica o boolean
	 * @param este
	 */
	public void setEste(boolean este) {
		this.este = este;
	}
	
}
