package vistas.paneles;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import controladores.Coordinador;

public class panelCargarInventario extends JPanel implements ActionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3501917153626542549L;
	private JPanel panelTitulo;
	private JPanel panelSuperiorCentro;
	private JPanel panelAgregar;
	private JPanel panelRuta;
	private JPanel panelCentro;
	private JPanel panelCentroCentro;
	private JPanel panelLogo;
	private JPanel panelNombre;

	private JLabel lblTitulo;
	private JLabel lblRuta;
	private JLabel lblProgreso;
	private JLabel lblNombreArchivo;
	private JLabel lblNombreRuta;
	private JLabel lblLogo;
	private JLabel lblNombre;

	private JButton btnArchivo;
	private JButton btnCargar;

	private JTextField txtNombre;

	 private JProgressBar pEstado;
	
	private Coordinador miCoordinador;

	 public panelCargarInventario(Coordinador miCoordinador){
		super();
	    this.miCoordinador = miCoordinador;
	    iniciarComponentes();
	  }

	private void iniciarComponentes() {

		panelTitulo = new JPanel();
		panelSuperiorCentro = new JPanel();
		panelAgregar = new JPanel();
		panelRuta = new JPanel();
		panelCentro = new JPanel();
		panelCentroCentro = new JPanel();
		panelLogo = new JPanel();
		panelNombre = new JPanel();

		lblTitulo = new JLabel();
		lblRuta = new JLabel();
		lblProgreso = new JLabel();
		lblNombreArchivo = new JLabel();
		lblNombreRuta = new JLabel();
		lblLogo = new JLabel();
		lblNombre = new JLabel();

		btnArchivo = new JButton();
		btnCargar = new JButton();

		txtNombre = new JTextField();
		
		pEstado = new JProgressBar();

		setLayout(new BorderLayout());
		{
			add(panelTitulo, BorderLayout.NORTH);
			panelTitulo.setLayout(new FlowLayout(1, 0, 20));
			{
				panelTitulo.add(lblTitulo);
				lblTitulo.setText("CARGAR INVENTARIO INICIAL");
				lblTitulo.setFont(new java.awt.Font("Decker", 1, 28));
			}

			add(panelCentro, BorderLayout.CENTER);
			panelCentro.setBackground(Color.white);
			panelCentro.setLayout(new BorderLayout(30, 0));
			{
				panelCentro.add(panelSuperiorCentro, BorderLayout.NORTH);
				panelSuperiorCentro.setLayout(new FlowLayout(0, 30, 40));
				{
					panelSuperiorCentro.add(panelAgregar);
					panelAgregar.setLayout(new BorderLayout(0, 10));
					{
						panelAgregar.add(lblNombreArchivo, BorderLayout.NORTH);
						lblNombreArchivo.setText("Subir Archivo Inventario");

						panelAgregar.add(btnArchivo, BorderLayout.CENTER);
						btnArchivo.setText("SUBIR INVENTARIO");
						btnArchivo.addActionListener(this);
					}

					panelSuperiorCentro.add(panelRuta);
					panelRuta.setLayout(new BorderLayout(0, 20));
					{
						panelRuta.add(lblNombreRuta, BorderLayout.NORTH);
						lblNombreRuta.setText("Localización del Archivo: ");

						panelRuta.add(lblRuta, BorderLayout.CENTER);
						lblRuta.setPreferredSize(new Dimension(150, 20));
					
					}

					panelSuperiorCentro.add(panelNombre);
					panelNombre.setLayout(new BorderLayout(0, 15));
					{
						panelNombre.add(lblNombre, BorderLayout.NORTH);
						lblNombre.setText("Nombre Inventario Inicial");

						panelNombre.add(txtNombre, BorderLayout.CENTER);
						txtNombre.setPreferredSize(new Dimension(150, 20));
						txtNombre.addKeyListener(this);

					}
					panelSuperiorCentro.add(panelLogo);
					panelLogo.setLayout(new FlowLayout(1, 120, 0));
					{
						panelLogo.add(btnCargar);
						btnCargar.setText("CARGAR DATOS");
						btnCargar.addActionListener(this);
						btnCargar.setEnabled(false);

						panelLogo.add(lblLogo);
						lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/img/logo.png")));
					}

				}

				panelCentro.add(panelCentroCentro);
				panelCentroCentro.setLayout(new FlowLayout(1, 500, 100));
				{
					panelCentroCentro.add(pEstado, "Center");
				    pEstado.setPreferredSize(new Dimension(400, 50));
				    
				    panelCentroCentro.add(lblProgreso, "Center");
				    lblProgreso.setPreferredSize(new Dimension(500, 100));
				    lblProgreso.setHorizontalAlignment(0);

				}
			}
		}
	}

//////////////////////////////////Eventos//////////////////////////////////
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnArchivo) {
			btnCargar.setEnabled(false);
			lblRuta.setText(miCoordinador.cargarArchivo());
			if(lblRuta.getText().isEmpty()) {
				btnCargar.requestFocus();
			}else {
				txtNombre.requestFocus();
				btnCargar.setEnabled(true);
			}
			
		}

		if (e.getSource() == btnCargar) {
			if (txtNombre.getText().isEmpty()) {
		        miCoordinador.AlertaError("El Campo Nombre", "No Puede Estar Vacío");
		        txtNombre.requestFocus();
		      }
		      else if (miCoordinador.AlertaConfirmar("Esta Seguro Que Desea", "¿Cargar Inventario?")){
		        try {
		          miCoordinador.almacenarArchivo(pEstado, lblProgreso, lblRuta.getText(), txtNombre.getText(), lblRuta, this, txtNombre, btnCargar, btnArchivo);
				}   catch (Exception e1){
		          JOptionPane.showMessageDialog(this, e1.getMessage());
		          setCursor(new Cursor(0));
		        }
		      }
		    }

	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		miCoordinador.mayusculas(e);

	}

}
