package br.uefs.ecomp.rotasMonster.util;

public class Ponto {
	private String nome;
	private int tipo;
	private Lista aresta;
	
	public Ponto(String nome, int tipo){
		this.nome = nome;
		this.tipo = tipo;
		aresta = new Lista();
	}
	
	/**
	 * @return the aresta
	 */
	public Lista getAresta() {
		return aresta;
	}
	/**
	 * @param aresta the aresta to set
	 */
	public void setAresta(Lista aresta) {
		this.aresta = aresta;
	}
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return the tipo
	 */
	public int getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
