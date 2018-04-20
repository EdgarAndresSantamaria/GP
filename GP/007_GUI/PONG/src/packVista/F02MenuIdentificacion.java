package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;

public class F02MenuIdentificacion extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JButton btnVolver;
	private JButton btnLogin;
	private boolean invitado = true;
	
	/**
	 * Create the frame.
	 */
	public F02MenuIdentificacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setForeground(Color.GREEN);
		
		JLabel lblContraseña = new JLabel("Contrase\u00F1a:");
		lblContraseña.setForeground(Color.GREEN);
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		passwordField = new JPasswordField();
		
		textField = new JTextField();
		textField.setColumns(10);
		
		btnVolver = new JButton("Volver");
		btnVolver.setActionCommand("volver");
		btnVolver.addActionListener(new Controlador());
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setForeground(new Color(0, 0, 0));
		
		btnLogin = new JButton("Login");
		btnLogin.setActionCommand("login");
		btnLogin.addActionListener(new Controlador());
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 15));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(57)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblContraseña, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsuario))
							.addGap(33)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField)
								.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
								.addComponent(btnLogin)))
						.addComponent(btnVolver))
					.addContainerGap(112, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnVolver)
					.addGap(57)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUsuario)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContraseña, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
					.addComponent(btnLogin)
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
			if (action.equals("volver")) 
			{
				F01MenuPrincipal ppal = new F01MenuPrincipal();
				dispose();
			}
			else if (action.equals("login"))
			{
				String user = textField.getText();
				String password = passwordField.getText();
				/*if(user == usuarioBD && password == passwordBD del usuario){
					JOptionPane.showMessageDialog(rootPane, "BIENVENIDO  "+user);
					F04MenuJuego jugar = new F04MenuJuego();
					invitado = false;
					dispose();
				  else if(user == usuarioBD && password != passwordBD del usuario){
				  	JOptionPane.showMessageDialog(rootPane, "La contraseña no es correcta");
				  else
				  	JOptionPane.showMessageDialog(rootPane, "Usuario no registrado, por favor REGISTRATE");
				*/
			}
		}
	}
	
	public boolean getInvitado()
	{
		return invitado;
	}
}
