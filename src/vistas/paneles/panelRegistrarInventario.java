package vistas.paneles;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controladores.Coordinador;
import modelo.Vo.InventarioInicialVo;
import modelo.conexiones.ConexionFireBird;
import recursos.componentes.modeloTablasEditable;

public class panelRegistrarInventario extends JPanel implements KeyListener, ActionListener, MouseListener {

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
	private JPanel panelNombre;

	private JLabel lblTitulo;
	private JLabel lblLogo;
	private JLabel lblInventarioIncial;
	private JLabel lblCodigo;
	private JLabel lblBarras;
	private JLabel lblDescripcion;
	private JLabel lblCantidad;
	private JLabel lblCosto;
	private JLabel lblTotal;
	private JLabel lblNombre;
	private JLabel lblMargen;

	private JTextField txtCodigo;
	private JTextField txtBarras;
	private JTextField txtDescripcion;
	private JTextField txtCantidad;
	private JTextField txtCosto;
	private JTextField txtTotal;
	private JTextField txtNombre;

	private JComboBox<String> cbxInventarioIncial;

	private JScrollPane jsTabla;

	private JTable tabla;

	private DefaultTableModel modelo;

	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnEliminar;

	private Coordinador miCoordinador;

	private boolean[] columnasEditables;
	@SuppressWarnings("rawtypes")
	private Class[] columnTypes;

	private boolean crear = false;

	private String familia;
	private String grupo;
	private String subgrupo;
	private InventarioInicialVo miInventario = null;

	public panelRegistrarInventario(Coordinador miCoordinador) {
		super();
		this.miCoordinador = miCoordinador;
		iniciarComponentes();
		try {
			miCoordinador.llenarCombo(cbxInventarioIncial);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}
	}

