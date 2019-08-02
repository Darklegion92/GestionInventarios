package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import controladores.Coordinador;

public class VentanaPrincipal extends JFrame implements WindowListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3980504299387481751L;
	

	private JMenuBar barraMenu;
	private JMenu menuConfiguraciones;
	private JMenu menuInventarios;

	private JMenuItem itemImportarInventario;
	private JMenuItem itemNuevoInventario;
	private JMenuItem itemExportarInventario;

	private JTabbedPane gestorVentanas;

	private Coordinador miCoordinador;
	
	

	public VentanaPrincipal() {
		super();
		iniciarComponentes();

	}

	private void iniciarComponentes() {
		
		barraMenu = new JMenuBar();
		menuConfiguraciones = new JMenu();
		menuInventarios = new JMenu();

		itemImportarInventario = new JMenuItem();
		itemNuevoInventario = new JMenuItem();
		itemExportarInventario = new JMenuItem();

		gestorVentanas = new JTabbedPane();
		
		setTitle("SOLTEC - Generador De Inventarios");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBackground(Color.CYAN);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource("/recursos/img/logoVentana.png")).getImage());
		setResizable(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(this);
		setLayout(new BorderLayout());
	
		{
			setJMenuBar(barraMenu);
			{
				barraMenu.add(menuConfiguraciones);
				menuConfiguraciones.setText("Configuraciones");
				{
					menuConfiguraciones.add(itemImportarInventario);
					itemImportarInventario.setText("Importar Inventario");
					itemImportarInventario.addActionListener(this);
				}

				barraMenu.add(menuInventarios);
				menuInventarios.setText("Inventarios");
				{
					menuInventarios.add(itemNuevoInventario);
					itemNuevoInventario.setText("Nuevo");
					itemNuevoInventario.addActionListener(this);

					menuInventarios.add(itemExportarInventario);
					itemExportarInventario.setText("Exportar");
					itemExportarInventario.addActionListener(this);
				}
			}
		}
		add(gestorVentanas);		
		pack();
	}

////////////////////////////////////Eventos///////////////////////////////
	@Override
	public void windowActivated(WindowEvent e) {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		if(miCoordinador.AlertaSalir("¿Estas Seguro De Salir ?","")) {
			System.exit(0);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == itemImportarInventario) {
			miCoordinador.crearPestanaCargarInventario(gestorVentanas);
		}
		
		if(e.getSource() == itemNuevoInventario) {
			miCoordinador.crearPestanaNuevoInventario(gestorVentanas);
		}
		
		if(e.getSource() == itemExportarInventario) {
			miCoordinador.crearPestanaExportarInventario(gestorVentanas);
		}

	}
	
	
	
/////////////////////////////////Metodos Privados//////////////////////////////////

	
////////////////////////////////Metodos Publicos//////////////////////////////	
	
	
	
	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	public JTabbedPane getGestorVentanas() {
		return gestorVentanas;
	}

	public void setGestorVentanas(JTabbedPane gestorVentanas) {
		this.gestorVentanas = gestorVentanas;
	}

}
