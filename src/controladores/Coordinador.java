package controladores;

import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.poi.EncryptedDocumentException;

import modelo.cargarInventario;
import modelo.consolidarExportar;
import modelo.exportarInventario;
import modelo.registrarInventario;
import modelo.Dao.ArticulosDao;
import modelo.Dao.InventariosInicialesDao;
import modelo.Dao.InventariosNuevosDao;
import modelo.Vo.InventarioConsolidadoVo;
import modelo.Vo.InventarioInicialVo;
import modelo.Vo.InventarioNuevoVo;
import modelo.conexiones.Conexion;
import modelo.conexiones.ConexionFireBird;
import modelo.funciones.Funciones;
import vistas.VentanaPrincipal;
import vistas.VentanaSplash;
import vistas.alertas.VentanaAlerta;
import vistas.paneles.panelCargarInventario;
import vistas.paneles.panelConsolidarExportar;
import vistas.paneles.panelExportarInventario;
import vistas.paneles.panelRegistrarInventario;;

public class Coordinador {

	private VentanaSplash miVentanaSplash;
	private VentanaPrincipal miVentana;
	private VentanaAlerta miVentanaAlerta;

	private panelCargarInventario miPanelCargarInventario;
	private panelRegistrarInventario miPanelRegistrarInventario;
	private panelExportarInventario miPanelExportarInventario;
	private panelConsolidarExportar miPanelConsolidarExportar;

	private cargarInventario miCargarInventario;
	private registrarInventario miRegistrarInventario;
	private exportarInventario miExportarInventario;
	private consolidarExportar miConsolidarExportar;

	private Funciones misFunciones;
	private Conexion miConexion;
	private ConexionFireBird miConexionFireBird;
	private InventariosInicialesDao miInventariosInicialesDao;
	private InventariosNuevosDao miInventariosNuevosDao;
	private ArticulosDao miArticulosDao;

///////////////////Getters And Setters De Ventanas////////////////////

	public VentanaAlerta getMiVentanaAlerta() {
		return miVentanaAlerta;
	}

	public panelConsolidarExportar getMiPanelConsolidarExportar() {
		return miPanelConsolidarExportar;
	}

	public void setMiPanelConsolidarExportar(panelConsolidarExportar miPanelConsolidarExportar) {
		this.miPanelConsolidarExportar = miPanelConsolidarExportar;
	}

	public consolidarExportar getMiConsolidarExportar() {
		return miConsolidarExportar;
	}

	public void setMiConsolidarExportar(consolidarExportar miConsolidarExportar) {
		this.miConsolidarExportar = miConsolidarExportar;
	}

	public ConexionFireBird getMiConexionFireBird() {
		return miConexionFireBird;
	}

	public void setMiConexionFireBird(ConexionFireBird miConexionFireBird) {
		this.miConexionFireBird = miConexionFireBird;
	}

	public ArticulosDao getMiArticulosDao() {
		return miArticulosDao;
	}

	public void setMiArticulosDao(ArticulosDao miArticulosDao) {
		this.miArticulosDao = miArticulosDao;
	}

	public exportarInventario getMiExportarInventario() {
		return miExportarInventario;
	}

	public void setMiExportarInventario(exportarInventario miExportarInventario) {
		this.miExportarInventario = miExportarInventario;
	}

	public panelExportarInventario getMiPanelExportarInventario() {
		return miPanelExportarInventario;
	}

	public void setMiPanelExportarInventario(panelExportarInventario miPanelExportarInventario) {
		this.miPanelExportarInventario = miPanelExportarInventario;
	}

	public InventariosNuevosDao getMiInventariosNuevosDao() {
		return miInventariosNuevosDao;
	}

	public void setMiInventariosNuevosDao(InventariosNuevosDao miInventariosNuevosDao) {
		this.miInventariosNuevosDao = miInventariosNuevosDao;
	}

	public registrarInventario getMiRegistrarInventario() {
		return miRegistrarInventario;
	}

	public void setMiRegistrarInventario(registrarInventario miRegistrarInventario) {
		this.miRegistrarInventario = miRegistrarInventario;
	}

	public panelCargarInventario getMiPanelCargarInventario() {
		return miPanelCargarInventario;
	}

	public void setMiPanelCargarInventario(panelCargarInventario miPanelCargarInventario) {
		this.miPanelCargarInventario = miPanelCargarInventario;
	}

	public panelRegistrarInventario getMiPanelRegistrarInventario() {
		return miPanelRegistrarInventario;
	}