	public panelRegistrarInventario(Coordinador miCoordinador, String[] inventarioIncial, int tipo) {
		this.miCoordinador = miCoordinador;
		iniciarComponentes();
		if (tipo == 1) {
			miCoordinador.llenarConsolidado(tabla, inventarioIncial[0]);
		} else if (tipo == 2) {
			miCoordinador.llenarIndividual(tabla, inventarioIncial[0]);
		}
		txtCodigo.setEditable(false);
		txtNombre.setEditable(false);
		txtNombre.setText(inventarioIncial[1]);
		btnGuardar.setEnabled(false);
		cbxInventarioIncial.setVisible(false);
		btnEliminar.setEnabled(false);
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
		panelNombre = new JPanel();

		lblTitulo = new JLabel();
		lblInventarioIncial = new JLabel();
		lblBarras = new JLabel();
		lblCantidad = new JLabel();
		lblCodigo = new JLabel();
		lblCosto = new JLabel();
		lblDescripcion = new JLabel();
		lblTotal = new JLabel();
		lblNombre = new JLabel();
		lblMargen = new JLabel();
		lblLogo = new JLabel();

		txtCodigo = new JTextField();
		txtBarras = new JTextField();
		txtCantidad = new JTextField();
		txtCosto = new JTextField();
		txtDescripcion = new JTextField();
		txtTotal = new JTextField();
		txtNombre = new JTextField();

		cbxInventarioIncial = new JComboBox<String>();

		jsTabla = new JScrollPane();

		tabla = new JTable();

		modelo = new DefaultTableModel();

		btnCancelar = new JButton();
		btnGuardar = new JButton();
		btnEliminar = new JButton();

		setLayout(new BorderLayout());
		{
			add(panelTitulo, BorderLayout.NORTH);
			panelTitulo.setLayout(new FlowLayout(1, 0, 20));
			{
				panelTitulo.add(lblTitulo);
				lblTitulo.setText("REGISTRO INVENTARIO");
				lblTitulo.setFont(new Font("Decker", 1, 20));

				panelTitulo.add(lblLogo);
				lblLogo.setIcon(new ImageIcon(getClass().getResource("/recursos/img/logo.png")));
				lblLogo.addMouseListener(this);
			}
			add(panelCentro, BorderLayout.CENTER);
			panelCentro.setLayout(new BorderLayout());
			{
				panelCentro.add(panelCentroSuperior, BorderLayout.NORTH);
				panelCentroSuperior.setLayout(new BorderLayout(0, 5));
				{
					panelCentroSuperior.add(panelInventarioInicial, BorderLayout.NORTH);
					panelInventarioInicial.setLayout(new BorderLayout());
					{
						panelInventarioInicial.add(lblInventarioIncial, BorderLayout.NORTH);
						lblInventarioIncial.setText("INVENTARIO INCIAL");
						lblInventarioIncial.setHorizontalAlignment(SwingConstants.CENTER);

						panelInventarioInicial.add(panelCombo);
						panelCombo.setLayout(new FlowLayout(1));
						{
							panelCombo.add(cbxInventarioIncial, BorderLayout.CENTER);
							cbxInventarioIncial.setPreferredSize(new Dimension(500, 25));
						}

						panelInventarioInicial.add(panelNombre, BorderLayout.EAST);
						panelNombre.setLayout(new BorderLayout());
						{
							panelNombre.add(lblNombre, BorderLayout.NORTH);
							lblNombre.setText("NOMBRE DE INVENTARIO");

							panelNombre.add(txtNombre, BorderLayout.CENTER);
							txtNombre.setPreferredSize(new Dimension(200, 20));
							txtNombre.addKeyListener(this);

							panelNombre.add(lblMargen, BorderLayout.EAST);
							lblMargen.setPreferredSize(new Dimension(150, 0));
						}
					}

					panelCentroSuperior.add(panelTitulos, BorderLayout.CENTER);
					panelTitulos.setLayout(new FlowLayout(1, 25, 0));
					{
						panelTitulos.add(lblCodigo);
						lblCodigo.setPreferredSize(new Dimension(150, 20));
						lblCodigo.setText("Código");
						lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);

						panelTitulos.add(lblBarras);
						lblBarras.setPreferredSize(new Dimension(150, 20));
						lblBarras.setText("Código Barras");
						lblBarras.setHorizontalAlignment(SwingConstants.CENTER);

						panelTitulos.add(lblDescripcion);
						lblDescripcion.setPreferredSize(new Dimension(400, 20));
						lblDescripcion.setText("Descripción");
						lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);

						panelTitulos.add(lblCantidad);
						lblCantidad.setPreferredSize(new Dimension(150, 20));
						lblCantidad.setText("Cantidad");
						lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);

						panelTitulos.add(lblCosto);
						lblCosto.setPreferredSize(new Dimension(150, 20));
						lblCosto.setText("Costo");
						lblCosto.setHorizontalAlignment(SwingConstants.CENTER);
					}

					panelCentroSuperior.add(panelFunciones, BorderLayout.SOUTH);
					panelFunciones.setLayout(new FlowLayout(1, 25, 20));
					{
						panelFunciones.add(txtCodigo);
						txtCodigo.setPreferredSize(new Dimension(150, 20));
						txtCodigo.requestFocus();
						txtCodigo.addKeyListener(this);

						panelFunciones.add(txtBarras);
						txtBarras.setPreferredSize(new Dimension(150, 20));
						txtBarras.setEditable(false);

						panelFunciones.add(txtDescripcion);
						txtDescripcion.setPreferredSize(new Dimension(400, 20));
						txtDescripcion.setEditable(false);
						txtDescripcion.addKeyListener(this);

						panelFunciones.add(txtCantidad);
						txtCantidad.setPreferredSize(new Dimension(150, 20));
						txtCantidad.setEditable(false);
						txtCantidad.addKeyListener(this);

						panelFunciones.add(txtCosto);
						txtCosto.setPreferredSize(new Dimension(150, 20));
						txtCosto.setEditable(false);
					}

				}

