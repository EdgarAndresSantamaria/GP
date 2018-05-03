package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packModelo.Pong;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;

public class F05MenuDificultad extends JFrame {

	private JPanel contentPane;
	private JButton btnFcil;
	private JButton btnDifcil;
	private JButton btnVolver;

	/**
	 * Create the frame.
	 */
	public F05MenuDificultad() {
		setTitle("PONG TAEP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 358);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setVisible(true);
		
		JLabel lblEligeLaDificultad = new JLabel("Elige la Dificultad");
		lblEligeLaDificultad.setForeground(Color.GREEN);
		lblEligeLaDificultad.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		btnFcil = new JButton("F\u00C1CIL");
		btnFcil.setBackground(Color.GREEN);
		btnFcil.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFcil.setActionCommand("facil");
		btnFcil.addActionListener(new Controlador());
		
		
		btnDifcil = new JButton("DIF\u00CDCIL");
		btnDifcil.setBackground(Color.RED);
		btnDifcil.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDifcil.setActionCommand("dificil");
		btnDifcil.addActionListener(new Controlador());
		
		btnVolver = new JButton("Volver");
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setActionCommand("volver");
		btnVolver.addActionListener(new Controlador());
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnVolver)
					.addGap(111)
					.addComponent(lblEligeLaDificultad)
					.addGap(180))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(195)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnDifcil, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
						.addComponent(btnFcil, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
					.addGap(197))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblEligeLaDificultad)
							.addGap(39)
							.addComponent(btnFcil, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addGap(42)
							.addComponent(btnDifcil, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnVolver))
					.addContainerGap(106, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		// Codigo para centrar el frame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);

		
	}
	
	private class Controlador extends WindowAdapter implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			if (action.equals("facil")) 
			{
				//Dificultad Facil
				
				Pong.getPong().setConfig("JugadorPersona", new Rectangle (0, 0, getWidth(), getHeight() / 2));
				
				F07Pong pong = F07Pong.getPong();
				dispose();
			}
			else if (action.equals("dificil"))
			{
				//Dificultad Dificil
				
				Pong.getPong().setConfig("JugadorPersona", new Rectangle (0, 0, getWidth(), getHeight() / 2));

				F07Pong pong = F07Pong.getPong();
				dispose();
			}
			else if (action.equals("volver"))
			{
				dispose();
				F02MenuIdentificacion id = new F02MenuIdentificacion();
				id.dispose();
				boolean pInvitado = id.getInvitado();
				F04MenuJuego menu = new F04MenuJuego(pInvitado);
			}
		}
	}

}
