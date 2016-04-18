package br.uefs.ecomp.rotasMonster.view;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Canvas;

public class GUIApplet extends JApplet{
	public GUIApplet() {
		getContentPane().setLayout(null);
		
		JButton btnPonto = new JButton("Ponto");
		btnPonto.setBounds(10, 33, 89, 23);
		getContentPane().add(btnPonto);
		
		JButton btnAresta = new JButton("Aresta");
		btnAresta.setBounds(10, 61, 89, 23);
		getContentPane().add(btnAresta);
		
		JLabel lblCadastrar = new JLabel("Cadastrar");
		lblCadastrar.setBounds(10, 11, 69, 14);
		getContentPane().add(lblCadastrar);
		
		JLabel lblRemover = new JLabel("Remover");
		lblRemover.setBounds(10, 95, 46, 14);
		getContentPane().add(lblRemover);
		
		JButton btnPonto_1 = new JButton("Ponto");
		btnPonto_1.setBounds(10, 113, 89, 23);
		getContentPane().add(btnPonto_1);
		
		JButton btnAresta_1 = new JButton("Aresta");
		btnAresta_1.setBounds(10, 140, 89, 23);
		getContentPane().add(btnAresta_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 221, 89, 20);
		getContentPane().add(comboBox);
		
		JLabel lblClculoDeRota = new JLabel("C\u00E1lculo de Rota");
		lblClculoDeRota.setBounds(10, 174, 83, 14);
		getContentPane().add(lblClculoDeRota);
		
		JLabel lblPontoDePartida = new JLabel("Ponto de Partida");
		lblPontoDePartida.setBounds(10, 199, 89, 14);
		getContentPane().add(lblPontoDePartida);
		
		JLabel lblPontoDeDestino = new JLabel("Ponto de Destino");
		lblPontoDeDestino.setBounds(10, 247, 89, 14);
		getContentPane().add(lblPontoDeDestino);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(10, 269, 89, 20);
		getContentPane().add(comboBox_1);
	}
}

