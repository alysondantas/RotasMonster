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

public class DoisPontos {
	private Ponto pontoA; // ponto A, o que esta antes
	private Ponto pontoB; // ponto B, o que esta depois
	private boolean este = false; // bolean para saber se � este o ponto atual
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
