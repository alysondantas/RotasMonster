package br.uefs.ecomp.rotasMonster.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import br.uefs.ecomp.rotasMonster.controller.*;
import br.uefs.ecomp.rotasMonster.exceptions.*;
import br.uefs.ecomp.rotasMonster.model.Ponto;
import br.uefs.ecomp.rotasMonster.util.*;

public class ArestasTest {
	AdministradorController controller = new AdministradorController();
	
	@Test
	public void cadastrarArestaSucesso(){
		Ponto a = new Ponto("A", 1, 13, 23);
		Ponto b = new Ponto("B", 1, 14, 23);
		
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
		
		int contadorPonto = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorPonto++;
		}
		
		int contadorAresta = 0;
		
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
		
		assertEquals(2, contadorPonto);
		assertEquals(2, contadorAresta);
	}
	
	@Test
	public void cadastrarArestaSemOrigem(){
		Ponto b = new Ponto("B", 1, 13, 23);
		
		try{
			controller.cadastrarPonto(b);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("", 10, "B");
		} catch (CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		int contadorPonto = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorPonto++;
		}
		
		int contadorAresta = 0;
		
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
		assertEquals(1, contadorPonto);
		assertEquals(0, contadorAresta);
		
	}
	
	@Test
	public void cadastrarArestaSemDestino(){
		Ponto a = new Ponto("A", 1, 13, 23);
		
		try{
			controller.cadastrarPonto(a);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		try {
			controller.cadastrarAresta("A", 10, "");
		} catch (CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		int contadorPonto = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorPonto++;
		}
		
		int contadorAresta = 0;
		
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
		assertEquals(1, contadorPonto);
		assertEquals(0, contadorAresta);
		
	}
	
	@Test
	public void cadastrarArestaSemPonto(){
		
		try {
			controller.cadastrarAresta("     ", 10, "");
		} catch (CampoObrigatorioInexistenteException e) {
			assertTrue(true);
		} catch (PontoNaoEncontradoException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		
		try {
			controller.listarArestas("A");
		} catch (PontoNaoEncontradoException e) {
			assertTrue(true);
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		}
		
	}
	
	@Test
	public void cadastrarArestaPontoInexistente(){
		Ponto a = new Ponto("A", 1, 13, 23);
		
		try{
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
			assertTrue(true);
		} catch (ConflitoException e) {
			fail();
		}
		
		int contadorPonto = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorPonto++;
		}
		
		int contadorAresta = 0;
		
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
		assertEquals(1, contadorPonto);
		assertEquals(0, contadorAresta);
	}
	
	@Test
	public void removerArestaSucesso(){
		Ponto a = new Ponto("A", 1, 13, 23);
		Ponto b = new Ponto("B", 1, 14, 23);
		
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
		
		
		try {
			controller.removeAresta(a, b);
		} catch (ArestaNaoEncontradoException e1) {
			fail();
		} catch (PontoNuloException e1) {
			fail();
		}
		
		int contadorPonto = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorPonto++;
		}
		
		int contadorAresta = 0;
		
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
		
		assertEquals(2, contadorPonto);
		assertEquals(0, contadorAresta);
	}
	
	@Test
	public void removerArestaInexistente(){
		Ponto a = new Ponto("A", 1, 13, 23);
		Ponto b = new Ponto("B", 1, 14, 23);
		Ponto c = new Ponto("C", 1, 13, 25);
		
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
		
		
		try {
			controller.removeAresta(a, c);
		} catch (ArestaNaoEncontradoException e1) {
			assertTrue(true);
		} catch (PontoNuloException e1) {
			fail();
		}
		
		int contadorPonto = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorPonto++;
		}
		
		int contadorAresta = 0;
		
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
		
		assertEquals(2, contadorPonto);
		assertEquals(2, contadorAresta);
	}
	
	@Test
	public void removerArestaNulo(){
		Ponto a = new Ponto("A", 1, 13, 23);
		Ponto b = new Ponto("B", 1, 14, 23);
		Ponto c = null;
		
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
		
		
		try {
			controller.removeAresta(a, c);
		} catch (ArestaNaoEncontradoException e1) {
			assertTrue(true);
		} catch (PontoNuloException e1) {
			assertTrue(true);
		}
		
		int contadorPonto = 0;
		MeuIterador iterador = controller.listarPontos();
		while(iterador.temProximo()){
			iterador.obterProximo();
			contadorPonto++;
		}
		
		int contadorAresta = 0;
		
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
		
		assertEquals(2, contadorPonto);
		assertEquals(2, contadorAresta);
	}
	
}
