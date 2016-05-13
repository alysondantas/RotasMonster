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
