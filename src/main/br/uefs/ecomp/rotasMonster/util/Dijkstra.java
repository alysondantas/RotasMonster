package br.uefs.ecomp.rotasMonster.util;

import br.uefs.ecomp.rotasMonster.exceptions.GrafoNuloException;
import br.uefs.ecomp.rotasMonster.exceptions.PontoNaoEncontradoException;
import br.uefs.ecomp.rotasMonster.model.Grafo;
import br.uefs.ecomp.rotasMonster.model.Ponto;
import br.uefs.ecomp.rotasMonster.model.Aresta;
import br.uefs.ecomp.rotasMonster.model.Distancia;

public class Dijkstra {
	private Grafo g;
	private int qtdPontos; //quantidade de pontos
	private FilaPrioridade fila;
	private Lista adj;
	
	public Dijkstra(Grafo g) throws GrafoNuloException{
		if(g == null){
			throw new GrafoNuloException();
		}
		this.g = g;
		qtdPontos = g.obterTamanho();
		fila = new FilaPrioridade();
	}
	
	public void setDijkstra(Grafo g){
		this.g= g;
	}
	
	public Lista iniciaDijkstra(Ponto origem, Ponto destino) throws PontoNaoEncontradoException{
		fila.inserir(0, origem);
		Ponto auxP;
		Aresta auxA;
		Ponto auxdestino = null;
		Lista arestas;
		double tamanho = 0;
		MeuIterador iterador = (MeuIterador) g.iterador();
		MeuIterador iterador2;
		Lista distancia = new Lista();
		//inserindo na fila
		while(iterador.temProximo()){
			auxP = (Ponto) iterador.obterProximo();
			if(auxP != origem){
				iterador2 = (MeuIterador) auxP.getArestas().iterador();
				while(iterador2.temProximo()){
					auxA = (Aresta) iterador.obterProximo();
					auxdestino = auxA.getPontoDestino();
					tamanho = auxA.gettempo();
					if(auxdestino.equals(destino)){
						break;
					}
				}
				if(auxdestino == null){
					Distancia d = new Distancia(auxP, 10000);
					distancia.inserirFinal(d);
					fila.inserir(10000, auxP);
				}else{
					Distancia d = new Distancia(auxP, tamanho);
					fila.inserir(tamanho, auxP);
					distancia.inserirFinal(d);
				}
			}
		}
		
		CelulaPrioridade celula;
		arestas = null;
		auxdestino = null;
		Distancia auxD = null;
		Ponto atualP = null;
		auxA = null;
		tamanho = 0;
		Lista caminho;
		double tamanhoU = 0;
		double tamanhoA = 0;
		/*
		 * auxdestinho = v
		 * auxP = u
		 * 
		 */
		while(!fila.estaVazia()){
			celula = (CelulaPrioridade) fila.recuperarInicio();
			auxP = (Ponto) celula.getObjeto();
			iterador = (MeuIterador) distancia.iterador();
			while(iterador.temProximo()){
				auxD = (Distancia) iterador.obterProximo();
				if(auxD.getP().equals(auxP)){
					tamanhoU = auxD.getTempo();
				}
			}
			if(auxP.isPassou() == false){
				auxP.setPassou(true);
				arestas = auxP.getArestas();
				iterador = (MeuIterador) arestas.iterador();
				while(iterador.temProximo()){
					auxA = (Aresta) iterador.obterProximo();
					auxdestino = auxA.getPontoDestino();
					tamanhoA = auxA.gettempo();
					iterador2 = (MeuIterador) distancia.iterador();
					while(iterador2.temProximo()){
						auxD = (Distancia) iterador2.obterProximo();
						atualP = auxD.getP();
						tamanho = auxD.getTempo();
						if(auxdestino.equals(atualP)){
							break;
						}
					}
					if(tamanho > tamanhoU + tamanhoA){
						auxD.setTempo(tamanhoU+tamanhoA);
						caminho = auxD.getPontos();
						caminho.inserirFinal(atualP);
						fila.inserir(tamanhoU+tamanhoA, atualP);
					}
				}
			}
		}
		return null;
	}
}
