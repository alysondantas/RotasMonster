/*******************************************************************************

Autor: Alyson Felipe Oliveira Dantas e Bruno Menezes de Lima

Componente Curricular: MI - Algoritmos II

Concluido em: 03/05/2016

Declaro que este código foi elaborado por esta dupla e não contém nenhum

trecho de código de outro colega ou de outro autor, tais como provindos de livros e

apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código

de outra autoria que não a minha está destacado com uma citação para o autor e a fonte

do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.

 ******************************************************************************************/
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
import br.uefs.ecomp.rotasMonster.model.Ponto;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RemoverPonto extends JFrame {

	private JPanel contentPane;
	private JTextField textPonto;
	private static AdministradorController controller;

	/**
	 * Launch the application.
	 */
	public static void janelaRemove(AdministradorController controller1) {
		controller = controller1;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoverPonto frame = new RemoverPonto();
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
	public RemoverPonto() {
		setTitle("R.Ponto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 210, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textPonto = new JTextField();
		textPonto.setBounds(10, 36, 86, 20);
		contentPane.add(textPonto);
		textPonto.setColumns(10);
		
		JLabel lblNomeDoPonto = new JLabel("Nome do ponto:");
		lblNomeDoPonto.setBounds(10, 11, 148, 14);
		contentPane.add(lblNomeDoPonto);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removerEvento();
			}
		});
		btnRemover.setBounds(10, 67, 89, 23);
		contentPane.add(btnRemover);
	}
	public void removerEvento(){
		String nome = textPonto.getText();
		Ponto p = null;
		try {
			p = controller.removerPonto(nome);
		} catch (PontoNaoEncontradoException e) {
			JOptionPane.showMessageDialog(null, "Erro ponto não encontrado!");
		} catch (CampoObrigatorioInexistenteException e) {
			JOptionPane.showMessageDialog(null, "Erro campo obrigatorio não preenchido!");
		} catch (ArestaNaoEncontradoException e) {
			JOptionPane.showMessageDialog(null, "Erro interno (aresta erro)!");
		} catch (PontoNuloException e) {
			JOptionPane.showMessageDialog(null, "Erro interno (ponto nulo!)");
		}
		if(p != null){
			JOptionPane.showMessageDialog(null, "Ponto removido!");
			 this.dispose();
		}else{
			JOptionPane.showMessageDialog(null, "Erro ponto não removido!");
		}
	}
}
