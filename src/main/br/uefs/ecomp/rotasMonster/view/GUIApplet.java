package br.uefs.ecomp.rotasMonster.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.*;

import br.uefs.ecomp.rotasMonster.controller.AdministradorController;
import br.uefs.ecomp.rotasMonster.exceptions.ArestaNaoEncontradoException;
import br.uefs.ecomp.rotasMonster.exceptions.CampoObrigatorioInexistenteException;
import br.uefs.ecomp.rotasMonster.exceptions.DestinoNaoEncontradoException;
import br.uefs.ecomp.rotasMonster.exceptions.GrafoNuloException;
import br.uefs.ecomp.rotasMonster.exceptions.PontoNaoEncontradoException;
import br.uefs.ecomp.rotasMonster.model.Aresta;
import br.uefs.ecomp.rotasMonster.model.Caminho;
import br.uefs.ecomp.rotasMonster.model.DoisPontos;
import br.uefs.ecomp.rotasMonster.model.Grafo;
import br.uefs.ecomp.rotasMonster.model.Ponto;
import br.uefs.ecomp.rotasMonster.util.Dijkstra;
import br.uefs.ecomp.rotasMonster.util.Lista;
import br.uefs.ecomp.rotasMonster.util.MeuIterador;

public class GUIApplet extends JApplet{
	public GUIApplet() {
	}
	private JPanel canvas = new JPanel();
	private AdministradorController controller = new AdministradorController();
	private Caminho menor;

//	public GUIApplet() {
//	}

//	public void start() {
//		
//	}
	
