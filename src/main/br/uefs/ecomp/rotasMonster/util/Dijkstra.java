package br.uefs.ecomp.rotasMonster.util;

import br.uefs.ecomp.rotasMonster.exceptions.GrafoNuloException;
import br.uefs.ecomp.rotasMonster.exceptions.PontoNaoEncontradoException;
import br.uefs.ecomp.rotasMonster.model.Grafo;
import br.uefs.ecomp.rotasMonster.model.Ponto;
import br.uefs.ecomp.rotasMonster.model.Aresta;

public class Dijkstra {
	private Grafo g;
	private int qtdPontos; //quantidade de pontos
	private FilaPrioridade distancia;
	
	public Dijkstra(Grafo g) throws GrafoNuloException{
		if(g == null){
			throw new GrafoNuloException();
		}
		this.g = g;
		qtdPontos = g.obterTamanho();
		distancia = new FilaPrioridade();
	}
	
	public void setDijkstra(Grafo g){
		this.g= g;
	}
	
	public Lista iniciaDijkstra(Ponto origem, Ponto destino) throws PontoNaoEncontradoException{
		distancia.inserir(0, origem);
		Ponto auxP;
		MeuIterador iterador = (MeuIterador) g.iterador();
		while(iterador.temProximo()){
			auxP = (Ponto) iterador.obterProximo();
			
		}
		CelulaPrioridade celula;
		
		Lista arestas;
		Ponto auxdestino;
		Aresta auxA;
		double tamanho;
		while(!distancia.estaVazia()){
			celula = distancia.getPrimeiro();
			auxP = (Ponto) celula.getObjeto();
			if(auxP.isPassou() == false){
				auxP.setPassou(true);
				arestas = auxP.getArestas();
				iterador = (MeuIterador) arestas.iterador();
				while(iterador.temProximo()){
					auxA = (Aresta) iterador.obterProximo();
					auxdestino = auxA.getPontoDestino();
					tamanho = auxA.gettempo();
					
				}
			}
		}
		return null;
	}
}
