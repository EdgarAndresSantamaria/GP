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
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

public class F03Registro extends JFrame {

	private JPanel contentPane;
	private JTextField usuario;
	private JPasswordField Contraseña;
	private JPasswordField RepiteContraseña;
	private JButton btnRegistrarse;
	private JButton btnVolver;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					F03Registro frame = new F03Registro();
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
	public F03Registro() {
		setResizable(false);
		setTitle("PONG TAEP");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 573, 419);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon img = new ImageIcon(F00Inicio.class.getResource("/packImagenes/pong5.jpg"));
		setIconImage(img.getImage());
		setVisible(true);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombreDeUsuario.setForeground(Color.GREEN);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContrasea.setForeground(Color.GREEN);
		
		JLabel lblRepiteLaContrasea = new JLabel("Repite la contrase\u00F1a:");
		lblRepiteLaContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRepiteLaContrasea.setForeground(Color.GREEN);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBackground(Color.WHITE);
		btnRegistrarse.setActionCommand("registrar");
		btnRegistrarse.addActionListener(new Controlador());
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblRegistroDeUsuario = new JLabel("REGISTRO DE USUARIO");
		lblRegistroDeUsuario.setForeground(Color.GREEN);
		lblRegistroDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		usuario = new JTextField();
		usuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usuario.setColumns(10);
		
		Contraseña = new JPasswordField();
		Contraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		RepiteContraseña = new JPasswordField();
		RepiteContraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnVolver = new JButton("Volver");
		btnVolver.setActionCommand("volver");
		btnVolver.addActionListener(new Controlador());
		btnVolver.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnVolver)
							.addPreferredGap(ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
							.addComponent(lblRegistroDeUsuario))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap(0, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNombreDeUsuario)
								.addComponent(lblContrasea)
								.addComponent(lblRepiteLaContrasea))
							.addGap(27)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(RepiteContraseña)
								.addComponent(Contraseña)
								.addComponent(usuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addGap(134))
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(105, Short.MAX_VALUE)
					.addComponent(btnRegistrarse)
					.addGap(208))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRegistroDeUsuario)
					.addGap(64)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombreDeUsuario)
						.addComponent(usuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrasea)
						.addComponent(Contraseña, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRepiteLaContrasea)
						.addComponent(RepiteContraseña, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
					.addComponent(btnRegistrarse)
					.addGap(24))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnVolver)
					.addContainerGap(337, Short.MAX_VALUE))
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
	
	private class Controlador extends WindowAdapter implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String action = e.getActionCommand();
			
			if (action.equals("registrar")) 
			{
				String username = usuario.getText();
				String pass1 = Contraseña.getText();
				String pass2 = RepiteContraseña.getText();
				if(!pass1.equals(pass2))
				{
					JOptionPane.showMessageDialog(rootPane, "Las contraseñas no coinciden");
				}
				else
				{
					//almacenar en la BD el usuario y la contraseña
					Boolean registroOK = Pong.getPong().registroUsuario(username, pass1);
					if(registroOK) {
						dispose();
						new F02MenuIdentificacion();
					}else {
						JOptionPane.showMessageDialog(rootPane, "Error en el registro.");
					}
				}
			}
			else if (action.equals("volver"))
			{
				F01MenuPrincipal ppal = new F01MenuPrincipal();
				ppal.setVisible(true);
				dispose();
			}
		}
	}
}
