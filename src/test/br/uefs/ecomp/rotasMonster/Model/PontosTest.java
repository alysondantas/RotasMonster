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
		
	}
}
