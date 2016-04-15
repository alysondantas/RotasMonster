package br.uefs.ecomp.rotasMonster.Model;

import static org.junit.Assert.fail;

import org.junit.Test;

import br.uefs.ecomp.rotasMonster.controller.AdministradorController;
import br.uefs.ecomp.rotasMonster.exceptions.CampoObrigatorioInexistenteException;
import br.uefs.ecomp.rotasMonster.exceptions.ConflitoException;
import br.uefs.ecomp.rotasMonster.exceptions.PontoNuloException;
import br.uefs.ecomp.rotasMonster.util.Ponto;

public class ArestasTest {
	AdministradorController controller = new AdministradorController();
	
	@Test
	public void cadastrarArestaSucesso(){
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
		
		
	}
}
