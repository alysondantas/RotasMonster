package br.uefs.ecomp.rotasMonster.util;

import br.uefs.ecomp.rotasMonster.exceptions.GrafoNuloException;
import br.uefs.ecomp.rotasMonster.exceptions.PontoNaoEncontradoException;
import br.uefs.ecomp.rotasMonster.model.Grafo;
import br.uefs.ecomp.rotasMonster.model.Ponto;
import br.uefs.ecomp.rotasMonster.model.Aresta;
import br.uefs.ecomp.rotasMonster.model.Distancia;
import br.uefs.ecomp.rotasMonster.model.DoisPontos;

public class Dijkstra {
	private Grafo g;
	private FilaPrioridade fila;
	private Lista caminhoPercorrido;
	
	public Dijkstra(Grafo g) throws GrafoNuloException{
		if(g == null){
			throw new GrafoNuloException();
		}
		this.g = g;
		fila = new FilaPrioridade();
		caminhoPercorrido = new Lista();
	}
	
	public void resetaDijkstra(Grafo g) throws GrafoNuloException{
		if(g == null){
			throw new GrafoNuloException();
		}
		this.g = g;
		fila = new FilaPrioridade();
		caminhoPercorrido = new Lista();
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
						System.out.println(auxP.getNome()+ " " + auxdestino.getNome() );
						fila.inserir(tamanhoU+tamanhoA, atualP);
						
						DoisPontos dois = new DoisPontos(auxP, auxdestino);
						caminhoPercorrido.inserirInicio(dois);
					}
				}
			}
		}
		
		//vc não faz ideia do que é mangue!
		System.out.println();
		DoisPontos celulinha;
		Ponto a = null;
		Lista test = new Lista();
		iterador2 = (MeuIterador) caminhoPercorrido.iterador();
		while(iterador2.temProximo()){
			celulinha = (DoisPontos) iterador2.obterProximo();
			if(test.obterTamanho() == 0){
				test.inserirInicio(celulinha);
				a = celulinha.getPontoA();
			}else if(celulinha.getPontoB().equals(a)){
				a = celulinha.getPontoA();
				test.inserirInicio(celulinha);
			}
			System.out.println(celulinha.getPontoA().getNome() + " " + celulinha.getPontoB().getNome());
		}
		
		celulinha = null;
		

		System.out.println();
		DoisPontos antescelulinha = null;
		iterador2 = (MeuIterador) test.iterador();
		while(iterador2.temProximo()){
			antescelulinha = celulinha;
			celulinha = (DoisPontos) iterador2.obterProximo();
			System.out.println(celulinha.getPontoA().getNome() + " " + celulinha.getPontoB().getNome());
		}
		System.out.println("Destino encontrado é: " + celulinha.getPontoB().getNome());
		System.out.println("Antes dele era:" + antescelulinha.getPontoB().getNome());
		System.out.println("Destino é :" + destino.getNome());
			if(antescelulinha.getPontoB().equals(destino)){
				System.out.println("Removido o destino invalido");
				test.removerFinal();
			}else if(!celulinha.getPontoB().equals(destino)){//se o ultimo ponto não for o destino é pq o menor caminho é direto da origem para o destino
				System.out.println("Mas ela não é o destino");
				test = new Lista();
				celulinha = new DoisPontos(origem, destino);
				test.inserirInicio(celulinha);
				System.out.println(celulinha.getPontoA().getNome() + " " + celulinha.getPontoB().getNome());
			}
			
			
		iterador = (MeuIterador) distancia.iterador();
		tamanho = -1;
		while(iterador.temProximo()){
			auxD = (Distancia) iterador.obterProximo();
			if(auxD.getP().equals(destino)){
				tamanho = auxD.getTempo();
//				caminho = auxD.getPontos();
				auxD.setPontos(test);
				break;
			}
		}
		if(tamanho == -1){
			return null;
		}else{
			return auxD;
		}

	}
}
