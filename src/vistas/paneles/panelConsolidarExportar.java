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

public class panelConsolidarExportar extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2601962226526160710L;
	
	private JPanel panelCentral;
	private JPanel panelTitulo;
	private JPanel panelBotones;
	private JPanel panelCombo1;
	private JPanel panelCombo2;
	private JPanel panelInferior;
	
	private JLabel lblTitulo;
	private JLabel lblLogo;
	private JLabel lblCombo1;
	private JLabel lblResultado;
	private JLabel lblCombo2;
	
	private JComboBox<String> cbxInventarios2;
	private JComboBox<String> cbxInventarios1;
	
	private JButton btnExportarSysplus;
	private JButton btnExportar;
	
	private Coordinador miCoordinador;
	
	public panelConsolidarExportar(Coordinador miCoordinador) {
		super();
		this.miCoordinador = miCoordinador;
		iniciarComponenetes();
		try {
			miCoordinador.llenarCombo(cbxInventarios2);
			miCoordinador.llenarCombo(cbxInventarios1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void iniciarComponenetes() {
		
		panelCentral = new JPanel();
		panelBotones = new JPanel();
		panelCombo1 = new JPanel();
		panelTitulo = new JPanel();
		panelInferior = new JPanel();
		panelCombo2 = new JPanel();
		
		lblCombo1 = new JLabel();
		lblCombo2 = new JLabel();
		lblLogo = new JLabel();
		lblResultado = new JLabel();
		lblTitulo = new JLabel();
		
		cbxInventarios2 = new JComboBox<String>();
		cbxInventarios1 = new JComboBox<String>();
		
		btnExportar = new JButton();
		btnExportarSysplus = new JButton(); 
		
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
				panelCentral.add(panelCombo1,BorderLayout.NORTH);
				panelCombo1.setLayout(new FlowLayout(1,50,30));
				{
					panelCombo1.add(lblCombo2);
					lblCombo2.setText("INVENTARIO 1 A CONSOLIDAR");
					lblCombo2.setHorizontalAlignment(SwingConstants.CENTER);
					
					panelCombo1.add(cbxInventarios1);
					cbxInventarios1.setPreferredSize(new Dimension(300,30));
					
					panelCombo1.add(panelCombo2,BorderLayout.NORTH);
					panelCombo2.setLayout(new FlowLayout(1,50,30));
					{
						panelCombo2.add(lblCombo1);
						lblCombo1.setText("INVENTARIO 2 A CONSOLIDAR");
						lblCombo1.setHorizontalAlignment(SwingConstants.CENTER);
						
						panelCombo2.add(cbxInventarios2);
						cbxInventarios2.setPreferredSize(new Dimension(300,30));
					}
				}
				
				
				
				panelCentral.add(panelBotones,BorderLayout.CENTER);
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
				}
			}
			
			add(panelInferior,BorderLayout.SOUTH);
			panelInferior.setLayout(new FlowLayout(1,20,20));
			{
				panelInferior.add(lblResultado);
				lblResultado.setPreferredSize(new Dimension(500,120));
				lblResultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnExportar) {
			/*try {
				lblResultado.setText(miCoordinador.exportar(cbxInventarios1.getSelectedItem().toString(),cbxInventarios2.getSelectedItem().toString(),1));
			} catch (SQLException | IOException e1) {
				e1.printStackTrace();
			}*/
		}
		
		if(e.getSource() == btnExportarSysplus) {
			/*try {
				lblResultado.setText(miCoordinador.exportar(cbxInventarios1.getSelectedItem().toString(),cbxInventarios2.getSelectedItem().toString(),2));
			} catch (SQLException | IOException e1) {
				e1.printStackTrace();
			}*/
		}
	}
	
	

}
