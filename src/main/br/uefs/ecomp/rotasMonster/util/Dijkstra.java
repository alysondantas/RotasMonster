package br.uefs.ecomp.rotasMonster.util;

import br.uefs.ecomp.rotasMonster.exceptions.PontoNaoEncontradoException;
import br.uefs.ecomp.rotasMonster.model.Grafo;
import br.uefs.ecomp.rotasMonster.model.Ponto;

public class Dijkstra {
	private Grafo g;
	
	public Dijkstra(Grafo g){
		this.g = g;
	}
	
	public Lista iniciaDijkstra(Ponto origem, Ponto destino) throws PontoNaoEncontradoException{
		MeuIterador iterador = (MeuIterador) g.iterador();
		Ponto aux = null;
		while(iterador.temProximo()){
			aux = (Ponto) iterador.obterProximo();
			if(aux.equals(origem)){
				break;
			}
		}
		if(aux == null){
			throw new PontoNaoEncontradoException();
		} else{
			aux = null;
			iterador.reiniciar();
			while(iterador.temProximo()){
				aux = (Ponto) iterador.obterProximo();
				if(aux.equals(destino)){
					break;
				}
			}
			if(aux == null){
				throw new PontoNaoEncontradoException();
			}
		}
		
		return null;
	}
}