	public void setMiPanelRegistrarInventario(panelRegistrarInventario miPanelRegistrarInventario) {
		this.miPanelRegistrarInventario = miPanelRegistrarInventario;
	}

	public InventariosInicialesDao getMiInventariosInicialesDao() {
		return miInventariosInicialesDao;
	}

	public void setMiInventariosInicialesDao(InventariosInicialesDao miInventariosInicialesDao) {
		this.miInventariosInicialesDao = miInventariosInicialesDao;
	}

	public Conexion getMiConexion() {
		return miConexion;
	}

	public void setMiConexion(Conexion miConexion) {
		this.miConexion = miConexion;
	}

	public Funciones getMisFunciones() {
		return misFunciones;
	}

	public void setMisFunciones(Funciones misFunciones) {
		this.misFunciones = misFunciones;
	}

	public cargarInventario getMiCargarInventario() {
		return miCargarInventario;
	}

	public void setMiCargarInventario(cargarInventario miCargarInventario) {
		this.miCargarInventario = miCargarInventario;
	}

	public void setMiVentanaAlerta(VentanaAlerta miVentanaAlerta) {
		this.miVentanaAlerta = miVentanaAlerta;
	}

	public VentanaPrincipal getMiVentana() {
		return miVentana;
	}

	public void setMiVentana(VentanaPrincipal miVentana) {
		this.miVentana = miVentana;
	}

	public VentanaSplash getMiVentanaSplash() {
		return miVentanaSplash;
	}

	public void setMiVentanaSplash(VentanaSplash miVentanaSplash) {
		this.miVentanaSplash = miVentanaSplash;
	}

//////////////Metodos///////////////////////////

	public boolean AlertaSalir(String mensaje1, String mensaje2) {
		miVentanaAlerta = new VentanaAlerta(miVentana, true);
		miVentanaAlerta.crearMensaje("ADVERTENCIA",
				"<html><p align='center'>" + mensaje1 + "</p><p align='center'>" + mensaje2 + "</p></html>", 2);
		miVentanaAlerta.setVisible(true);
		while (miVentanaAlerta.isVisible()) {
			/////// Mientras se espera respuesta
		}
		return miVentanaAlerta.isRespuesta();
	}

	public boolean AlertaConfirmar(String mensaje1, String mensaje2) {
		miVentanaAlerta = new VentanaAlerta(miVentana, true);
		miVentanaAlerta.crearMensaje("CORRECTO",
				"<html><p align='center'>" + mensaje1 + "</p><p align='center'>" + mensaje2 + "</p></html>", 2);
		miVentanaAlerta.setVisible(true);
		while (miVentanaAlerta.isVisible()) {
			/////// Mientras se espera respuesta
		}
		return miVentanaAlerta.isRespuesta();
	}

	public void AlertaError(String mensaje1, String mensaje2) {
		miVentanaAlerta = new VentanaAlerta(miVentana, true);
		miVentanaAlerta.crearMensaje("ERROR",
				"<html><p align='center'>" + mensaje1 + "</p><p align='center'>" + mensaje2 + "</p></html>", 1);
		miVentanaAlerta.setVisible(true);
	}

	public void AlertaCorrecto(String mensaje1, String mensaje2) {
		miVentanaAlerta = new VentanaAlerta(miVentana, true);
		miVentanaAlerta.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		miVentanaAlerta.crearMensaje("CORRECTO",
				"<html><p align='center'>" + mensaje1 + "</p><p align='center'>" + mensaje2 + "</p></html>", 1);
		miVentanaAlerta.setVisible(true);
		miVentanaAlerta.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}

	/**
	 * metodo para abrir nueva pestaña del tipo Agrupaciones
	 */
	public void crearPestanaCargarInventario(JTabbedPane gestorVentanas) {
		gestorVentanas.setVisible(true);
		miPanelCargarInventario = new panelCargarInventario(this);
		gestorVentanas.addTab("Nuevo Inventario",
				new ImageIcon(getClass().getResource("/recursos/img/iconoInventarios.png")), miPanelCargarInventario,
				"Inventarios");
		gestorVentanas.setSelectedComponent(miPanelCargarInventario);
	}

	public void crearPestanaExportarInventario(JTabbedPane gestorVentanas) {
		iniciarConexion();
		gestorVentanas.setVisible(true);
		miPanelExportarInventario = new panelExportarInventario(this);
		gestorVentanas.addTab("Exportar Inventario",
				new ImageIcon(getClass().getResource("/recursos/img/iconoInventarios.png")), miPanelExportarInventario,
				"Inventarios");
		gestorVentanas.setSelectedComponent(miPanelExportarInventario);

	}

