package br.uefs.ecomp.rotasMonster.util;

public interface IGrafo {
	
	public int obterTamanho();
	
	public Object removePonto(Object ponto);
	
	public void inserirPonto(Object ponto);
	
	public Object recuperarPonto(int index);
	
	public Iterador iterador();
}
