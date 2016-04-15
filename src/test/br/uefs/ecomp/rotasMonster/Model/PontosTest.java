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
		
		try{
			Ponto b1 = controller.cadastrarPonto(b);
			Ponto a1 = controller.cadastrarPonto(a);
		}catch(PontoNuloException e){
			fail();
		} catch (CampoObrigatorioInexistenteException e) {
			fail();
		} catch (ConflitoException e) {
			fail();
		}
		
		
	}
}
