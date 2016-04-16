package br.uefs.ecomp.rotasMonster.Model;

import static org.junit.Assert.*;

import org.junit.Test;

import br.uefs.ecomp.rotasMonster.controller.*;
import br.uefs.ecomp.rotasMonster.exceptions.*;
import br.uefs.ecomp.rotasMonster.util.*;

public class PontosTest {
	AdministradorController controller = new AdministradorController();
	
	@Test
	public void cadastrarPontoSucesso(){
		Ponto a = new Ponto("A", 1);
		Ponto b = new Ponto("B", 1);
		Ponto a1 = null;
		Ponto b1 = null;
		
		try{
			b1 = controller.cadastrarPonto(b);
			a1 = controller.cadastrarPonto(a);
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
		
		assertEquals(2, contador);
		assertEquals(a, a1);
		assertEquals(b, b1);
	}
	
	@Test
	public void cadastrarPontoIgual(){
		Ponto a = new Ponto("A", 1);
		Ponto b = new Ponto("A", 1);
		
		try{
			controller.cadastrarPonto(b);
			controller.cadastrarPonto(a);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			assertTrue(true);
		}
		
		int contador = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contador++;
		}
		
		assertEquals(1, contador);
		
	}
	
	@Test
	public void cadastrarPontoSemNome(){
		Ponto a = new Ponto("", 1);
		Ponto b = new Ponto("    ", 1);
		
		try{
			controller.cadastrarPonto(b);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		} catch (ConflitoException e) {
			fail();
		}
		
		try{
			controller.cadastrarPonto(a);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		} catch (ConflitoException e) {
			fail();
		}
		
		int contador = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contador++;
		}
		
		assertEquals(0, contador);
	}
	
	@Test
	public void alterarPontoSucesso(){
		Ponto a = new Ponto("A", 1);
		Ponto a1 = null;
		
		try{
			a1 = controller.cadastrarPonto(a);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try{
			controller.alterarPonto("A", "A1", 2);
		}catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			a1 = controller.recuperarPonto("A1");
		} catch (PontoNaoEncontradoException | CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		assertEquals(a, a1);
		assertEquals(a.getNome(), a1.getNome());
		assertEquals(a.getTipo(), a1.getTipo());
	}
	
	@Test
	public void alterarPontoNomeExistente(){
		Ponto a = new Ponto("A", 1);
		Ponto b = new Ponto("B", 1);
		Ponto a1 = null;
		
		try{
			a1 = controller.cadastrarPonto(a);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try{
			controller.cadastrarPonto(b);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try{
			controller.alterarPonto("A", "B", 2);
		}catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			assertTrue(true);
		}
		
		try {
			a1 = controller.recuperarPonto("A1");
		} catch (PontoNaoEncontradoException | CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		}
		
		assertEquals(a, a1);
		assertEquals(a.getNome(), a1.getNome());
		assertEquals(a.getTipo(), a1.getTipo());
	}
	
	@Test
	public void alterarPontoSemNome(){
		Ponto a = new Ponto("A", 1);
		Ponto b = new Ponto("B", 1);
		Ponto a1 = null;
		
		try{
			a1 = controller.cadastrarPonto(a);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try{
			controller.cadastrarPonto(b);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try{
			controller.alterarPonto("A", "   ", 2);
		}catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		} catch (ConflitoException e) {
			fail();
		}
		
		try{
			controller.alterarPonto("B", "", 2);
		}catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			a1 = controller.recuperarPonto("   ");
		} catch (PontoNaoEncontradoException | CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		}
		
		assertEquals(a, a1);
		assertEquals(a.getNome(), a1.getNome());
		assertEquals(a.getTipo(), a1.getTipo());
	}
	
	@Test
	public void alterarPontoExistente(){
		Ponto aux = null;
		
		try{
			controller.alterarPonto("A", "B", 2);
		}catch (PontoNaoEncontradoException e) {
			assertTrue(true);
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		assertEquals(aux, null);

	}
	
	@Test
	public void removerPontoSucesso1(){
		Ponto a = new Ponto("A", 1);
		Ponto b = new Ponto("B", 1);
		
		try{
			controller.cadastrarPonto(b);
			controller.cadastrarPonto(a);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("A", 10, "B");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		int contadorAresta = 0;
		MeuIterador iterador = null;
		try {
			iterador = controller.listarArestas("A");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		try {
			iterador = controller.listarArestas("B");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		
		assertEquals(2, contadorAresta);
		
		try {
			controller.removeAresta(a, b);
		} catch (ArestaNaoEncontradoException e) {
			fail();
		} catch (PontoNuloException e) {
			fail();
		}
		
		
		contadorAresta = 0;
		
		try {
			iterador = controller.listarArestas("A");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		
		try {
			iterador = controller.listarArestas("B");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		
		assertEquals(0, contadorAresta);
	}
	
	@Test
	public void removerPontoSucesso2(){
		Ponto a = new Ponto("A", 1);
		Ponto b = new Ponto("B", 1);
		Ponto c = new Ponto("C", 1);
		
		try{
			controller.cadastrarPonto(b);
			controller.cadastrarPonto(a);
			controller.cadastrarPonto(c);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("A", 10, "B");
			controller.cadastrarAresta("A", 20, "C");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		int contadorAresta = 0;
		MeuIterador iterador = null;
		try {
			iterador = controller.listarArestas("A");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		
		try {
			iterador = controller.listarArestas("B");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		
		try {
			iterador = controller.listarArestas("C");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		
		assertEquals(4, contadorAresta);
		
		try {
			controller.removeAresta(a, b);
		} catch (ArestaNaoEncontradoException e) {
			fail();
		} catch (PontoNuloException e) {
			fail();
		}
		
		
		contadorAresta = 0;
		
		try {
			iterador = controller.listarArestas("A");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		
		try {
			iterador = controller.listarArestas("B");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		
		try {
			iterador = controller.listarArestas("C");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		
		assertEquals(2, contadorAresta);
	}
	
	@Test
	public void removerPontoSucesso3(){
		Ponto a = new Ponto("A", 1);
		Ponto b = new Ponto("B", 1);
		Ponto c = new Ponto("C", 1);
		
		try{
			controller.cadastrarPonto(b);
			controller.cadastrarPonto(a);
			controller.cadastrarPonto(c);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("A", 10, "B");
			controller.cadastrarAresta("A", 20, "C");
			controller.cadastrarAresta("B", 20, "C");
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		int contadorAresta = 0;
		MeuIterador iterador = null;
		try {
			iterador = controller.listarArestas("A");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		
		try {
			iterador = controller.listarArestas("B");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		
		try {
			iterador = controller.listarArestas("C");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		
		assertEquals(6, contadorAresta);
		
		try {
			controller.removeAresta(a, b);
		} catch (ArestaNaoEncontradoException e) {
			fail();
		} catch (PontoNuloException e) {
			fail();
		}
		
		
		contadorAresta = 0;
		
		try {
			iterador = controller.listarArestas("A");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		
		try {
			iterador = controller.listarArestas("B");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		
		try {
			iterador = controller.listarArestas("C");
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorAresta++;
		}
		
		assertEquals(4, contadorAresta);
	}
}
