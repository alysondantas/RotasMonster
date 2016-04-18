package br.uefs.ecomp.rotasMonster.util;

public class Ponto {
	private String nome;
	private int tipo = 0;
	private Lista arestas;
	private int coordX = -1;
	private int coordY = -1;
	
	public Ponto(String nome, int tipo, int x, int y){
		this.nome = nome;
		this.tipo = tipo;
		this.coordX = x;
		this.coordY = y;
		arestas = new Lista();
	}
	
	/*
	 * tipo = 1 / garagem
	 * tipo = 2 / ponto normal
	 * tipo = 3 /destino
	 */
	
	/**
	 * @return the aresta
	 */
	public Lista getArestas() {
		return arestas;
	}
	/**
	 * @param aresta the arestsa to set
	 */
	public void setArestas(Lista arestas) {
		this.arestas = arestas;
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

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
}
