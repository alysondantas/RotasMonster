package br.uefs.ecomp.rotasMonster.controller;

import br.uefs.ecomp.rotasMonster.util.*;
import br.uefs.ecomp.rotasMonster.exceptions.*;

public class AdministradorController {
	Grafo grafo = new Grafo();
	public AdministradorController(){
		
	}
	
	public Ponto cadastrarPonto(Ponto p) throws PontoNuloException, CampoObrigatorioInexistenteException{
		if(p==null){
			throw new PontoNuloException();
		}else if(p.getTipo() == 0 || p.getNome().trim().isEmpty()){
			throw new CampoObrigatorioInexistenteException();
		}
		grafo.inserirPonto(p);
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
	
	public Aresta cadastrarAresta(String nomeOrigem, int distancia, String nomeDestino) throws CampoObrigatorioInexistenteException, PontoNaoEncontradoException{
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
		
		Lista arestas = origem.getArestas();
		Aresta aresta = new Aresta(origem, destino, distancia);
		arestas.inserirInicio(aresta);
		
		return aresta;
	}
}
