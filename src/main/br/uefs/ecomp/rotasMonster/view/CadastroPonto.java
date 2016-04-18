package br.uefs.ecomp.rotasMonster.view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.uefs.ecomp.rotasMonster.controller.AdministradorController;
import br.uefs.ecomp.rotasMonster.exceptions.CampoObrigatorioInexistenteException;
import br.uefs.ecomp.rotasMonster.exceptions.ConflitoException;
import br.uefs.ecomp.rotasMonster.exceptions.PontoNuloException;
import br.uefs.ecomp.rotasMonster.util.Ponto;

public class CadastroPonto extends JFrame {

	private JPanel contentPane;
	private static AdministradorController controller;
	private JTextField textPNome;
	private JLabel lblTipoDoPonto;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textX;
	private JTextField textY;
	private JRadioButton rdColeta;
	private JRadioButton rdDestino; 
	/**
	 * Launch the application.
	 */
	public static void janelaCadastro(AdministradorController controller1) {
		controller = controller1;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroPonto frame = new CadastroPonto();
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
	public CadastroPonto() {
		setTitle("Pontos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 222, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textPNome = new JTextField();
		textPNome.setBounds(10, 33, 86, 20);
		contentPane.add(textPNome);
		textPNome.setColumns(10);
		
		JLabel lblNomeDoPonto = new JLabel("Nome do Ponto:");
		lblNomeDoPonto.setBounds(10, 11, 103, 14);
		contentPane.add(lblNomeDoPonto);
		
		lblTipoDoPonto = new JLabel("Tipo do ponto:");
		lblTipoDoPonto.setBounds(10, 64, 103, 14);
		contentPane.add(lblTipoDoPonto);
		
		rdColeta = new JRadioButton("Coleta");
		buttonGroup.add(rdColeta);
		rdColeta.setBounds(10, 85, 109, 23);
		contentPane.add(rdColeta);
		
		rdDestino = new JRadioButton("Destino");
		buttonGroup.add(rdDestino);
		rdDestino.setBounds(10, 111, 109, 23);
		contentPane.add(rdDestino);
		
		textX = new JTextField();
		textX.setBounds(30, 165, 53, 20);
		contentPane.add(textX);
		textX.setColumns(10);
		
		JLabel lblCoordenadas = new JLabel("Coordenadas:");
		lblCoordenadas.setBounds(10, 141, 86, 14);
		contentPane.add(lblCoordenadas);
		
		JLabel lblX = new JLabel("X:");
		lblX.setBounds(10, 168, 23, 14);
		contentPane.add(lblX);
		
		JLabel lblY = new JLabel("Y:");
		lblY.setBounds(93, 168, 23, 14);
		contentPane.add(lblY);
		
		textY = new JTextField();
		textY.setBounds(113, 165, 53, 20);
		contentPane.add(textY);
		textY.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cadastrarEvento();
			}
		});
		btnCadastrar.setBounds(10, 213, 89, 23);
		contentPane.add(btnCadastrar);
	}
	
	public void cadastrarEvento(){
		
		String nome = textPNome.getText();
		int tipo = 0;
		if(rdColeta.isSelected()){
			tipo = 2;
		}else if(rdDestino.isSelected()){
			tipo = 3;
		}
		String coordX = textX.getText();
		String coordY = textY.getText();
		int x = -1;
		try {
			x = Integer.parseInt(coordX);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Apenas numeros!");
		}
		
		int y = -1;
		try {
			y = Integer.parseInt(coordY);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Apenas numeros!");
		}
		Ponto verifica = null;
		Ponto p = new Ponto(nome, tipo, x, y);
		try {
			verifica=controller.cadastrarPonto(p);
		} catch (PontoNuloException e) {
			JOptionPane.showMessageDialog(null, "Erro interno!");
		} catch (CampoObrigatorioInexistenteException e) {
			JOptionPane.showMessageDialog(null, "Erro campo não preenchido!");
		} catch (ConflitoException e) {
			JOptionPane.showMessageDialog(null, "Erro ponto ja cadastrado!");
		}
		
		if(verifica != null && verifica.equals(p)){
			JOptionPane.showMessageDialog(null, "Ponto cadastrado!");
			 this.dispose();
		}else{
			JOptionPane.showMessageDialog(null, "Erro ponto não cadastrado!");
		}
	}
}
