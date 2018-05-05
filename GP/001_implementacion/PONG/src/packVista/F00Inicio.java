package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class F00Inicio extends JFrame {

	private JPanel contentPane;
	private JButton btnSalir;
	private JButton continuar;
	private JButton btnTaep;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					F00Inicio frame = new F00Inicio();
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
	public F00Inicio() {
		setResizable(false);
		setTitle("PONG TAEP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 559, 385);
		contentPane = new JPanel();
		
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon img = new ImageIcon(F00Inicio.class.getResource("/packImagenes/pong5.jpg"));
		setIconImage(img.getImage());
		setVisible(true);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(F00Inicio.class.getResource("/packImagenes/Titulo.png")));
		
		btnSalir = new JButton("SALIR");
		btnSalir.setBackground(Color.GREEN);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSalir.setActionCommand("salir");
		btnSalir.addActionListener(new Controlador());
		
		JLabel lblPulsaespacioPara = new JLabel("Pulsa la imagen de abajo para continuar...");
		lblPulsaespacioPara.setForeground(Color.WHITE);
		lblPulsaespacioPara.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		continuar = new JButton("");
		continuar.setActionCommand("continuar");
		continuar.addActionListener(new Controlador());
		continuar.setBackground(Color.WHITE);
		continuar.setIcon(new ImageIcon(F00Inicio.class.getResource("/packImagenes/dff7dc51794c142763cdab1301f0174d_icon-iloveimg-resized.png")));
		
		btnTaep = new JButton("TAEP");
		btnTaep.setActionCommand("TAEP");
		btnTaep.addActionListener(new Controlador());
		btnTaep.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnTaep.setBackground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnTaep)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(210)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(continuar, 0, 0, Short.MAX_VALUE)
								.addComponent(btnSalir, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(216, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(131, Short.MAX_VALUE)
					.addComponent(lblPulsaespacioPara)
					.addGap(125))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(46, Short.MAX_VALUE)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 485, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(13)
							.addComponent(lblPulsaespacioPara)
							.addGap(18)
							.addComponent(continuar)
							.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
							.addComponent(btnSalir)
							.addContainerGap())
						.addComponent(btnTaep)))
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
			if (action.equals("salir")) 
			{
				cerrar();
			}
			else if(action.equals("continuar"))
			{
				F01MenuPrincipal ppal = new F01MenuPrincipal();
				dispose();
			}
			else if(action.equals("TAEP"))
			{
				String nl =  System.getProperty("line.separator");
				JOptionPane.showMessageDialog(rootPane, "GRUPO TAEP: "
						+nl+ "Paula de Jaime"
						+nl+ "Edgar Andrés Santa María"
						+nl+ "Edurne López"
						+nl+ "Ana Miranda" 
						+nl+ "¡DISFRUTA EL JUEGO!");
			}
		}
	}
	
	public void cerrar(){
		Object [] opciones ={"Aceptar","Cancelar"};
		int eleccion = JOptionPane.showOptionDialog(rootPane,"¿Seguro que quieres salir?","Mensaje de Confirmacion",
		JOptionPane.YES_NO_OPTION,
		JOptionPane.QUESTION_MESSAGE,null,opciones,"Aceptar");
		if (eleccion == JOptionPane.YES_OPTION)
		{
		System.exit(0);
		}
	}

}
