package br.uefs.ecomp.rotasMonster.view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.uefs.ecomp.rotasMonster.controller.AdministradorController;
import br.uefs.ecomp.rotasMonster.exceptions.CampoObrigatorioInexistenteException;
import br.uefs.ecomp.rotasMonster.exceptions.ConflitoException;
import br.uefs.ecomp.rotasMonster.exceptions.PontoNaoEncontradoException;
import br.uefs.ecomp.rotasMonster.model.Aresta;

public class CadastroAresta extends JFrame {

	private JPanel contentPane;
	private static AdministradorController controller;
	private JTextField textPOrigem;
	private JTextField textPDestino;
	private JTextField textTempo;

	/**
	 * Launch the application.
	 */
	public static void janelaCadastro(AdministradorController controller1) {
		controller = controller1;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroAresta frame = new CadastroAresta();
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
	public CadastroAresta() {
		setTitle("Arestas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 222, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNomeDoPonto = new JLabel("Nome do ponto de origem:");
		lblNomeDoPonto.setBounds(10, 11, 157, 14);
		contentPane.add(lblNomeDoPonto);
		
		textPOrigem = new JTextField();
		textPOrigem.setBounds(10, 36, 86, 20);
		contentPane.add(textPOrigem);
		textPOrigem.setColumns(10);
		
		JLabel lblNomeDoDestino = new JLabel("Nome do ponto de destino:");
		lblNomeDoDestino.setBounds(10, 67, 157, 14);
		contentPane.add(lblNomeDoDestino);
		
		textPDestino = new JTextField();
		textPDestino.setBounds(10, 92, 86, 20);
		contentPane.add(textPDestino);
		textPDestino.setColumns(10);
		
		JLabel lblTempoDaOrigem = new JLabel("Tempo da origem para o destino:");
		lblTempoDaOrigem.setBounds(10, 123, 186, 14);
		contentPane.add(lblTempoDaOrigem);
		
		textTempo = new JTextField();
		textTempo.setBounds(10, 148, 86, 20);
		contentPane.add(textTempo);
		textTempo.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cadastrarEvento();
			}
		});
		btnCadastrar.setBounds(10, 179, 89, 23);
		contentPane.add(btnCadastrar);
	}
	
	public void cadastrarEvento(){
		String nomeOrigem = textPOrigem.getText();
		String nomeDestino = textPDestino.getText();
		String tempoS = textTempo.getText();
		
		double tempo = 0;
		try {
			tempo = Double.parseDouble(tempoS);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Apenas numeros!");
		}
		
		Aresta a = null;
		try {
			a = controller.cadastrarAresta(nomeOrigem, tempo, nomeDestino);
		} catch (CampoObrigatorioInexistenteException e) {
			JOptionPane.showMessageDialog(null, "Erro campo obrigatorio não preenchido!");
		} catch (PontoNaoEncontradoException e) {
			JOptionPane.showMessageDialog(null, "Um ponto informado não encontrado!");
		} catch (ConflitoException e) {
			JOptionPane.showMessageDialog(null, "Erro Aresta ja cadastrada!");
		}
		
		if(a != null){
			JOptionPane.showMessageDialog(null, "Aresta cadastrado!");
			 this.dispose();
		}else{
			JOptionPane.showMessageDialog(null, "Erro não cadastrada!");
		}
	}

}
