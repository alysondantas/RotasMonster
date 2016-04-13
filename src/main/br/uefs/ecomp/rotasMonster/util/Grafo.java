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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void inserirPonto(Object ponto) {
		pontos.inserirInicio(ponto);
	}
	
	@Override
	public Object recuperarPonto(Object ponto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Iterador iterador() {
		return pontos.iterador();
	}
	

}
