package br.uefs.ecomp.rotasMonster.view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import br.uefs.ecomp.rotasMonster.controller.AdministradorController;
import javax.swing.JComboBox;

public class GUI {
	private JFrame frmRotasmonster;
	private AdministradorController controller = new AdministradorController();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmRotasmonster.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRotasmonster = new JFrame();
		frmRotasmonster.setTitle("RotasMonster");
		frmRotasmonster.setBounds(100, 100, 705, 474);
		frmRotasmonster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRotasmonster.getContentPane().setLayout(null);
		
		JButton btnPonto = new JButton("Ponto");
		btnPonto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				 CadastroPonto cadastroPonto = new CadastroPonto();
				 cadastroPonto.janelaCadastro(controller);
			}
		});
		btnPonto.setBounds(10, 36, 89, 23);
		frmRotasmonster.getContentPane().add(btnPonto);
		
		JButton btnAresta = new JButton("Aresta");
		btnAresta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CadastroAresta cadastroAresta = new CadastroAresta();
				cadastroAresta.janelaCadastro(controller);
			}
		});
		btnAresta.setBounds(10, 67, 89, 23);
		frmRotasmonster.getContentPane().add(btnAresta);
		
		JLabel lblCadastrar = new JLabel("Cadastrar:");
		lblCadastrar.setBounds(10, 11, 76, 14);
		frmRotasmonster.getContentPane().add(lblCadastrar);
		
		JLabel lblRemover = new JLabel("Remover:");
		lblRemover.setBounds(10, 101, 76, 14);
		frmRotasmonster.getContentPane().add(lblRemover);
		
		JButton btnPonto_1 = new JButton("Ponto");
		btnPonto_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RemoverPonto removeponto = new RemoverPonto();
				removeponto.janelaRemove(controller);
			}
		});
		btnPonto_1.setBounds(10, 126, 89, 23);
		frmRotasmonster.getContentPane().add(btnPonto_1);
		
		JButton btnAresta_1 = new JButton("Aresta");
		btnAresta_1.setBounds(10, 160, 89, 23);
		frmRotasmonster.getContentPane().add(btnAresta_1);
		
		JLabel lblCalcularRota = new JLabel("Calcular rota:");
		lblCalcularRota.setBounds(10, 194, 81, 14);
		frmRotasmonster.getContentPane().add(lblCalcularRota);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 240, 89, 20);
		frmRotasmonster.getContentPane().add(comboBox);
		
		JLabel lblPontoDePartida = new JLabel("Ponto de partida:");
		lblPontoDePartida.setBounds(10, 215, 89, 14);
		frmRotasmonster.getContentPane().add(lblPontoDePartida);
		
		JLabel lblPontoDeDestino = new JLabel("Ponto de destino:");
		lblPontoDeDestino.setBounds(10, 271, 89, 14);
		frmRotasmonster.getContentPane().add(lblPontoDeDestino);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(10, 296, 89, 20);
		frmRotasmonster.getContentPane().add(comboBox_1);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.setBounds(10, 327, 89, 23);
		frmRotasmonster.getContentPane().add(btnCalcular);
	}
}
