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

public class Aresta {
	private Ponto pontoDestino; //ponto para o destino
	private double tempo = 0; // tempo ate o destino
	/**
	 * Construtor da classe
	 * @param pontoDest
	 * @param tempo
	 */
	public Aresta(Ponto pontoDest, double tempo){
		this.pontoDestino = pontoDest;
		this.tempo = tempo;
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
	 * @return the tempo
	 */
	public double gettempo() {
		return tempo;
	}
	/**
	 * @param tempo the tempo to set
	 */
	public void settempo(double tempo) {
		this.tempo = tempo;
	}
}
