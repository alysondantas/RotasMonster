package br.uefs.ecomp.rotasMonster.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.uefs.ecomp.rotasMonster.controller.AdministradorController;
import br.uefs.ecomp.rotasMonster.exceptions.ArestaNaoEncontradoException;
import br.uefs.ecomp.rotasMonster.exceptions.CampoObrigatorioInexistenteException;
import br.uefs.ecomp.rotasMonster.exceptions.PontoNaoEncontradoException;
import br.uefs.ecomp.rotasMonster.exceptions.PontoNuloException;
import br.uefs.ecomp.rotasMonster.util.Aresta;
import br.uefs.ecomp.rotasMonster.util.Ponto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RemoveAresta extends JFrame {

	private JPanel contentPane;
	private static AdministradorController controller;
	private JTextField textPOrigem;
	private JTextField textPDestino;
	
	/**
	 * Launch the application.
	 */
	public static void janelaRemove(AdministradorController controller1) {
		controller = controller1;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveAresta frame = new RemoveAresta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RemoveAresta() {
		setTitle("R.Aresta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 210, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPontoOrgiem = new JLabel("Ponto Orgiem:");
		lblPontoOrgiem.setBounds(10, 11, 86, 14);
		contentPane.add(lblPontoOrgiem);
		
		textPOrigem = new JTextField();
		textPOrigem.setBounds(10, 36, 86, 20);
		contentPane.add(textPOrigem);
		textPOrigem.setColumns(10);
		
		JLabel lblPontoDestino = new JLabel("Ponto Destino:");
		lblPontoDestino.setBounds(10, 67, 174, 14);
		contentPane.add(lblPontoDestino);
		
		textPDestino = new JTextField();
		textPDestino.setBounds(10, 92, 86, 20);
		contentPane.add(textPDestino);
		textPDestino.setColumns(10);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removerEvento();
			}
		});
		btnRemover.setBounds(10, 132, 89, 23);
		contentPane.add(btnRemover);
	}
	public void removerEvento(){
		String nomePO = textPOrigem.getText();
		String nomePD = textPDestino.getText();
		Ponto origem = null;
		Ponto destino = null;
		try {
			origem = controller.recuperarPonto(nomePO);
		} catch (PontoNaoEncontradoException e) {
			JOptionPane.showMessageDialog(null, "Erro ponto n�o encontrado!");
		} catch (CampoObrigatorioInexistenteException e) {
			JOptionPane.showMessageDialog(null, "Erro Campo Obrigatorio inexistente!");
		}
		
		try {
			destino = controller.recuperarPonto(nomePD);
		} catch (PontoNaoEncontradoException e) {
			JOptionPane.showMessageDialog(null, "Erro ponto n�o encontrado!");
		} catch (CampoObrigatorioInexistenteException e) {
			JOptionPane.showMessageDialog(null, "Erro Campo Obrigatorio inexistente!");
		}
		
		if(origem != null && destino != null){
			Aresta a = null;
			try {
				a = controller.removeAresta(origem, destino);
			} catch (ArestaNaoEncontradoException e) {
				JOptionPane.showMessageDialog(null, "Erro aresta n�o encontrado!");
			} catch (PontoNuloException e) {
				JOptionPane.showMessageDialog(null, "Erro interno!");
			}
		}else{
			JOptionPane.showMessageDialog(null, "Erro Aresta n�o removida!");
		}
		
	}
}
