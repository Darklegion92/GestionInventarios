package vistas.paneles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controladores.Coordinador;

public class panelExportarInventario extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2601962226526160710L;
	
	private JPanel panelCentral;
	private JPanel panelTitulo;
	private JPanel panelBotones;
	private JPanel panelCombo;
	private JPanel panelInferior;
	
	private JLabel lblTitulo;
	private JLabel lblLogo;
	private JLabel lblCombo;
	private JLabel lblResultado;
	
	private JComboBox<String> cbxInventarios;
	
	private JButton btnExportarSysplus;
	private JButton btnExportar;
	private JButton btnConsultarConsolidado;
	 
	private Coordinador miCoordinador;
	
	private boolean ind = false;
	
	public panelExportarInventario(Coordinador miCoordinador) {
		super();
	 	this.miCoordinador = miCoordinador;
	    iniciarComponenetes();
	    try {
	      miCoordinador.llenarCombo(cbxInventarios);
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	}
	
	 public panelExportarInventario(boolean modal, Coordinador miCoordinador){
	    super(modal);
	    this.miCoordinador = miCoordinador;
	    iniciarComponenetes();
	    try {
	      miCoordinador.llenarComboIndividual(cbxInventarios);
	      btnExportar.setVisible(false);
	      btnExportarSysplus.setVisible(false);
	      ind = true;
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	  }
	
	private void iniciarComponenetes() {
		
		panelCentral = new JPanel();
		panelBotones = new JPanel();
		panelCombo = new JPanel();
		panelTitulo = new JPanel();
		panelInferior = new JPanel();
		
		lblCombo = new JLabel();
		lblLogo = new JLabel();
		lblResultado = new JLabel();
		lblTitulo = new JLabel();
		
		cbxInventarios = new JComboBox<String>();
		
		btnExportar = new JButton();
		btnExportarSysplus = new JButton();
		btnConsultarConsolidado = new JButton();
		
		setLayout(new BorderLayout(50,50));
		{
			add(panelTitulo, BorderLayout.NORTH);
			panelTitulo.setLayout(new BorderLayout(20,20));
			{
				panelTitulo.add(lblTitulo,BorderLayout.CENTER);
				lblTitulo.setText("EXPORTAR INVENTARIO");
				lblTitulo.setFont(new java.awt.Font("Decker", 1, 28));
				lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
				
				panelTitulo.add(lblLogo,BorderLayout.EAST);
				lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/img/logo.png")));
			}
			
			add(panelCentral);
			panelCentral.setLayout(new BorderLayout());
			{
				
				
				panelCentral.add(panelCombo,BorderLayout.NORTH);
				panelCombo.setLayout(new FlowLayout(1,50,30));
				{
					panelCombo.add(lblCombo);
					lblCombo.setText("ELIGE UN INVENTARIO A IMPRIMIR");
					lblCombo.setHorizontalAlignment(SwingConstants.CENTER);
					
					panelCombo.add(cbxInventarios);
					cbxInventarios.setPreferredSize(new Dimension(300,30));
				}
				
				panelCentral.add(panelBotones);
				panelBotones.setLayout(new FlowLayout(1,50,40));
				{
					panelBotones.add(btnExportar);
					btnExportar.setText("EXPORTAR ANALISIS");
					btnExportar.setPreferredSize(new Dimension(250,60));
					btnExportar.addActionListener(this);
					
					panelBotones.add(btnExportarSysplus);
					btnExportarSysplus.setText("EXPORTAR SYSPLUS");
					btnExportarSysplus.setPreferredSize(new Dimension(250,60));
					btnExportarSysplus.addActionListener(this);
					
					panelBotones.add(btnConsultarConsolidado);
				    btnConsultarConsolidado.setText("CONSULTAR");
				    btnConsultarConsolidado.setPreferredSize(new Dimension(250, 60));
				    btnConsultarConsolidado.addActionListener(this);
				}
			}
			
			add(panelInferior,BorderLayout.SOUTH);
			panelInferior.setLayout(new FlowLayout(1,20,20));
			{
				panelInferior.add(lblResultado);
				lblResultado.setPreferredSize(new Dimension(500,220));
				lblResultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnExportar) {
			try {
				lblResultado.setText(miCoordinador.exportar(cbxInventarios.getSelectedItem().toString(),1));
			} catch (SQLException | IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == btnExportarSysplus) {
			try {
				lblResultado.setText(miCoordinador.exportar(cbxInventarios.getSelectedItem().toString(),2));
			} catch (SQLException | IOException e1) {
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == btnConsultarConsolidado){
	      if (ind) {
	        miCoordinador.crearPresta�aConsultarIndividual(cbxInventarios.getSelectedItem().toString().split("-"));
	      } else {
	        miCoordinador.crearPresta�aConsultarConsolidado(cbxInventarios.getSelectedItem().toString().split("-"));
	      }
	    }
	}
	
	

}
