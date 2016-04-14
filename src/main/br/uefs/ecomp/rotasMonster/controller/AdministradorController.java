package br.uefs.ecomp.rotasMonster.controller;

import br.uefs.ecomp.rotasMonster.util.*;
import br.uefs.ecomp.rotasMonster.exceptions.*;

public class AdministradorController {
	Grafo grafo = new Grafo();
	public AdministradorController(){
		
	}
	
	public Ponto cadastrarPonto(Ponto p) throws PontoNuloException, CampoObrigatorioInexistenteException, ConflitoException{
		if(p==null){
			throw new PontoNuloException();
		}else if(p.getTipo() == 0 || p.getNome().trim().isEmpty()){
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
	
	public void alterarPonto(String nomeP, String novonomeP, int tipo) throws PontoNaoEncontradoException{
		MeuIterador iterador = (MeuIterador) grafo.iterador();
		Ponto ponto = null;
		
		while(iterador.temProximo()){
			ponto = (Ponto) iterador.obterProximo();
			if(ponto.getNome().equals(nomeP)){
				break;
			}
		}
		if(ponto == null){
			throw new PontoNaoEncontradoException();
		}
		ponto.setNome(novonomeP);
		ponto.setTipo(tipo);
	}
	
	public Ponto removerPonto(){
		return null;
	}
	
	public Aresta cadastrarAresta(String nomeOrigem, int distancia, String nomeDestino) throws CampoObrigatorioInexistenteException, PontoNaoEncontradoException, ConflitoException{
		Ponto origem = null;
		Ponto destino = null;
		MeuIterador iterador = (MeuIterador) grafo.iterador();
		
		if(nomeOrigem.trim().isEmpty() || nomeDestino.trim().isEmpty() || distancia<1){
			throw new CampoObrigatorioInexistenteException();
		}
		while(iterador.temProximo()){
			origem = (Ponto) iterador.obterProximo();
			if(origem.getNome().equals(nomeOrigem)){
				break;
			}
		}
		iterador.reiniciar();
		while(iterador.temProximo()){
			destino = (Ponto) iterador.obterProximo();
			if(destino.getNome().equals(nomeDestino)){
				break;
			}
		}
		if(origem == null || destino == null){
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
		Aresta arestaO = new Aresta(destino, distancia);
		arestas.inserirInicio(arestaO);
		
		arestas = destino.getArestas();
		Aresta arestaD = new Aresta(origem, distancia);
		arestas.inserirInicio(arestaD);
		
		return arestaO;
	}
	
	public void alterarAresta(String nomeOrigem, String nomeDestino, int distancia){
		MeuIterador iteradorgrafo;
		MeuIterador iteradorponto;
		
	}
}
