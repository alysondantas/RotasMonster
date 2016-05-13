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
package br.uefs.ecomp.rotasMonster.controller;

import br.uefs.ecomp.rotasMonster.util.*;
import br.uefs.ecomp.rotasMonster.exceptions.*;
import br.uefs.ecomp.rotasMonster.model.*;


public class AdministradorController {
	Grafo grafo = new Grafo();
	public AdministradorController(){
		
	}
	/**
	 * Metodo para retornar o grafo
	 * @return
	 */
	public Grafo getGrafo(){
		return grafo;
	}
	/**
	 * Metodo de cadastrar o Ponto
	 * @param p
	 * @return
	 * @throws PontoNuloException
	 * @throws CampoObrigatorioInexistenteException
	 * @throws ConflitoException
	 */
	public Ponto cadastrarPonto(Ponto p) throws PontoNuloException, CampoObrigatorioInexistenteException, ConflitoException{
		if(p==null){
			throw new PontoNuloException();
		}else if(p.getTipo() == 0 || p.getNome().trim().isEmpty()){
			throw new CampoObrigatorioInexistenteException();
		}else if(p.getCoordX() == -1 || p.getCoordY() == -1){
			throw new CampoObrigatorioInexistenteException();
		}
		Ponto aux = null;
		try {
			aux = recuperarPonto(p.getNome());
		} catch (PontoNaoEncontradoException e) {
			grafo.inserirPonto(p);
		}
		if(aux != null){
			throw new ConflitoException();
		}
		return p;
	}
	
	public MeuIterador listarPontos(){
		return (MeuIterador) grafo.iterador();
	}
	/**
	 * Metodo de recuperar o Ponto
	 * @param nomeP
	 * @return
	 * @throws PontoNaoEncontradoException
	 * @throws CampoObrigatorioInexistenteException
	 */
	public Ponto recuperarPonto(String nomeP) throws PontoNaoEncontradoException, CampoObrigatorioInexistenteException {
		Ponto ponto;
		MeuIterador iterador = (MeuIterador) grafo.iterador();
		if(nomeP == null || nomeP.trim().isEmpty()){
			throw new CampoObrigatorioInexistenteException();
		}
		while(iterador.temProximo()){
			ponto = (Ponto) iterador.obterProximo();
			if(ponto.getNome().equals(nomeP)){
				return ponto;
			}
		}
		
		throw new PontoNaoEncontradoException();
	}
	/**
	 * Metodo de alterar Ponto
	 * @param nomeP
	 * @param novonomeP
	 * @param tipo
	 * @throws PontoNaoEncontradoException
	 * @throws CampoObrigatorioInexistenteException
	 * @throws ConflitoException
	 */
	public void alterarPonto(String nomeP, String novonomeP, int tipo) throws PontoNaoEncontradoException, CampoObrigatorioInexistenteException, ConflitoException{
		MeuIterador iterador = (MeuIterador) grafo.iterador();
		Ponto ponto = null;
		Ponto aux = null;
		while(iterador.temProximo()){
			ponto = (Ponto) iterador.obterProximo();
			if(ponto.getNome().equals(nomeP)){
				break;
			}
		}
		if(ponto == null){
			throw new PontoNaoEncontradoException();
		}
		try {
			aux = recuperarPonto(novonomeP);
		} catch (PontoNaoEncontradoException e) {
			ponto.setNome(novonomeP);
			ponto.setTipo(tipo);
		}
		if(aux != null){
			throw new ConflitoException();
		}
		
	}
	/**
	 * Metodo de remover Produto
	 * @param nomeP
	 * @return
	 * @throws PontoNaoEncontradoException
	 * @throws CampoObrigatorioInexistenteException
	 * @throws ArestaNaoEncontradoException
	 * @throws PontoNuloException
	 */
	public Ponto removerPonto(String nomeP) throws PontoNaoEncontradoException, CampoObrigatorioInexistenteException, ArestaNaoEncontradoException, PontoNuloException{
		Ponto remover = recuperarPonto(nomeP);
		Ponto auxP;
		Aresta auxA;
		Lista arestas = remover.getArestas();
		MeuIterador iterador = (MeuIterador) arestas.iterador();
		while(iterador.temProximo()){
			auxA = (Aresta) iterador.obterProximo();
			auxP = auxA.getPontoDestino();
			removeAresta(remover, auxP);
		}
		
		remover = (Ponto) grafo.removePonto(remover);
		if(remover == null){
			throw new PontoNaoEncontradoException();
		}
		return remover;
	}
	/**
	 * Metodo para remover as arestas
	 * @param origem
	 * @param destino
	 * @return
	 * @throws ArestaNaoEncontradoException
	 * @throws PontoNuloException
	 */
	public Aresta removeAresta(Ponto origem, Ponto destino) throws ArestaNaoEncontradoException, PontoNuloException{
		if(origem == null || destino == null){
			throw new PontoNuloException();
		}
		MeuIterador iterador;
		int index = 0;
		Aresta arestaAux;
		Lista listOrg;
		Lista listDest;
		listOrg = origem.getArestas();
		iterador = (MeuIterador) listOrg.iterador();
		Aresta removida = null;
		listDest = destino.getArestas();
		
		if(!listOrg.estaVazia()){
			while(iterador.temProximo()){
				arestaAux = (Aresta) iterador.obterProximo();
				if(arestaAux.getPontoDestino().equals(destino)){
					removida = (Aresta) listOrg.remover(index);
					break;
				}
				index++;
			}
		}
		if(removida == null){
			throw new ArestaNaoEncontradoException();
		}
		removida = null;
		index = 0;
		iterador = (MeuIterador) listDest.iterador();
		if(!listDest.estaVazia()){
			while(iterador.temProximo()){
				arestaAux = (Aresta) iterador.obterProximo();
				if(arestaAux.getPontoDestino().equals(origem)){
					removida = (Aresta) listDest.remover(index);
					break;
				}
				index ++;
			}
		}
		if(removida == null){
			throw new ArestaNaoEncontradoException();
		}
		return removida;
	}
		