	public void crearPestanaExportarInventarioInd(JTabbedPane gestorVentanas) {
		iniciarConexion();
		gestorVentanas.setVisible(true);
		miPanelExportarInventario = new panelExportarInventario(true, this);
		gestorVentanas.addTab("Exportar Inventario Individual",
				new ImageIcon(getClass().getResource("/recursos/img/iconoInventarios.png")), miPanelExportarInventario,
				"Inventarios");
		gestorVentanas.setSelectedComponent(miPanelExportarInventario);
	}

	public void crearPestanaNuevoInventario(JTabbedPane gestorVentanas) {
		iniciarConexion();
		gestorVentanas.setVisible(true);
		miPanelRegistrarInventario = new panelRegistrarInventario(this);
		gestorVentanas.addTab("Importar Inventario",
				new ImageIcon(getClass().getResource("/recursos/img/iconoInventarios.png")), miPanelRegistrarInventario,
				"Inventarios");
		gestorVentanas.setSelectedComponent(miPanelRegistrarInventario);

	}

	public void crearPrestañaConsultarConsolidado(String[] inventarioIncial) {
		iniciarConexion();
		miVentana.getGestorVentanas().setVisible(true);
		miPanelRegistrarInventario = new panelRegistrarInventario(this, inventarioIncial, 1);
		miVentana.getGestorVentanas().addTab("Consultar Consolidado",
				new ImageIcon(getClass().getResource("/recursos/img/iconoInventarios.png")), miPanelRegistrarInventario,
				"Inventarios");
		miVentana.getGestorVentanas().setSelectedComponent(miPanelRegistrarInventario);
	}

	public void crearPrestañaConsultarIndividual(String[] inventarioIncial) {
		iniciarConexion();
		miVentana.getGestorVentanas().setVisible(true);
		miPanelRegistrarInventario = new panelRegistrarInventario(this, inventarioIncial, 2);
		miVentana.getGestorVentanas().addTab("Consultar Individual",
				new ImageIcon(getClass().getResource("/recursos/img/iconoInventarios.png")), miPanelRegistrarInventario,
				"Inventarios");
		miVentana.getGestorVentanas().setSelectedComponent(miPanelRegistrarInventario);
	}

	public void crearPestanaConsolidarExportar(JTabbedPane gestorVentanas) {
		iniciarConexion();
		gestorVentanas.setVisible(true);
		miPanelConsolidarExportar = new panelConsolidarExportar(this);
		gestorVentanas.addTab("Consolidar y Exportar",
				new ImageIcon(getClass().getResource("/recursos/img/iconoInventarios.png")), miPanelConsolidarExportar,
				"Inventarios");
		gestorVentanas.setSelectedComponent(miPanelConsolidarExportar);
	}

	public void llenarCombo(JComboBox<String> combo) throws SQLException {
		misFunciones.llenarCombo(combo, miInventariosInicialesDao.consultar());

	}

	public void llenarComboIndividual(JComboBox<String> combo) throws SQLException {
		misFunciones.llenarCombo(combo, miInventariosNuevosDao.consultar());
	}

	public void soloNumeros(JTextField campo) {
		misFunciones.validarSoloNumeros(campo);
	}

	public void almacenarArchivo(JProgressBar pEstado, JLabel progreso, String ruta, String nombre, JLabel lblRuta,
			Container container, JTextField txtNombre, JButton btnCargar, JButton btnArchivo) throws EncryptedDocumentException, FileNotFoundException, IOException, SQLException {
		miCargarInventario.almacenarArchivo(pEstado, progreso, ruta, nombre, lblRuta, container, txtNombre, btnCargar,
				btnArchivo);
	}

	public void cerrarVentanaSplash() {
		miVentanaSplash.dispose();
	}

	public String cargarArchivo() {
		return miCargarInventario.cargarArchivo();
	}

	public void iniciarConexion() {
		try {
			if (miConexion.getConnection().isClosed()) {
				System.err.println("conexion caida");
				miConexion = new Conexion();
			}
		} catch (NullPointerException | SQLException e) {
			System.err.println("conexion caida");
			miConexion = new Conexion();
		}

	}

	public Double StringDouble(String valor) {
		return misFunciones.StringADouble(valor);
	}

	public void mayusculas(KeyEvent e) {
		misFunciones.mayusculas(e);
	}

	public Integer guardarInventario(String nombre) throws SQLException {
		return miInventariosInicialesDao.guardar(nombre);
	}

	public void terminarConexion() throws SQLException {
		miConexion.desconectar();
	}