				panelCentro.add(jsTabla, BorderLayout.CENTER);
				{

					jsTabla.setViewportView(tabla);
					jsTabla.setBorder(
							javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
					columnasEditables = new boolean[] { false, false, false, false, false, false, false,false,false,false,false };
					columnTypes = new Class[] { String.class, String.class, Double.class, Double.class, Double.class,
							Double.class, Double.class,Boolean.class,String.class,String.class,String.class };
					modelo = new modeloTablasEditable(columnasEditables, columnTypes);
					modelo.addColumn("CODIGO");
					modelo.addColumn("DESCRIPCION");
					modelo.addColumn("INV. INICIAL");
					modelo.addColumn("INV. NUEVO");
					modelo.addColumn("AJUSTE TOTAL");
					modelo.addColumn("COST. UNIDAD");
					modelo.addColumn("TOTAL");
					modelo.addColumn("Creado");
					modelo.addColumn("FAMILIA");
					modelo.addColumn("GRUPO");
					modelo.addColumn("SUBGRUPO");
					tabla.setModel(modelo);
					tabla.addMouseListener(this);
				}

			}

			add(panelInferior, BorderLayout.SOUTH);
			panelInferior.setLayout(new FlowLayout(1, 150, 5));
			{
				panelInferior.add(btnGuardar);
				btnGuardar.setText("GUARDAR");
				btnGuardar.addActionListener(this);
				;

				panelInferior.add(btnCancelar);
				btnCancelar.setText("CANCELAR");
				btnCancelar.addActionListener(this);

				panelInferior.add(btnEliminar);
				btnEliminar.setText("ELIMINAR");
				btnEliminar.addActionListener(this);

				panelInferior.add(lblTotal);
				lblTotal.setText("TOTAL");

				panelInferior.add(txtTotal);
				txtTotal.setPreferredSize(new Dimension(150, 20));
				txtTotal.setEditable(false);
			}

		}

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getSource() == txtCodigo){ 
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				miCoordinador.iniciarConexion();
				String[] id = cbxInventarioIncial.getSelectedItem().toString().split("-");
				try {
					miInventario = new InventarioInicialVo();
					miInventario = miCoordinador.consultar(txtCodigo.getText(), id[0]);
					if (!miInventario.getBarras().isEmpty()) {
						txtDescripcion.setEditable(false);
						txtCodigo.setText(miInventario.getCodigo());
						txtBarras.setText(miInventario.getBarras());
						txtDescripcion.setText(miInventario.getDescripcion());
						txtCantidad.setText("1");
						familia = miInventario.getFamilia();
						grupo = miInventario.getGrupo();
						subgrupo = miInventario.getSubgrupo();
						if (miInventario.getCosto().doubleValue() < 0.0D) {
							miInventario.setCosto(Double.valueOf(0.0D));
						}
						txtCosto.setText(miCoordinador.formatoNumero(miInventario.getCosto(), 2));
						txtCantidad.setEditable(true);
						txtCantidad.requestFocus();
						txtCantidad.selectAll();
						cbxInventarioIncial.setEnabled(false);
					} else {
						txtDescripcion.setEditable(true);
						txtDescripcion.requestFocus();
						miInventario.setCosto(Double.valueOf(0.0D));
						miInventario.setCantidad(Double.valueOf(0.0D));
					}
				} catch (NullPointerException e1) {
					miCoordinador.AlertaError("Artículo " + txtCodigo.getText(), "No Existe, Digite Una Descripción");
					txtDescripcion.setEditable(true);
					txtDescripcion.requestFocus();
					miInventario.setCosto(Double.valueOf(0.0D));
					miInventario.setCantidad(Double.valueOf(0.0D));
					familia = "SIN FAMILIA";
					grupo = "SIN GRUPO";
					subgrupo = "SIN SUBGRUPO";
				} catch (SQLException e2) {
					ConexionFireBird miConexionFireBird = new ConexionFireBird("clector", "1234");
					miCoordinador.setMiConexionFireBird(miConexionFireBird);
					miCoordinador.iniciarConexion();
				}
			}
		}
		if ((e.getSource() == txtCantidad) && (e.getKeyCode() == KeyEvent.VK_ENTER)) {
			int i = miCoordinador.validarCodigo(tabla, txtCodigo.getText());
			System.err.println(i);
			if (i >= 1) {
				sumarDato(i);
			} else {
				agregarTabla();
			}

			miCoordinador.totalTabla(tabla, txtTotal);
			crear = false;
		}

		if (e.getSource() == txtDescripcion) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				txtCosto.setText("0");
				txtCantidad.requestFocus();
				txtCantidad.setEditable(true);
				txtDescripcion.setEditable(false);
				crear = true;
				}
		}
		
		if (e.getSource() == txtNombre) { 
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.err.println("ingresa");
					if(!txtNombre.getText().isEmpty()) {
						txtCosto.requestFocus();
					}
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == txtCantidad) {
			if (txtCantidad.getText().length() > 0) {
				Double costo = Integer.parseInt(txtCantidad.getText()) * miInventario.getCosto();
				txtCosto.setText(miCoordinador.formatoNumero(costo, 0));
			} else {
				txtCosto.setText("0");
			}
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		if ((e.getSource() == txtNombre) || (e.getSource() == txtDescripcion)) {
			miCoordinador.mayusculas(e);
		}

		if (e.getSource() == txtCantidad) {
			miCoordinador.soloNumeros(txtCantidad);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGuardar) {
			if (!txtNombre.getText().isEmpty()) {
				try {
					miCoordinador.iniciarConexion();
					miCoordinador.guardarInventarioNuevo(tabla, cbxInventarioIncial.getSelectedItem().toString(),
							txtNombre.getText());
					miCoordinador.AlertaCorrecto("Inventario", "Guardado Correctamente");
					miCoordinador.limpiarTabla(tabla);
					cbxInventarioIncial.setEnabled(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
					miCoordinador.iniciarConexion();
				}
			} else {
				miCoordinador.AlertaError("El Inventario Debe Llevar", "Un Nombre Para Su Busqueda");
			}
		}

		if ((e.getSource() == btnCancelar)
				&& (miCoordinador.AlertaConfirmar("Se Borraran Todos Los Datos", "¿Estas Seguro?"))) {
			miCoordinador.cerrarPestaña();
		}

		if ((e.getSource() == btnEliminar)
				&& (miCoordinador.AlertaConfirmar("Eliminara codigo " + tabla.getValueAt(tabla.getSelectedRow(), 0),
						"Â¿Estas Seguro?"))) {
			miCoordinador.eliminarFila(tabla);
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
			      Double.valueOf(miInventario.getCosto().doubleValue() * ajuste.doubleValue()), 
			      Boolean.valueOf(crear), 
			      familia, 
			      grupo, 
			      subgrupo });
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
		Double cantidad = miCoordinador.StringDouble(tabla.getValueAt(i - 1, 3).toString());
		modelo = (DefaultTableModel) tabla.getModel();
		modelo.removeRow(i - 1);
		cantidad = cantidad + miCoordinador.StringDouble(txtCantidad.getText());
		txtCantidad.setText(miCoordinador.formatoNumero(cantidad, 2));
		agregarTabla();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if ((e.getSource() == tabla) && (e.getClickCount() > 1)) {
	      int row = tabla.getSelectedRow();
	      txtCodigo.setText(tabla.getValueAt(row, 0).toString());
	      txtDescripcion.setText(tabla.getValueAt(row, 1).toString());
	      txtCantidad.setText(tabla.getValueAt(row, 3).toString());
	      txtCosto.setText(tabla.getValueAt(row, 5).toString());
	      familia = tabla.getValueAt(row, 8).toString();
	      grupo = tabla.getValueAt(row, 9).toString();
	      subgrupo = tabla.getValueAt(row, 10).toString();
	      txtCodigo.setEditable(false);
	      txtCantidad.setEditable(true);
	      miCoordinador.eliminarFila(tabla);
		}
			    

	    if ((e.getSource() == lblLogo) && 
	      (e.getClickCount() > 1)) {
	      miCoordinador.habilitarOpciones();
	    }

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
