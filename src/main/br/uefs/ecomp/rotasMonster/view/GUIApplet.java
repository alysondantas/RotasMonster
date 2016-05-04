package br.uefs.ecomp.rotasMonster.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
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
import br.uefs.ecomp.rotasMonster.exceptions.GrafoNuloException;
import br.uefs.ecomp.rotasMonster.exceptions.PontoNaoEncontradoException;
import br.uefs.ecomp.rotasMonster.model.Aresta;
import br.uefs.ecomp.rotasMonster.model.Caminho;
import br.uefs.ecomp.rotasMonster.model.Grafo;
import br.uefs.ecomp.rotasMonster.model.Ponto;
import br.uefs.ecomp.rotasMonster.util.Dijkstra;
import br.uefs.ecomp.rotasMonster.util.MeuIterador;

public class GUIApplet extends JApplet{
	private JPanel canvas = new JPanel();
	private AdministradorController controller = new AdministradorController();

	public GUIApplet() {
	}

	public void start() {
		
	}
	
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
		
		//Parte responsável por adicionar os componentes da janela
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
		
		JLabel lblCadastrar = new JLabel("Cadastrar");
		lblCadastrar.setBounds(10, 11, 69, 14);
		getContentPane().add(lblCadastrar);
		
		JLabel lblRemover = new JLabel("Remover");
		lblRemover.setBounds(10, 95, 46, 14);
		getContentPane().add(lblRemover);
		
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
		
		JComboBox comboBoxOrigem = new JComboBox();
		comboBoxOrigem.setBounds(10, 221, 89, 20);
		getContentPane().add(comboBoxOrigem);
		
		JLabel lblClculoDeRota = new JLabel("C\u00E1lculo de Rota");
		lblClculoDeRota.setBounds(10, 174, 83, 14);
		getContentPane().add(lblClculoDeRota);
		
		JLabel lblPontoDePartida = new JLabel("Ponto de Partida");
		lblPontoDePartida.setBounds(10, 199, 89, 14);
		getContentPane().add(lblPontoDePartida);
		
		JLabel lblPontoDeDestino = new JLabel("Ponto de Destino");
		lblPontoDeDestino.setBounds(10, 247, 89, 14);
		getContentPane().add(lblPontoDeDestino);
		
		JComboBox comboBoxDestino = new JComboBox();
		comboBoxDestino.setBounds(10, 269, 89, 20);
		getContentPane().add(comboBoxDestino);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Ponto po = (Ponto)comboBoxOrigem.getSelectedItem();
				String nomePo = po.getNome();
				Ponto pd = (Ponto)comboBoxDestino.getSelectedItem();
				String nomePd = pd.getNome();
				try {
					Caminho menor = controller.realizarDijkstra(nomePo, null, nomePd);
				} catch (PontoNaoEncontradoException | CampoObrigatorioInexistenteException | GrafoNuloException e) {
					e.printStackTrace();
				}
			}
		});
		btnCalcular.setBounds(10, 300, 89, 23);
		getContentPane().add(btnCalcular);
		
		canvas.setBackground(Color.CYAN);
		canvas.setBounds(115, 11, 475, 379);
		getContentPane().add(canvas);
		
	}
	
	public void paint() {
		
	}
	
	public Graphics updateCanvas() {
		System.out.println("Atualizando Canvas...");
		Graphics g = canvas.getGraphics(); //Pega o Graphics atual do canvas e salva na variável g

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

}