	public void init() {
		//Parte responsável pelo Look and Feel do programa
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); //define o look and feel como o padrão do sistema
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    }
	    SwingUtilities.updateComponentTreeUI(this); //atualiza o Look and Feel de todos os componentes relacionados
	    
	    //Parte responsável pelas definições da janela
		getContentPane().setLayout(null); //Sem layout pré-definido
		setSize(600, 400); //Define o tamanho da janela como 600 x 400
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				showStatus("Mouse clicado em (" + (arg0.getX() - 115) + ", " + (arg0.getY() - 11) + "). ");
			}
		});
		
		//Parte responsável por adicionar os componentes da janela
		
		//botão de cadastrar ponto
		JButton btnPonto = new JButton("Ponto");
		btnPonto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				 CadastroPonto cadastroPonto = new CadastroPonto();
				 cadastroPonto.janelaCadastro(controller);
				 
				 canvas.paintComponents(updateCanvas());				 
			}
		});
		btnPonto.setBounds(10, 33, 89, 23);
		getContentPane().add(btnPonto);
		
		//botão de cadastrar aresta
		JButton btnAresta = new JButton("Aresta");
		btnAresta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CadastroAresta cadastroAresta = new CadastroAresta();
				cadastroAresta.janelaCadastro(controller);
				
				canvas.paintComponents(updateCanvas());
			}
		});
		btnAresta.setBounds(10, 61, 89, 23);
		getContentPane().add(btnAresta);
		
		//Label de cadastro
		JLabel lblCadastrar = new JLabel("Cadastrar");
		lblCadastrar.setBounds(10, 11, 69, 14);
		getContentPane().add(lblCadastrar);
		
		//Label de Remoção
		JLabel lblRemover = new JLabel("Remover");
		lblRemover.setBounds(10, 95, 46, 14);
		getContentPane().add(lblRemover);
		
		//Botão de remover ponto
		JButton btnPonto_1 = new JButton("Ponto");
		btnPonto_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RemoverPonto removeponto = new RemoverPonto();
				removeponto.janelaRemove(controller);
				
				canvas.paintComponents(updateCanvas());

			}
		});
		btnPonto_1.setBounds(10, 113, 89, 23);
		getContentPane().add(btnPonto_1);
		
		//botão de remover aresta
		JButton btnAresta_1 = new JButton("Aresta");
		btnAresta_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RemoveAresta removearesta = new RemoveAresta();
				removearesta.janelaRemove(controller);
				
				canvas.paintComponents(updateCanvas());

			}
		});
		btnAresta_1.setBounds(10, 140, 89, 23);
		getContentPane().add(btnAresta_1);
		
		//comboBox de Ponto de Origem
		JComboBox<String> comboBoxOrigem = new JComboBox<String>();
		comboBoxOrigem.addMouseListener(new MouseAdapter() { //ação para atualizar o ComboBox toda a vez que o usuário clicar nele

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("oi");
				comboBoxOrigem.removeAllItems(); //limpa o comboBox
				MeuIterador itera = controller.listarPontos(); 
				while(itera.temProximo()) { //itera a lista de pontos do controller
					Ponto p = (Ponto)itera.obterProximo();
					comboBoxOrigem.addItem(p.getNome()); //adiciona ele na comboBox
				}
			}
			
		});
		
		comboBoxOrigem.setBounds(10, 221, 89, 20);
		getContentPane().add(comboBoxOrigem);
		
		//Label de Cálculo do Menor Caminho
		JLabel lblClculoDeRota = new JLabel("C\u00E1lculo de Rota");
		lblClculoDeRota.setBounds(10, 174, 83, 14);
		getContentPane().add(lblClculoDeRota);
		
		//Label do ponto de partida
		JLabel lblPontoDePartida = new JLabel("Ponto de Partida");
		lblPontoDePartida.setBounds(10, 199, 89, 14);
		getContentPane().add(lblPontoDePartida);
		
		//Label do ponto de destino
		JLabel lblPontoDeDestino = new JLabel("Ponto de Destino");
		lblPontoDeDestino.setBounds(10, 247, 89, 14);
		getContentPane().add(lblPontoDeDestino);
		
		//ComboBox do ponto de Destino
		JComboBox<String> comboBoxDestino = new JComboBox<String>();
		comboBoxDestino.addMouseListener(new MouseAdapter() { //ação para atualizar o ComboBox toda a vez que o usuário clicar nele

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("oi");
				comboBoxDestino.removeAllItems(); //limpa a comboBox
				MeuIterador itera = controller.listarPontos();
				while(itera.temProximo()) { //itera a lista de pontos do controller
					Ponto p = (Ponto)itera.obterProximo();
					if(p.getTipo() == 3) //caso seja um ponto de destino
					comboBoxDestino.addItem(p.getNome()); //adiciona ele na comboBox
				}
			}
			
		});
		
		comboBoxDestino.setBounds(10, 269, 89, 20);
		getContentPane().add(comboBoxDestino);
		
		//Label do Ponto de Coleta
		JLabel lblPontoDeColeta = new JLabel("Ponto de Coleta");
		lblPontoDeColeta.setBounds(10, 295, 89, 14);
		getContentPane().add(lblPontoDeColeta);
		
		//ComboBox do Ponto de Coleta
		JComboBox comboBoxColeta = new JComboBox();
		comboBoxColeta.addMouseListener(new MouseAdapter() { //ação para atualizar o ComboBox toda a vez que o usuário clicar nele

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("oi");
				comboBoxColeta.removeAllItems(); //limpa a comboBox
				MeuIterador itera = controller.listarPontos();
				while(itera.temProximo()) { //itera a lista de pontos do controller
					Ponto p = (Ponto)itera.obterProximo();
					if(p.getTipo() == 2) //caso seja um ponto de coleta
					comboBoxColeta.addItem(p.getNome()); //adiciona ele na comboBox
				}
			}
			
		});
		comboBoxColeta.setBounds(10, 317, 89, 20);
		getContentPane().add(comboBoxColeta);
		
		//Botão de calcular o menor caminho
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String po = (String) comboBoxOrigem.getSelectedItem();
				String pd = (String) comboBoxDestino.getSelectedItem();
				String pc = (String) comboBoxColeta.getSelectedItem();
				try {
					menor = controller.realizarDijkstra(po, pc, pd);
					canvas.paintComponents(desenharCaminho());
				} catch (PontoNaoEncontradoException e) {
					JOptionPane.showMessageDialog(null, "Não foi possível encontrar o ponto especificado");
				} catch (CampoObrigatorioInexistenteException e) {
					JOptionPane.showMessageDialog(null, "Campo obrigatório não preechido");
				} catch (GrafoNuloException e) {
					JOptionPane.showMessageDialog(null, "Grafo Nulo");
				} catch (DestinoNaoEncontradoException e) {
					JOptionPane.showMessageDialog(null, "Erro grafo sem conexão com o destino!");
				}

			}
		});
		btnCalcular.setBounds(10, 352, 89, 23);
		getContentPane().add(btnCalcular);
		
		//Configurações do JPanel com o grafo a ser exibido
		canvas.setBackground(Color.BLACK);
		canvas.setBounds(115, 11, 475, 379);
		getContentPane().add(canvas);
		
		
	}
	
