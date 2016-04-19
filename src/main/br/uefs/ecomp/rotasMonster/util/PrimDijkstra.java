package br.uefs.ecomp.rotasMonster.util;

import br.uefs.ecomp.rotasMonster.exceptions.ArestaNaoEncontradoException;
import br.uefs.ecomp.rotasMonster.model.Aresta;
import br.uefs.ecomp.rotasMonster.model.Grafo;
import br.uefs.ecomp.rotasMonster.model.Ponto;

public class PrimDijkstra {
	private Grafo grafo;
	private Ponto inicio;
	private Ponto destino;
	
	public PrimDijkstra(Grafo grafo, Ponto inicio, Ponto destino){
		this.grafo = grafo;
		this.inicio = inicio;
		this.destino = destino;
		
	}
	
	public void rodarPrim() throws ArestaNaoEncontradoException{
		Lista listaArestas = inicio.getArestas();
		if(listaArestas == null){
			throw new ArestaNaoEncontradoException();
		}
		int tamanho = grafo.obterTamanho();
		Ponto aux = inicio;
		Ponto auxD = null;
		int contador = 0;
		String caminho = ": ";
		MeuIterador iterador = null;
		Aresta aresta = null;
		Aresta arestaaux = null;
		Aresta arestaaux2 = null;
		int contaux = 0;
		boolean japassou = true;
		
		while(contador<tamanho){
			listaArestas = aux.getArestas();
			iterador = (MeuIterador) listaArestas.iterador();
			while(iterador.temProximo()){
				aresta = (Aresta) iterador.obterProximo();
					if(arestaaux == null){
						arestaaux = aresta;
					}else{
						if(aresta.gettempo() < arestaaux.gettempo()){
							auxD = aresta.getPontoDestino();
							if(auxD.isPassou() == true){
								contaux++;
								arestaaux2 = aresta;
							}else{
								arestaaux = aresta;
							}
						}
					}
			}
			if(contaux == listaArestas.obterTamanho() || arestaaux == null){
				arestaaux = arestaaux2;
			}
			auxD = arestaaux.getPontoDestino();
			if(aux.isPassou() == false){
				aux.setPassou(true);
				contador++;
			}
			caminho = caminho + aux.getNome() + "-" + auxD.getNome() + "/";
			System.out.println(caminho);
			aux = auxD;
			arestaaux = null;
			arestaaux2 = null;
			contaux = 0;
		}
		System.out.println("FIM");
		System.out.println(caminho);
	}
	
	
	public void dijkstra(){
		
	}
}
