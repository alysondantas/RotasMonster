package br.uefs.ecomp.rotasMonster.util;

public class Grafo implements IGrafo {
	Lista pontos;
	public Grafo(){
		pontos = new Lista();
	}
	
	@Override
	public int obterTamanho() {
		return this.pontos.obterTamanho();
	}
	
	@Override
	public Object removePonto(Object ponto) {
		if(pontos.estaVazia()){
			return null;
		}
		Ponto auxP = null;
		ponto = (Ponto) ponto;
		MeuIterador iterador = (MeuIterador) pontos.iterador();
		int index = 0;
		while(iterador.temProximo()){
			auxP = (Ponto) iterador.obterProximo();
			if(auxP.equals(ponto)){
				pontos.remover(index);
			}
			index++;
		}
		return auxP;
	}
	
	@Override
	public void inserirPonto(Object ponto) {
		pontos.inserirInicio(ponto);
	}
	
	@Override
	public Iterador iterador() {
		return pontos.iterador();
	}
	

}