	public void guardarInventario(InventarioInicialVo inventario) throws SQLException {
		miInventariosInicialesDao.guardar(inventario);
	}

	public InventarioInicialVo consultar(String codigo, String id) throws SQLException {
		iniciarConexion();
		iniciarConexionFirebird();
		codigo = miArticulosDao.consultarArticulo(codigo);
		try {
			return miInventariosInicialesDao.consultar(codigo, id);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String formatoNumero(Double numero, int d) {
		return misFunciones.formatearNumero(numero, d);
	}

	public void totalTabla(JTable tabla, JTextField txt) {
		miCargarInventario.totalTabla(tabla, txt);

	}

	public int validarCodigo(JTable tabla, String codigo) {

		return miCargarInventario.validarCodigo(tabla, codigo);
	}

	public void guardarInventarioNuevo(JTable tabla, String inventarioInicial, String nombre) throws SQLException {
		miRegistrarInventario.guardar(tabla, inventarioInicial, nombre);
	}

	public void cerrarPestaña() {
		Component pestaña = miVentana.getGestorVentanas().getSelectedComponent();
		if (miVentana.getGestorVentanas() != null) {
			miVentana.getGestorVentanas().remove(pestaña);
		}

	}

	public int guardarInventarioNuevo(String idInventarioInicial, String nombre) throws SQLException {
		return miInventariosNuevosDao.guardar(idInventarioInicial, nombre);
	}

	public void guardarInventario(InventarioNuevoVo miInventario) throws SQLException {
		miInventariosNuevosDao.guardar(miInventario);

	}

	public void limpiarTabla(JTable tabla) {
		misFunciones.limpiarTabla(tabla);

	}

	public String exportar(String inventario, int tipo) throws SQLException, IOException {
		return miExportarInventario.exportar(inventario, tipo);
	}

	public ArrayList<InventarioConsolidadoVo> consultar(String idInventarioInicial) throws SQLException {
		return miInventariosNuevosDao.consultar(idInventarioInicial);
	}

	public ArrayList<InventarioConsolidadoVo> consultarConsolidar(String idInventarioInicial1,
			String idInventarioInicial2) throws SQLException {
		return miInventariosNuevosDao.consultar(idInventarioInicial1, idInventarioInicial2);
	}

	public void eliminarFila(JTable tabla) {
		misFunciones.eliminarFila(tabla);
	}

	public void llenarConsolidado(JTable tabla, String idInventarioIncial) {
		miRegistrarInventario.llenarConsolidado(tabla, idInventarioIncial);
	}

	public void llenarIndividual(JTable tabla, String idInventarioNuevo) {
		miRegistrarInventario.llenarIndividual(tabla, idInventarioNuevo);
	}

	public ArrayList<InventarioConsolidadoVo> consultarConsolidado(String idInventarioIncial) throws SQLException {
		return miInventariosNuevosDao.consultar(idInventarioIncial);
	}

	public String exportar(String inventario1, String inventario2, int tipo) throws SQLException, IOException {
		return miConsolidarExportar.exportar(inventario1, inventario2, tipo);
	}

	public ArrayList<InventarioNuevoVo> consultarIndividual(String idInventarioNuevo)
			throws NumberFormatException, SQLException {
		return miInventariosNuevosDao.consultar(Integer.valueOf(Integer.parseInt(idInventarioNuevo)));
	}

	public void iniciarConexionFirebird() {
		try {
			if ((miConexionFireBird.getConnection().isClosed()) || (miConexionFireBird.getConnection() == null)) {
				miConexionFireBird = new ConexionFireBird("sysdba", "masterkey");
			}
		} catch (Exception e) {
			miConexionFireBird = new ConexionFireBird("sysdba", "masterkey");
		}

	}

	public ArrayList<InventarioConsolidadoVo> consultarConsolidado(String idInventarioInicial1,
			String idInventarioInicial2) throws SQLException {
		return miInventariosNuevosDao.consultar(idInventarioInicial1, idInventarioInicial2);
	}

	public void habilitarOpciones() {
		miVentana.habilitarOpciones();
	}

	public void iniciarTransaccion() {
		try {
			miConexion.getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error "+e);
			e.printStackTrace();
		}
		
	}

	public void committ() {
		try {
			miConexion.getConnection().commit();
			miConexion.getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error "+e);
			e.printStackTrace();
		}		
	}
	
	public void rollback() {
		try {
			miConexion.getConnection().rollback();
			miConexion.getConnection().setAutoCommit(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error "+e);
			e.printStackTrace();
		}		
	}
}