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
	
	public Distancia iniciaDijkstra(Ponto origem, Ponto destino) throws PontoNaoEncontradoException{
		Ponto auxP;
		Aresta auxA;
		Ponto auxdestino = null;
		Lista arestas;
		double tamanho = 0;
		MeuIterador iterador = (MeuIterador) g.iterador();
		MeuIterador iterador2;
		Lista distancia = new Lista();
		Distancia ori = new Distancia(origem, 0);
		distancia.inserirInicio(ori);
		fila.inserir(0, origem);
		//inserindo na fila
		while(iterador.temProximo()){
			auxP = (Ponto) iterador.obterProximo();
			if(auxP != origem){
					Distancia d = new Distancia(auxP, 10000);
					distancia.inserirInicio(d);
					fila.inserir(10000, auxP);
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
			celula = (CelulaPrioridade) fila.removerInicio();
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
						caminho.inserirInicio(atualP);
						fila.inserir(tamanhoU+tamanhoA, atualP);
					}
				}
			}
		}
		iterador = (MeuIterador) distancia.iterador();
		
		tamanho = -1;
		caminho = null;
		
		while(iterador.temProximo()){
			auxD = (Distancia) iterador.obterProximo();
			if(auxD.getP().equals(destino)){
				tamanho = auxD.getTempo();
				caminho = auxD.getPontos();
				break;
			}
		}
		if(tamanho == -1 || caminho == null){
			return null;
		}else{
			return auxD;
		}

	}
}
