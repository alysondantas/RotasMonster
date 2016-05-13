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
package br.uefs.ecomp.rotasMonster.util;

import br.uefs.ecomp.rotasMonster.exceptions.DestinoNaoEncontradoException;
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
	
	/**
	 * Contstutor da classe
	 * @param g
	 * @throws GrafoNuloException
	 */
	public Dijkstra(Grafo g) throws GrafoNuloException{
		if(g == null){
			throw new GrafoNuloException();
		}
		this.g = g;
		fila = new FilaPrioridade();
		caminhoPercorrido = new Lista();
	}
	
	/**
	 * Metodo para reiniciar o objeto para outro grafo
	 * @param g
	 * @throws GrafoNuloException
	 */
	public void resetaDijkstra(Grafo g) throws GrafoNuloException{
		if(g == null){
			throw new GrafoNuloException();
		}
		this.g = g;
		fila = new FilaPrioridade();
		caminhoPercorrido = new Lista();
	}
	
	/**
	 * Metodo de Dijkstra
	 * @param origem
	 * @param destino
	 * @return
	 * @throws PontoNaoEncontradoException
	 * @throws DestinoNaoEncontradoException
	 */
	public Distancia iniciaDijkstra(Ponto origem, Ponto destino) throws PontoNaoEncontradoException, DestinoNaoEncontradoException{
		g.setPassouPontos();//reinicia o boolean de visita que esta em todos os pontos do grafo
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
		//inserindo na fila e na lista todos os pontos que estão no grafo
		while(iterador.temProximo()){
			auxP = (Ponto) iterador.obterProximo();
			if(auxP != origem){
					Distancia d = new Distancia(auxP, 10000);//coloco a distancia como 10000 considero infinito
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
		//enquanto existe elemento na fila
		while(!fila.estaVazia()){
			celula = (CelulaPrioridade) fila.removerInicio();//removo um elemento da fila
			auxP = (Ponto) celula.getObjeto();//pego o ponto dele elemento
			iterador = (MeuIterador) distancia.iterador();
			while(iterador.temProximo()){//procuro por ele na lista de distancias ja existente
				auxD = (Distancia) iterador.obterProximo();
				if(auxD.getP().equals(auxP)){
					tamanhoU = auxD.getTempo();//pego o tempo da origem ate ele
				}
			}
			if(auxP.isPassou() == false){//se não visitou o ponto ainda
				auxP.setPassou(true);
				arestas = auxP.getArestas();
				iterador = (MeuIterador) arestas.iterador();
				while(iterador.temProximo()){//procuro por adjacentes dele
					auxA = (Aresta) iterador.obterProximo();
					auxdestino = auxA.getPontoDestino();
					tamanhoA = auxA.gettempo();
					iterador2 = (MeuIterador) distancia.iterador();
					while(iterador2.temProximo()){//procuro na lista de distancia pelo 
						auxD = (Distancia) iterador2.obterProximo();//procuro por cada adjacente na lista de distancia
						atualP = auxD.getP();
						tamanho = auxD.getTempo();
						if(auxdestino.equals(atualP)){//quando encontro ele e destino na lista paro a pesquisa
							break;
						}
					}
					if(tamanho > tamanhoU + tamanhoA){//se o tamnho da aresta que ja esta na lista for maior que a soma da aresta atual mais o caminho ate esse b
						auxD.setTempo(tamanhoU+tamanhoA);//guardo essa soma na variavel que esta na lista
						caminho = auxD.getPontos();
						caminho.inserirInicio(atualP);
//						System.out.println(auxP.getNome()+ " " + auxdestino.getNome() );
						fila.inserir(tamanhoU+tamanhoA, atualP);//insiro novamente na fila essa variavel
						DoisPontos dois = new DoisPontos(auxP, auxdestino);
						caminhoPercorrido.inserirInicio(dois);//insiro os dois pontos envolvidos no processo em uma lista com todos os caminhos que foram percorridos...
					}
				}
			}
		}
		
//		System.out.println();
		DoisPontos auxDoisPontos;
		Ponto a = null;
		Lista trajeto = new Lista();
		iterador2 = (MeuIterador) caminhoPercorrido.iterador();
		while(iterador2.temProximo()){//percorro o caminho percorrido pelo descrava que vai estar de traz para frente
			auxDoisPontos = (DoisPontos) iterador2.obterProximo();
			if(trajeto.obterTamanho() == 0 && auxDoisPontos.getPontoB().equals(destino)){ // se a lista do trajeto foz vazia e se o ponto B for o destino pode inserir
				trajeto.inserirInicio(auxDoisPontos);//insere os dois pontos na lista
				a = auxDoisPontos.getPontoA();//a pega o ponto A
			}else if(auxDoisPontos.getPontoB().equals(a)){ // se não se o ponto A (Guardado) for igual ao B do atual
				a = auxDoisPontos.getPontoA();
				trajeto.inserirInicio(auxDoisPontos);//insere os dois pontos na lista
			}
//			System.out.println(celulinha.getPontoA().getNome() + " " + celulinha.getPontoB().getNome());
		}
		
		if(trajeto.obterTamanho()<1){//se não tiver elementos na lista lança exceção
			throw new DestinoNaoEncontradoException();
		}
		
		auxDoisPontos = null;
//		System.out.println();
		iterador2 = (MeuIterador) trajeto.iterador();
		while(iterador2.temProximo()){
			auxDoisPontos = (DoisPontos) iterador2.obterProximo();
//			System.out.println(celulinha.getPontoA().getNome() + " " + celulinha.getPontoB().getNome());
		}
//		System.out.println("Destino encontrado é: " + celulinha.getPontoB().getNome());
//		System.out.println("Destino é: " + destino.getNome());
		iterador = (MeuIterador) distancia.iterador();
		tamanho = -1;
		while(iterador.temProximo()){//procuro na lista de distancia a distancia ate o destino
			auxD = (Distancia) iterador.obterProximo();
			if(auxD.getP().equals(destino)){//se encontrar
				tamanho = auxD.getTempo();//tamanh0 recebe a distancia
				auxD.setPontos(trajeto);//e o trajeto fica dentro do objeto distancia
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
