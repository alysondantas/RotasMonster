/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas e Bruno Menezes de Lima

Componente Curricular: MI - Algoritmos II

Concluido em: 03/05/2016

Declaro que este código foi elaborado por esta dupla e não contém nenhum

trecho de código de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código

de outra autoria que não a minha está destacado com uma citação para o autor e a fonte

do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

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