	/**
	 * Metodo para cadastrar uma aresta
	 * @param nomeOrigem
	 * @param tempo
	 * @param nomeDestino
	 * @return
	 * @throws CampoObrigatorioInexistenteException
	 * @throws PontoNaoEncontradoException
	 * @throws ConflitoException
	 */
	public Aresta cadastrarAresta(String nomeOrigem, double tempo, String nomeDestino) throws CampoObrigatorioInexistenteException, PontoNaoEncontradoException, ConflitoException{
		Ponto origem = null;
		Ponto destino = null;
		boolean pontoNaoEncontrado = true;
		MeuIterador iterador = (MeuIterador) grafo.iterador();
		
		if(nomeOrigem.trim().isEmpty() || nomeDestino.trim().isEmpty() || tempo<0.1){
			throw new CampoObrigatorioInexistenteException();
		}
		
		while(iterador.temProximo()){
			origem = (Ponto) iterador.obterProximo();
			if(origem.getNome().equals(nomeOrigem)){
				pontoNaoEncontrado = false;
				break;
			}
		}
		
		pontoNaoEncontrado = true;
		
		iterador.reiniciar();
		while(iterador.temProximo()){
			destino = (Ponto) iterador.obterProximo();
			if(destino.getNome().equals(nomeDestino)){
				pontoNaoEncontrado = false;
				break;
			}
		}
		
		if(pontoNaoEncontrado == true){
			throw new PontoNaoEncontradoException();
		}
		
		Aresta auxa;
		Ponto auxp;
		iterador.reiniciar();
		MeuIterador iteradorPonto;
		while(iterador.temProximo()){
			auxp = (Ponto) iterador.obterProximo();
			if(auxp.equals(origem)){
				iteradorPonto = (MeuIterador) auxp.getArestas().iterador();
				while(iteradorPonto.temProximo()){
					auxa = (Aresta) iteradorPonto.obterProximo();
					if(auxa.getPontoDestino().equals(destino)){
						throw new ConflitoException();
					}
				}
			}
			
		}
		
		
		Lista arestas = origem.getArestas();
		Aresta arestaO = new Aresta(destino, tempo);
		arestas.inserirInicio(arestaO);
		
		arestas = destino.getArestas();
		Aresta arestaD = new Aresta(origem, tempo);
		arestas.inserirInicio(arestaD);
		
		return arestaO;
	}
	/**
	 * Metodo para listar as arestas
	 * @param nome
	 * @return
	 * @throws PontoNaoEncontradoException
	 * @throws CampoObrigatorioInexistenteException
	 */
	public MeuIterador listarArestas(String nome) throws PontoNaoEncontradoException, CampoObrigatorioInexistenteException{
		Ponto p = recuperarPonto(nome);
		Lista arestas = p.getArestas();
		return (MeuIterador) arestas.iterador();
	}
	/**
	 * Metodo que realiza o Dijkstra
	 * @param sOrigem
	 * @param sColeta
	 * @param sDestino
	 * @return
	 * @throws PontoNaoEncontradoException
	 * @throws CampoObrigatorioInexistenteException
	 * @throws GrafoNuloException
	 * @throws DestinoNaoEncontradoException
	 */
	public Caminho realizarDijkstra(String sOrigem, String sColeta, String sDestino) throws PontoNaoEncontradoException, CampoObrigatorioInexistenteException, GrafoNuloException, DestinoNaoEncontradoException{
		Ponto origem = recuperarPonto(sOrigem);
		Ponto coleta = recuperarPonto(sColeta);
		Ponto destino = recuperarPonto(sDestino);
		
		Dijkstra djkCD = new Dijkstra(grafo);
		Dijkstra djkOC = new Dijkstra(grafo);
		
		Distancia caminhoOC = djkOC.iniciaDijkstra(origem, coleta);
		grafo.setPassouPontos();
		Distancia caminhoCD = djkCD.iniciaDijkstra(coleta, destino);
		
		Caminho caminho = new Caminho(caminhoOC, caminhoCD);
		
		return caminho;
	}
	
}
