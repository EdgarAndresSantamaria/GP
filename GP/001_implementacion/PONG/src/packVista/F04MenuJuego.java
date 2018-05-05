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
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import javax.swing.LayoutStyle.ComponentPlacement;

public class F04MenuJuego extends JFrame {

	private JPanel contentPane;
	private JButton btnJugarVsIa;
	private JButton btnNewButton;
	private JButton btnRanking;
	private JButton btnLogout;
	private JLabel lblganaQuienMarque;
	private JLabel label;
	private JLabel label_1;
	private boolean invitado;

	/**
	 * Create the frame.
	 */
	public F04MenuJuego(boolean pInvitado) {
		setResizable(false);
		invitado = pInvitado;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 722, 473);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon img = new ImageIcon(F00Inicio.class.getResource("/packImagenes/pong5.jpg"));
		setIconImage(img.getImage());
		setVisible(true);
		
		JLabel lblPongTaep = new JLabel("");
		lblPongTaep.setIcon(new ImageIcon(F04MenuJuego.class.getResource("/packImagenes/Titulo.png")));
		lblPongTaep.setForeground(new Color(0, 255, 0));
		lblPongTaep.setFont(new Font("Tahoma", Font.BOLD, 25));
		
		btnJugarVsIa = new JButton("Jugar VS IA");
		btnJugarVsIa.setBackground(Color.GREEN);
		btnJugarVsIa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnJugarVsIa.setActionCommand("IA");
		btnJugarVsIa.addActionListener(new Controlador());
		
		btnNewButton = new JButton("Jugar VS Player");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setActionCommand("2j");
		btnNewButton.addActionListener(new Controlador());
		
		btnRanking = new JButton("Ranking");
		btnRanking.setBackground(Color.GREEN);
		btnRanking.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRanking.setActionCommand("ranking");
		btnRanking.addActionListener(new Controlador());
		
		btnLogout = new JButton("Logout");
		btnLogout.setActionCommand("logout");
		btnLogout.addActionListener(new Controlador());
		btnLogout.setBackground(Color.WHITE);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		lblganaQuienMarque = new JLabel("*Gana quien marque 22 tantos");
		lblganaQuienMarque.setForeground(Color.RED);
		
		label = new JLabel("*");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setForeground(Color.RED);
		
		label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblganaQuienMarque)
					.addContainerGap(517, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(205)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnJugarVsIa, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
						.addComponent(btnRanking, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 8, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addContainerGap(200, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(124)
					.addComponent(lblPongTaep)
					.addContainerGap(134, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(306)
					.addComponent(btnLogout)
					.addContainerGap(317, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblPongTaep)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(label))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(150)
							.addComponent(btnJugarVsIa, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addGap(49)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)))
					.addGap(35)
					.addComponent(btnRanking, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
					.addComponent(btnLogout)
					.addGap(23)
					.addComponent(lblganaQuienMarque)
					.addContainerGap())
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
			if (action.equals("IA")) 
			{
				Pong.getPong().setConfig("Persona", new Rectangle (0, 0, getWidth(), getHeight() / 2));
				
				F05MenuDificultad dif = new F05MenuDificultad();
				dispose();
			}
			else if (action.equals("2j"))
			{
				//Jugar 2 jugadores
				
				Pong.getPong().setConfig("Persona", new Rectangle (0, 0, getWidth(), getHeight() / 2));
				Pong.getPong().setOponente("2Jugadores");
				
				new F07Pong("Jugador");
				dispose();
			}
			else if(action.equals("ranking"))
			{
				if(invitado)
				{
					JOptionPane.showMessageDialog(contentPane, "Eres un Invitado, no puedes ver el Ranking");
					btnRanking.setEnabled(false);
				}
				else
				{
					F06Ranking ranking = new F06Ranking();
					dispose();
				}
				
			}
			else if (action.equals("logout"))
			{
				//Cerrar Sesion
				F00Inicio inicio = new F00Inicio();
				dispose();
			}
		}
	}
}
