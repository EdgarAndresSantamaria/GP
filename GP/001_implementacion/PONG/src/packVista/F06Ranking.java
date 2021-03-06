package packVista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import javax.swing.LayoutStyle.ComponentPlacement;

public class F06Ranking extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private boolean logueado = false;
	private JButton btnVolver;
	private DefaultTableModel model;


	/**
	 * Create the frame.
	 * @throws JSONException 
	 */
	public F06Ranking(JSONArray pDatos, boolean logueado) throws JSONException {
		setResizable(false);
		setTitle("PONG TAEP");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 569, 374);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		ImageIcon img = new ImageIcon(F00Inicio.class.getResource("/packImagenes/pong5.jpg"));
		setIconImage(img.getImage());
		setVisible(true);
		
		this.logueado = logueado;
		
		JScrollPane scrollPane = new JScrollPane();
		
		btnVolver = new JButton("Volver");
		btnVolver.setActionCommand("volver");
		btnVolver.addActionListener(new Controlador());
		btnVolver.setBackground(Color.WHITE);
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblRanking = new JLabel("RANKING");
		lblRanking.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRanking.setForeground(Color.GREEN);
		lblRanking.setBackground(Color.BLACK);
		
		JLabel lblLink = new JLabel("Link1");
		lblLink.setForeground(Color.WHITE);
		
		JLabel lblLink_1 = new JLabel("Link2");
		lblLink_1.setForeground(Color.WHITE);
		
		JLabel lblLink_2 = new JLabel("Link3");
		lblLink_2.setForeground(Color.WHITE);
		
		JLabel lblLink_3 = new JLabel("Link4");
		lblLink_3.setForeground(Color.WHITE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnVolver)
							.addGap(140)
							.addComponent(lblRanking))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblLink)
									.addGap(130)
									.addComponent(lblLink_1, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 178, Short.MAX_VALUE)
									.addComponent(lblLink_2, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
									.addGap(127)
									.addComponent(lblLink_3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnVolver)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblRanking)))
							.addGap(3)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblLink, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLink_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblLink_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblLink_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		model = new DefaultTableModel() {
			@Override 
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		
		//A�adir las columnas de la tabla
		model.addColumn("Player1");
		model.addColumn("Scored1");
		model.addColumn("Scored2");
		model.addColumn("Player2");

		//A�adir las filas a la tabla desde el fichero JSON
		for (int i = 0; i < pDatos.length(); i++) {
			JSONObject one = pDatos.getJSONObject(i);
			model.addRow(new Object[]{one.get("jugador1"), one.get("puntuacion1"), one.get("puntuacion2"), one.get("jugador2")});
		}
		table = new JTable(model);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setAutoCreateRowSorter(true);
		table.setShowVerticalLines(false);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		table.setCellSelectionEnabled(true);
		table.setPreferredScrollableViewportSize(new Dimension(450,63));
		table.setFillsViewportHeight(true);		
				
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		   tcr.setHorizontalAlignment(SwingConstants.CENTER);
		table.setModel(model);
		   table.getColumnModel().getColumn(0).setCellRenderer(tcr);
		   table.getColumnModel().getColumn(1).setCellRenderer(tcr);
		   table.getColumnModel().getColumn(2).setCellRenderer(tcr);
		   table.getColumnModel().getColumn(3).setCellRenderer(tcr);
		
		scrollPane.setViewportView(table);
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
				//para probar cuando esta logueado
				//logueado = true;
	
				if(logueado)
				{
					F04MenuJuego juego = new F04MenuJuego(false);
					dispose();
				}
				else
				{
					F01MenuPrincipal ppal = new F01MenuPrincipal();
					ppal.setVisible(true);
					dispose();
				}
			}
		}
	}
}
