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

public class Caminho {
	private Distancia origemColeta; //distancia da origem para a coleta
	private Distancia coletaDestino; //distancia da coleta para o destino
	/**
	 * Construtor da classe
	 * @param um
	 * @param dois
	 */
	public Caminho(Distancia um, Distancia dois){
		this.setOrigemColeta(um);
		this.setColetaDestino(dois);
	}
	/**
	 * 
	 * @return distancia da origem para a coleta
	 */
	public Distancia getOrigemColeta() {
		return origemColeta;
	}
	/**
	 * Modifica a distancia da origem para a coleta
	 * @param origemColeta
	 */
	public void setOrigemColeta(Distancia origemColeta) {
		this.origemColeta = origemColeta;
	}
	/**
	 * 
	 * @return distancia da coleta para destino
	 */
	public Distancia getColetaDestino() {
		return coletaDestino;
	}
	/**
	 * Modifica distancia da coleta para destino
	 * @param coletaDestino
	 */
	public void setColetaDestino(Distancia coletaDestino) {
		this.coletaDestino = coletaDestino;
	}
}
