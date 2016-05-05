package br.uefs.ecomp.rotasMonster.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import br.uefs.ecomp.rotasMonster.controller.AdministradorController;
import br.uefs.ecomp.rotasMonster.exceptions.CampoObrigatorioInexistenteException;
import br.uefs.ecomp.rotasMonster.exceptions.ConflitoException;
import br.uefs.ecomp.rotasMonster.exceptions.DestinoNaoEncontradoException;
import br.uefs.ecomp.rotasMonster.exceptions.GrafoNuloException;
import br.uefs.ecomp.rotasMonster.exceptions.PontoNaoEncontradoException;
import br.uefs.ecomp.rotasMonster.exceptions.PontoNuloException;
import br.uefs.ecomp.rotasMonster.util.Dijkstra;
import br.uefs.ecomp.rotasMonster.util.MeuIterador;

public class DijkstraTest {
	AdministradorController controller = new AdministradorController();
	
	@Test
	public void calcularDijkstra1(){
		Ponto a = new Ponto("A", 1, 13, 23);
		Ponto b = new Ponto("B", 1, 14, 23);
		Ponto c = new Ponto("C", 1, 13, 23);
		Ponto d = new Ponto("D", 1, 14, 23);
		Ponto e1 = new Ponto("E", 1, 14, 23);
		
		try{
			controller.cadastrarPonto(b);
			controller.cadastrarPonto(a);
			controller.cadastrarPonto(c);
			controller.cadastrarPonto(d);
			controller.cadastrarPonto(e1);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		int contador = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contador++;
		}
		
		assertEquals(5, contador);
		
		try {
			controller.cadastrarAresta("A", 1, "B");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("A", 3, "D");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("A", 10, "E");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("B", 5, "C");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("C", 2, "D");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("C", 1, "E");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("D", 6, "E");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		Ponto origem = null;
		Ponto destino = null;
		try {
			origem = controller.recuperarPonto("A");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		try {
			destino = controller.recuperarPonto("E");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		Grafo g = controller.getGrafo();
		Dijkstra djk = null;
		
		try {
			djk = new Dijkstra(g);
		} catch (GrafoNuloException e) {
			fail();
		}
		
		Distancia caminho = null;
		
		try {
			caminho = djk.iniciaDijkstra(origem, destino);
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (DestinoNaoEncontradoException e) {
			fail();
		}
		
		int tamanho = (int) caminho.getTempo();
		
		assertEquals(6, tamanho);
		
		assertEquals(6.0, caminho.getTempo(), 0);
		
	}
	
	@Test
	public void calcularDijkstra2(){
		Ponto a = new Ponto("A", 1, 13, 23);
		Ponto b = new Ponto("B", 1, 14, 23);
		Ponto c = new Ponto("C", 1, 13, 23);
		Ponto d = new Ponto("D", 1, 14, 23);
		Ponto e1 = new Ponto("E", 1, 14, 23);
		Ponto f = new Ponto("F", 1, 14, 23);
		
		try{
			controller.cadastrarPonto(b);
			controller.cadastrarPonto(a);
			controller.cadastrarPonto(c);
			controller.cadastrarPonto(d);
			controller.cadastrarPonto(e1);
			controller.cadastrarPonto(f);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		int contador = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contador++;
		}
		
		assertEquals(6, contador);
		
		try {
			controller.cadastrarAresta("A", 7, "B");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("A", 14, "D");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("A", 9, "C");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("B", 10, "C");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("B", 15, "E");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("C", 11, "E");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("D", 2, "C");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("D", 9, "F");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("E", 6, "F");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		Ponto origem = null;
		Ponto destino = null;
		try {
			origem = controller.recuperarPonto("A");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		try {
			destino = controller.recuperarPonto("F");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		Grafo g = controller.getGrafo();
		Dijkstra djk = null;
		
		try {
			djk = new Dijkstra(g);
		} catch (GrafoNuloException e) {
			fail();
		}
		
		Distancia caminho = null;
		
		try {
			caminho = djk.iniciaDijkstra(origem, destino);
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (DestinoNaoEncontradoException e) {
			fail();
		}
		
		int tamanho = (int) caminho.getTempo();
		
		assertEquals(20, tamanho);
		
		assertEquals(20.0, caminho.getTempo(), 0);
		
	}
	
	@Test
	public void calcularDijkstraAE(){
		Ponto a = new Ponto("A", 1, 13, 23);
		Ponto b = new Ponto("B", 1, 14, 23);
		Ponto c = new Ponto("C", 1, 13, 23);
		Ponto d = new Ponto("D", 1, 14, 23);
		Ponto e1 = new Ponto("E", 1, 14, 23);
		
		try{
			controller.cadastrarPonto(b);
			controller.cadastrarPonto(a);
			controller.cadastrarPonto(c);
			controller.cadastrarPonto(d);
			controller.cadastrarPonto(e1);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		int contador = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contador++;
		}
		
		assertEquals(5, contador);
		
		try {
			controller.cadastrarAresta("A", 1, "B");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("A", 3, "D");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("A", 5, "E");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("B", 5, "C");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("C", 2, "D");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("C", 1, "E");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("D", 6, "E");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		Ponto origem = null;
		Ponto destino = null;
		try {
			origem = controller.recuperarPonto("A");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		try {
			destino = controller.recuperarPonto("E");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		Grafo g = controller.getGrafo();
		Dijkstra djk = null;
		
		try {
			djk = new Dijkstra(g);
		} catch (GrafoNuloException e) {
			fail();
		}
		
		Distancia caminho = null;
		
		try {
			caminho = djk.iniciaDijkstra(origem, destino);
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (DestinoNaoEncontradoException e) {
			fail();
		}
		
		int tamanho = (int) caminho.getTempo();
		
		assertEquals(5, tamanho);
		
		assertEquals(5.0, caminho.getTempo(), 0);
		
	}
	
	@Test
	public void calcularDijkstra4(){
		Ponto a = new Ponto("A", 1, 13, 23);
		Ponto b = new Ponto("B", 1, 14, 23);
		Ponto c = new Ponto("C", 1, 13, 23);
		Ponto d = new Ponto("D", 1, 14, 23);
		Ponto e1 = new Ponto("E", 1, 14, 23);
		Ponto f = new Ponto("F", 1, 14, 23);
		
		try{
			controller.cadastrarPonto(b);
			controller.cadastrarPonto(a);
			controller.cadastrarPonto(c);
			controller.cadastrarPonto(d);
			controller.cadastrarPonto(e1);
			controller.cadastrarPonto(f);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		int contador = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contador++;
		}
		
		assertEquals(6, contador);
		
		try {
			controller.cadastrarAresta("A", 5, "B");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("B", 2, "C");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("B", 5, "D");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("C", 3, "E");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("D", 1, "F");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("E", 2, "F");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		
		Ponto origem = null;
		Ponto destino = null;
		try {
			origem = controller.recuperarPonto("A");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		try {
			destino = controller.recuperarPonto("F");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		Grafo g = controller.getGrafo();
		Dijkstra djk = null;
		
		try {
			djk = new Dijkstra(g);
		} catch (GrafoNuloException e) {
			fail();
		}
		
		Distancia caminho = null;
		
		try {
			caminho = djk.iniciaDijkstra(origem, destino);
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (DestinoNaoEncontradoException e) {
			fail();
		}
		
		int tamanho = (int) caminho.getTempo();
		
		assertEquals(11, tamanho);
		
		assertEquals(11.0, caminho.getTempo(), 0);
		
	}
	
	@Test
	public void calcularDijkstra5(){
		Ponto a = new Ponto("A", 1, 13, 23);
		Ponto b = new Ponto("B", 1, 14, 23);
		Ponto c = new Ponto("C", 1, 13, 23);
		Ponto d = new Ponto("D", 1, 14, 23);
		Ponto e1 = new Ponto("E", 1, 14, 23);
		Ponto f = new Ponto("F", 1, 14, 23);
		
		try{
			controller.cadastrarPonto(b);
			controller.cadastrarPonto(a);
			controller.cadastrarPonto(c);
			controller.cadastrarPonto(d);
			controller.cadastrarPonto(e1);
			controller.cadastrarPonto(f);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		int contador = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contador++;
		}
		
		assertEquals(6, contador);
		
		try {
			controller.cadastrarAresta("A", 5, "B");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("B", 2, "C");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("B", 5, "D");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("C", 3, "E");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("D", 2, "F");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("E", 2, "F");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		
		Ponto origem = null;
		Ponto destino = null;
		try {
			origem = controller.recuperarPonto("A");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		try {
			destino = controller.recuperarPonto("F");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		Grafo g = controller.getGrafo();
		Dijkstra djk = null;
		
		try {
			djk = new Dijkstra(g);
		} catch (GrafoNuloException e) {
			fail();
		}
		
		Distancia caminho = null;
		
		try {
			caminho = djk.iniciaDijkstra(origem, destino);
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (DestinoNaoEncontradoException e) {
			fail();
		}
		
		int tamanho = (int) caminho.getTempo();
		
		assertEquals(12, tamanho);
		
		assertEquals(12.0, caminho.getTempo(), 0);
		
	}
	
	@Test
	public void calcularDijkstraOrigemColetaDestino(){
		Ponto a = new Ponto("A", 1, 13, 23);
		Ponto b = new Ponto("B", 1, 14, 23);
		Ponto c = new Ponto("C", 1, 13, 23);
		Ponto d = new Ponto("D", 1, 14, 23);
		Ponto e1 = new Ponto("E", 1, 14, 23);
		
		try{
			controller.cadastrarPonto(b);
			controller.cadastrarPonto(a);
			controller.cadastrarPonto(c);
			controller.cadastrarPonto(d);
			controller.cadastrarPonto(e1);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		int contador = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contador++;
		}
		
		assertEquals(5, contador);
		
		try {
			controller.cadastrarAresta("A", 1, "B");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("A", 3, "D");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("A", 10, "E");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("B", 5, "C");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("C", 2, "D");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("C", 1, "E");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("D", 6, "E");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		Caminho caminho = null;
		
		try {
			caminho = controller.realizarDijkstra("A", "C", "E");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (GrafoNuloException e) {
			fail(); 
		} catch (DestinoNaoEncontradoException e) {
			fail();
		}
		
		Distancia distanciaAC = caminho.getOrigemColeta();
		Distancia distanciaCE = caminho.getColetaDestino();

		
//		int tamanho = (int) caminho.getTempo();
		
//		assertEquals(6, tamanho);
		
//		assertEquals(6.0, caminho.getTempo(), 0);
		
	}
	
	@Test
	public void calcularDijkstraSemCaminhoDestino(){
		Ponto a = new Ponto("A", 1, 13, 23);
		Ponto b = new Ponto("B", 1, 14, 23);
		Ponto c = new Ponto("C", 1, 13, 23);
		Ponto d = new Ponto("D", 1, 14, 23);
		Ponto e1 = new Ponto("E", 1, 14, 23);
		
		try{
			controller.cadastrarPonto(b);
			controller.cadastrarPonto(a);
			controller.cadastrarPonto(c);
			controller.cadastrarPonto(d);
			controller.cadastrarPonto(e1);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		int contador = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contador++;
		}
		
		assertEquals(5, contador);
		
		try {
			controller.cadastrarAresta("A", 1, "B");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("A", 3, "D");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("A", 10, "E");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("B", 5, "C");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("C", 2, "D");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		
		
		Ponto origem = null;
		Ponto destino = null;
		try {
			origem = controller.recuperarPonto("A");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		try {
			destino = controller.recuperarPonto("E");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		Grafo g = controller.getGrafo();
		Dijkstra djk = null;
		
		try {
			djk = new Dijkstra(g);
		} catch (GrafoNuloException e) {
			fail();
		}
		
		Distancia caminho = null;
		
		try {
			caminho = djk.iniciaDijkstra(origem, destino);
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (DestinoNaoEncontradoException e) {
			assertTrue(true);
		}
		
		int tamanho = (int) caminho.getTempo();
		
		assertEquals(10, tamanho);
		
		assertEquals(10.0, caminho.getTempo(), 0);
		
	}
}
