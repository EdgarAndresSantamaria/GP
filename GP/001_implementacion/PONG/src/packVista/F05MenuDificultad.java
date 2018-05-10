package packVista;

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
		setResizable(false);
		setTitle("PONG TAEP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 358);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon img = new ImageIcon(F00Inicio.class.getResource("/packImagenes/pong5.jpg"));
		setIconImage(img.getImage());
		setVisible(true);
		
		JLabel lblEligeLaDificultad = new JLabel("Elige la Dificultad");
		lblEligeLaDificultad.setForeground(Color.GREEN);
		lblEligeLaDificultad.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
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
					.addGap(136)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnDifcil, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnFcil, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblEligeLaDificultad, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(155))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblEligeLaDificultad))
						.addComponent(btnVolver))
					.addGap(71)
					.addComponent(btnFcil, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addComponent(btnDifcil, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(64, Short.MAX_VALUE))
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
				
				Pong.getPong().setOponente("IA facil");
				dispose();
				F07Pong miclase = new F07Pong(new Rectangle(600,600));
				Thread elHilo = new Thread(miclase);
				elHilo.start();
			}
			else if (action.equals("dificil"))
			{
				//Dificultad Dificil
				Pong.getPong().setOponente("IA dificil");
				dispose();
				F07Pong miclase = new F07Pong(new Rectangle(600,600));
				Thread elHilo = new Thread(miclase);
				elHilo.start();
			}
			else if (action.equals("volver"))
			{
				dispose();
				if(Pong.getPong().esInvitadoJugador1())
					new F04MenuJuego(true);
				}else {
					new F04MenuJuego(false);
				}
		}
	}

}
