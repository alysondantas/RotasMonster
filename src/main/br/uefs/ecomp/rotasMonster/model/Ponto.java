/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas e Bruno Menezes de Lima

Componente Curricular: MI - Algoritmos II

Concluido em: 03/05/2016

Declaro que este c�digo foi elaborado por esta dupla e n�o cont�m nenhum

trecho de c�digo de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e p�ginas ou documentos eletr�nicos da Internet. Qualquer trecho de c�digo

de outra autoria que n�o a minha est� destacado com uma cita��o para o autor e a fonte

do c�digo, e estou ciente que estes trechos n�o ser�o considerados para fins de avalia��o.

 ******************************************************************************************/
package br.uefs.ecomp.rotasMonster.model;

import br.uefs.ecomp.rotasMonster.util.Lista;

public class Ponto{
	private String nome; // Nome do ponto
	private int tipo = 0; // tipo do ponto
	private Lista arestas; //Lista de arestas (lista de adjacencias)
	private int coordX = -1; // coordenada X no grafico
	private int coordY = -1; // coordenada Y no grafico
	private boolean passou = false; // boolean se passou ou n�o
	/**
	 * Contrutor da classe
	 * @param nome
	 * @param tipo
	 * @param x
	 * @param y
	 */
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
	/**
	 * 
	 * @return a coordenada X
	 */
	public int getCoordX() {
		return coordX;
	}
	/**
	 * Seta a coordenada X
	 * @param coordX
	 */
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	/**
	 * 
	 * @return a coordena Y
	 */
	public int getCoordY() {
		return coordY;
	}
	/**
	 * Seta a coordenada Y
	 * @param coordY
	 */
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	/**
	 * 
	 * @return o boolean
	 */
	public boolean isPassou() {
		return passou;
	}
	/**
	 * Seta o boolean
	 * @param passou
	 */
	public void setPassou(boolean passou) {
		this.passou = passou;
	}
}
