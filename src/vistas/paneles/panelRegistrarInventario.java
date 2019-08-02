package vistas.paneles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controladores.Coordinador;
import modelo.Vo.InventarioInicialVo;
import recursos.componentes.modeloTablasEditable;

public class panelRegistrarInventario extends JPanel implements KeyListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 162562414709289859L;
	
	
	private JPanel panelTitulo;
	private JPanel panelCentro;
	private JPanel panelCentroSuperior;
	private JPanel panelInventarioInicial;
	private JPanel panelCombo;
	private JPanel panelTitulos;
	private JPanel panelFunciones;
	private JPanel panelInferior;
	
	private JLabel lblTitulo;
	private JLabel lblInventarioIncial;
	private JLabel lblCodigo;
	private JLabel lblBarras;
	private JLabel lblDescripcion;
	private JLabel lblCantidad;
	private JLabel lblCosto;
	private JLabel lblTotal;
	
	private JTextField txtCodigo;
	private JTextField txtBarras;
	private JTextField txtDescripcion;
	private JTextField txtCantidad;
	private JTextField txtCosto;
	private JTextField txtTotal;
	
	private JComboBox<String> cbxInventarioIncial;
	
	private JScrollPane jsTabla;
	
	private JTable tabla;
	
	private DefaultTableModel modelo;
	
	private JButton btnGuardar;
	private JButton btnCancelar;
	
	private Coordinador miCoordinador;
	
	private boolean[] columnasEditables;
	@SuppressWarnings("rawtypes")
	private Class[] columnTypes;	
	
	private InventarioInicialVo miInventario = null;
	
	public panelRegistrarInventario(Coordinador miCoordinador) {
		super();
		this.miCoordinador = miCoordinador;
		iniciarComponentes();
		try {
			miCoordinador.llenarCombo(cbxInventarioIncial);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void iniciarComponentes() {
		
		panelTitulo = new JPanel();
		panelCentro = new JPanel();
		panelCentroSuperior = new JPanel();
		panelInventarioInicial = new JPanel();
		panelCombo = new JPanel();
		panelTitulos = new JPanel();
		panelFunciones = new JPanel();
		panelInferior = new JPanel();
		
		lblTitulo = new JLabel();
		lblInventarioIncial = new JLabel();
		lblBarras= new JLabel();
		lblCantidad = new JLabel();
		lblCodigo = new JLabel();
		lblCosto = new JLabel();
		lblDescripcion = new JLabel();
		lblTotal = new JLabel();
		
		txtCodigo = new JTextField();
		txtBarras = new JTextField();
		txtCantidad = new JTextField();
		txtCosto = new JTextField();
		txtDescripcion = new JTextField();
		txtTotal = new JTextField();
		
		cbxInventarioIncial = new JComboBox<String>();
		
		jsTabla = new JScrollPane();
		
		tabla = new JTable();
		
		modelo = new DefaultTableModel();
		
		btnCancelar = new JButton();
		btnGuardar = new JButton();
		
		setLayout(new BorderLayout());
		{
			add(panelTitulo, BorderLayout.NORTH);
			panelTitulo.setLayout(new FlowLayout(1, 0, 20));
			{
				panelTitulo.add(lblTitulo);
				lblTitulo.setText("REGISTRO INVENTARIO");
				lblTitulo.setFont(new Font("Decker", 1, 20));
			}
			add(panelCentro, BorderLayout.CENTER);
			panelCentro.setLayout(new BorderLayout());
			{
				panelCentro.add(panelCentroSuperior,BorderLayout.NORTH);
				panelCentroSuperior.setLayout(new BorderLayout(0,5));
				{
					panelCentroSuperior.add(panelInventarioInicial,BorderLayout.NORTH);
					panelInventarioInicial.setLayout(new BorderLayout());
					{
						panelInventarioInicial.add(lblInventarioIncial,BorderLayout.NORTH);
						lblInventarioIncial.setText("INVENTARIO INCIAL");
						lblInventarioIncial.setHorizontalAlignment(SwingConstants.CENTER);
						
						panelInventarioInicial.add(panelCombo);
						panelCombo.setLayout(new FlowLayout(1));
						{
							panelCombo.add(cbxInventarioIncial,BorderLayout.CENTER);
							cbxInventarioIncial.setPreferredSize(new Dimension(500,25));
						}
					}
					
					panelCentroSuperior.add(panelTitulos,BorderLayout.CENTER);
					panelTitulos.setLayout(new FlowLayout(1,25,0));
					{
						panelTitulos.add(lblCodigo);
						lblCodigo.setPreferredSize(new Dimension(150,20));
						lblCodigo.setText("Código");
						lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
						
						panelTitulos.add(lblBarras);
						lblBarras.setPreferredSize(new Dimension(150,20));
						lblBarras.setText("Código Barras");
						lblBarras.setHorizontalAlignment(SwingConstants.CENTER);
						
						panelTitulos.add(lblDescripcion);
						lblDescripcion.setPreferredSize(new Dimension(400,20));
						lblDescripcion.setText("Descripción");
						lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
						
						panelTitulos.add(lblCantidad);
						lblCantidad.setPreferredSize(new Dimension(150,20));
						lblCantidad.setText("Cantidad");
						lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
						
						panelTitulos.add(lblCosto);
						lblCosto.setPreferredSize(new Dimension(150,20));
						lblCosto.setText("Costo");
						lblCosto.setHorizontalAlignment(SwingConstants.CENTER);
					}
					
					panelCentroSuperior.add(panelFunciones,BorderLayout.SOUTH);
					panelFunciones.setLayout(new FlowLayout(1,25,20));
					{
						panelFunciones.add(txtCodigo);
						txtCodigo.setPreferredSize(new Dimension(150,20));
						txtCodigo.requestFocus();
						txtCodigo.addKeyListener(this);
						
						panelFunciones.add(txtBarras);
						txtBarras.setPreferredSize(new Dimension(150,20));
						txtBarras.setEditable(false);
						
						panelFunciones.add(txtDescripcion);
						txtDescripcion.setPreferredSize(new Dimension(400,20));
						txtDescripcion.setEditable(false);
						
						panelFunciones.add(txtCantidad);
						txtCantidad.setPreferredSize(new Dimension(150,20));
						txtCantidad.setEditable(false);
						txtCantidad.addKeyListener(this);
						
						panelFunciones.add(txtCosto);
						txtCosto.setPreferredSize(new Dimension(150,20));
						txtCosto.setEditable(false);
					}
						
				}
				
				panelCentro.add(jsTabla,BorderLayout.CENTER);
				{
					
						jsTabla.setViewportView(tabla);
						jsTabla.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
	    				columnasEditables = new boolean[]{false,false,false,false,false,false,false};
	    				columnTypes = new Class[] {String.class,String.class,Double.class,Double.class,Double.class,Double.class,Double.class};
	    				modelo = new  modeloTablasEditable(columnasEditables,columnTypes);
	    				modelo.addColumn("CODIGO");
	    				modelo.addColumn("DESCRIPCION");
	    				modelo.addColumn("INV. INICIAL");
	    				modelo.addColumn("INV. NUEVO");
	    				modelo.addColumn("AJUSTE TOTAL");
	    				modelo.addColumn("COST. UNIDAD");
	    				modelo.addColumn("TOTAL");		    				
	    				tabla.setModel(modelo);
				}
		
			}
			
			add(panelInferior,BorderLayout.SOUTH);
			panelInferior.setLayout(new FlowLayout(1,200,5));
			{
				panelInferior.add(btnGuardar);
				btnGuardar.setText("GUARDAR");
				btnGuardar.addActionListener(this);;
				
				panelInferior.add(btnCancelar);
				btnCancelar.setText("CANCELAR");
				btnCancelar.addActionListener(this);
				
				panelInferior.add(lblTotal);
				lblTotal.setText("TOTAL");
				
				panelInferior.add(txtTotal);
				txtTotal.setPreferredSize(new Dimension(150,20));
				txtTotal.setEditable(false);
			}
		
		}
	
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getSource() == txtCodigo) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				String[] id = cbxInventarioIncial.getSelectedItem().toString().split("-");
				try {
					miInventario= miCoordinador.consultar(txtCodigo.getText(),id[0]);
					if(!miInventario.getBarras().isEmpty()) {
						txtCodigo.setText(miInventario.getCodigo());
						txtBarras.setText(miInventario.getBarras());
						txtDescripcion.setText(miInventario.getDescripcion());
						txtCantidad.setText("1");
						if(miInventario.getCosto()<0) {
							miInventario.setCosto(0.0);
						}
						txtCosto.setText(miCoordinador.formatoNumero(miInventario.getCosto(),2));
						txtCantidad.setEditable(true);
						txtCantidad.requestFocus();
						txtCantidad.selectAll();
						cbxInventarioIncial.setEnabled(false);
						txtCodigo.setEditable(false);
					}
				} catch (SQLException | NullPointerException e1) {
					miCoordinador.AlertaError("El Código No Existe", "Revise EL Inventario Inicial");
					txtCodigo.selectAll();
				}
			}
		}
		if(e.getSource() == txtCantidad) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				int i = miCoordinador.validarCodigo(tabla,txtCodigo.getText());
				System.err.println(i);
				if(i>=1) {
					sumarDato(i);
				}else {
					agregarTabla();
				}
				
				miCoordinador.totalTabla(tabla,txtTotal);
			}
		}	
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getSource() == txtCantidad) {
			if(txtCantidad.getText().length()>0) {
				Double costo = Integer.parseInt(txtCantidad.getText())*miInventario.getCosto();
				txtCosto.setText(miCoordinador.formatoNumero(costo,0));
			}else {
				txtCosto.setText("0");
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getSource() == txtCantidad) {
			miCoordinador.soloNumeros(txtCantidad);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnGuardar) {
			try {
				miCoordinador.guardarInventarioNuevo(tabla,cbxInventarioIncial.getSelectedItem().toString());
				miCoordinador.AlertaCorrecto("Inventario", "Guardado Correctamente");
				miCoordinador.limpiarTabla(tabla);
				cbxInventarioIncial.setEnabled(true);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		if(e.getSource() == btnCancelar) {
			miCoordinador.cerrarPestaña();
		}
		
	}

	
	private void agregarTabla() {
		modelo = (DefaultTableModel) tabla.getModel();
		
		Double ajuste = miCoordinador.StringDouble(txtCantidad.getText()) - miInventario.getCantidad();
		modelo.addRow(new Object[] {
				txtCodigo.getText(),
				txtDescripcion.getText(),
				miInventario.getCantidad(),
				miCoordinador.StringDouble(txtCantidad.getText()),
				ajuste,
				miInventario.getCosto(),
				miInventario.getCosto()*ajuste
		});
		estadoIncial();
	}

	private void estadoIncial() {
		txtCodigo.setEditable(true);
		txtCodigo.setText("");
		txtBarras.setText("");
		txtDescripcion.setText("");
		txtCantidad.setText("");
		txtCantidad.setEditable(false);
		txtCosto.setText("");
		txtCodigo.requestFocus();
		
	}
	
	private void sumarDato(int i) {
		Double cantidad = miCoordinador.StringDouble(tabla.getValueAt(i-1, 3).toString());
		modelo = (DefaultTableModel) tabla.getModel();
		modelo.removeRow(i-1);
		cantidad = cantidad + miCoordinador.StringDouble(txtCantidad.getText());
		txtCantidad.setText(miCoordinador.formatoNumero(cantidad, 2));
		agregarTabla();
	}
	
}