//	public void paint() {
//		
//	}
	
	public Graphics updateCanvas() { //Método para atualizar o JPanel com o Grafo
		System.out.println("Atualizando Canvas...");
		Graphics2D g = (Graphics2D)canvas.getGraphics(); //Pega o Graphics atual do canvas e salva na variável g
		
		//PARTE RESPONSÁVEL PELA LIMPEZA DA TELA (SUPER MANGUE)
		g.setPaint(Color.BLACK);
		g.fill(new Rectangle(0, 0, 475, 379));
		g.setPaint(Color.WHITE);
		
		//Parte responsável pela iteração e desenho dos pontos
		MeuIterador itP = controller.listarPontos(); //cria um iterador com a lista de pontos do controller
		while (itP.temProximo()) { //Itera a lista de pontos
			Ponto p = (Ponto) itP.obterProximo();
			System.out.println("Ponto " + p.getNome());
			g.drawOval(p.getCoordX(), p.getCoordY(), 10, 10); //desenha um círculo com as coordenadas do ponto e de tamanho 10x10
			g.drawString(p.getNome(), p.getCoordX(), p.getCoordY() - 10); //escreve o nome do ponto 10 pixels acima dele
			
			MeuIterador itA = (MeuIterador) p.getArestas().iterador();
			while (itA.temProximo()) {
				Aresta a = (Aresta) itA.obterProximo();
				System.out.println("Aresta " + a.gettempo());
				g.drawLine(p.getCoordX() + 5, p.getCoordY() + 5, a.getPontoDestino().getCoordX() + 5, a.getPontoDestino().getCoordY() + 5);
				g.drawString(Double.toString(a.gettempo()), (p.getCoordX() + a.getPontoDestino().getCoordX())/2, (p.getCoordY() + a.getPontoDestino().getCoordY())/2);
			}
		} 
		return g; //retorna g atualizado
		
	}
	
	public Graphics desenharCaminho() {
		System.out.println("Desenhando o menor caminho...");
		Graphics2D g = (Graphics2D)updateCanvas();
		g.setPaint(Color.RED);
		String caminho = "";
		Double tempo = menor.getOrigemColeta().getTempo() + menor.getColetaDestino().getTempo();
		
		Lista lista1 = menor.getOrigemColeta().getPontos();
		Lista lista2 = menor.getColetaDestino().getPontos();
		MeuIterador iterador = (MeuIterador)lista1.iterador();
		while(iterador.temProximo()) {
			DoisPontos dp = (DoisPontos)iterador.obterProximo();
			Ponto pA = dp.getPontoA();
			Ponto pB = dp.getPontoB();
			caminho = caminho + pA.getNome() + pB.getNome();
			g.drawOval(pA.getCoordX(), pA.getCoordY(), 10, 10);
			g.drawOval(pB.getCoordX(), pB.getCoordY(), 10, 10);
			g.drawLine(pA.getCoordX() + 5, pA.getCoordY() + 5, pB.getCoordX() + 5, pB.getCoordY() + 5);
		}
		iterador = (MeuIterador) lista2.iterador();
		while(iterador.temProximo()) {
			DoisPontos dp = (DoisPontos)iterador.obterProximo();
			Ponto pA = dp.getPontoA();
			Ponto pB = dp.getPontoB();
			caminho = caminho + pA.getNome() + pB.getNome();
			g.drawOval(pA.getCoordX(), pA.getCoordY(), 10, 10);
			g.drawOval(pB.getCoordX(), pB.getCoordY(), 10, 10);
			g.drawLine(pA.getCoordX() + 5, pA.getCoordY() + 5, pB.getCoordX() + 5, pB.getCoordY() + 5);
		}
		
		showStatus("Caminho percorrido: " + caminho + " / Tempo: " + tempo);
		
		return g;
	}
}

