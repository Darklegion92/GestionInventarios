package modelo.funciones;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controladores.Coordinador;
import modelo.Vo.Datos;

public class Funciones {

	private Coordinador miCoordinador;

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	/**
	 * 
	 * @param panel
	 * @return
	 */
	public Object DiseñoComponentes(JPanel panel) {
		for (int i = 0; panel.getComponents().length > i; i++) {

			if (panel.getComponents()[i] instanceof JLabel) {

				((JLabel) panel.getComponents()[i]).setBorder(javax.swing.BorderFactory.createCompoundBorder(null,
						javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
				((JLabel) panel.getComponents()[i]).setFont(new java.awt.Font("Decker", 0, 14));
				((JLabel) panel.getComponents()[i]).setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

			}
			if (panel.getComponents()[i] instanceof JTextField) {

				((JTextField) panel.getComponents()[i]).setBorder(javax.swing.BorderFactory.createCompoundBorder(null,
						javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
				((JTextField) panel.getComponents()[i]).setFont(new java.awt.Font("Decker", 0, 14));
				((JTextField) panel.getComponents()[i]).setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

			}

			if (panel.getComponents()[i] instanceof JButton) {

				((JButton) panel.getComponents()[i]).setFont(new java.awt.Font("Decker", 1, 15));
				((JButton) panel.getComponents()[i])
						.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
				((JButton) panel.getComponents()[i]).setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			}
			if (panel.getComponents()[i] instanceof JComboBox<?>) {

				((JComboBox<?>) panel.getComponents()[i]).setFont(new java.awt.Font("Decker", 0, 14));
				((JComboBox<?>) panel.getComponents()[i]).setBorder(javax.swing.BorderFactory.createCompoundBorder(null,
						javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
			}

		}

		return panel;
	}

	/**
	 * 
	 * @param panel
	 * @return
	 */
	public Object DiseñoComponentes2(JPanel panel) {
		for (int i = 0; panel.getComponents().length > i; i++) {

			if (panel.getComponents()[i] instanceof JLabel) {

				((JLabel) panel.getComponents()[i]).setBorder(javax.swing.BorderFactory.createCompoundBorder(null,
						javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)));
				((JLabel) panel.getComponents()[i]).setFont(new java.awt.Font("Decker", 0, 14));
				((JLabel) panel.getComponents()[i]).setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

			}
			if (panel.getComponents()[i] instanceof JTextField) {

				((JTextField) panel.getComponents()[i]).setBorder(javax.swing.BorderFactory.createCompoundBorder(null,
						javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
				((JTextField) panel.getComponents()[i]).setFont(new java.awt.Font("Decker", 0, 14));
				((JTextField) panel.getComponents()[i]).setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

			}

			if (panel.getComponents()[i] instanceof JButton) {

				((JButton) panel.getComponents()[i]).setFont(new java.awt.Font("Decker", 1, 15));
				((JButton) panel.getComponents()[i])
						.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
				((JButton) panel.getComponents()[i]).setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			}
			if (panel.getComponents()[i] instanceof JComboBox<?>) {

				((JComboBox<?>) panel.getComponents()[i]).setFont(new java.awt.Font("Decker", 0, 14));
				((JComboBox<?>) panel.getComponents()[i]).setBorder(javax.swing.BorderFactory.createCompoundBorder(null,
						javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
			}

		}

		return panel;
	}

	/**
	 * 
	 * @param campo
	 */
	public void validarSoloLetras(JTextField campo) { // solo letras
		campo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c)) {
					e.consume();
				}
			}
		});
	}

	/**
	 * 
	 * @param campo
	 */
	public void validarSoloLetrasCbx(JComboBox<String> campo) {
		campo.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (Character.isDigit(c)) {
					evt.consume();
				}
			}
		});

	}

	/**
	 * 
	 * @param campo
	 * @param min
	 * @return
	 */
	public String validarMinimoLetraS(String campo, int min) {
		int tam = ((CharSequence) campo).length();
		if (tam < min) {

			return "ok";
		}
		return null;
	}

	/**
	 * 
	 * @param campo
	 */
	public void validarSoloNumeros(JTextField campo) {
		campo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				String com = Character.toString(e.getKeyChar());
				if (!Character.isDigit(c) && !com.equals(".")) {
					e.consume();
				}
			}
		});
	}

	/**
	 * 
	 * @param campo
	 * @param max
	 */
	public void limitarMaximoCaracteres(JTextField campo, int max) {
		campo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int tam = campo.getText().length();
				if (tam >= max) {
					e.consume();
				}
			}
		});
	}

	/**
	 * 
	 * @param campo
	 * @param min
	 * @return
	 */
	public boolean limitarMinimoCaracteres(String campo, int min) {
		int tam = campo.length();
		if (tam < min) {

			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param email
	 * @return
	 */
	public boolean validarEmail(String email) {

		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		if (matcher.find()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 
	 * @param campo
	 */
	public void efectoFoco(JTextField campo) {

		campo.addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusGained(java.awt.event.FocusEvent evt) {
				focusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt) {
				focusLost(evt);
			}
		});

	}

	/**
	 * 
	 * @param caja
	 * @return
	 */
	public boolean validarCajaPass(String caja) {
		boolean respuesta = false;
		if (caja != null && caja != "") {
			String c = (String) caja;
			int letras = 0;
			for (int i = 0; i < c.length(); i++) {
				if (c.charAt(i) == ' ') {
					letras++;
				}

				if (letras == 0) {
					respuesta = true;
				}
			}

		}
		return respuesta;

	}

	/**
	 * 
	 * @param caja
	 * @return
	 */
	public String validarCajaComboBusqueda(String caja) {
		String respuesta = null;
		if (caja != null && caja != "") {
			int valor = Integer.parseInt(caja);
			if (valor > 0) {
				String c = (String) caja;
				for (int i = 0; i < c.length(); i++) {
					if (c.charAt(i) == '-') {
						return "ok";
					}

				}
			}

		}
		return respuesta;

	}

	/**
	 * 
	 * @param numero
	 * @param d
	 * @return
	 */
	public String formatearNumero(Double numero, int d) {
		NumberFormat FM = NumberFormat.getNumberInstance(new Locale("en"));
		FM.setMaximumFractionDigits(d);
		return FM.format(numero);
	}

	/**
	 * 
	 * @param valor
	 * @return
	 */
	public Double StringADouble(String valor) {

		if (valor == "") {
			valor = "0";
		}

		valor = valor.replace("$", "");
		// valor = valor.replace(",", "");
		Double dato;
		// valor = valor.replace(".0", "");
		try {
			dato = Double.valueOf(valor);
		} catch (NumberFormatException e) {
			valor = valor.replace(",", "");
			dato = Double.valueOf(valor);
		}
		return dato;
	}

	/**
	 * 
	 * @param combo
	 * @param lista
	 */
	public void llenarCombo(JComboBox<String> combo, String[] lista) {
		combo.removeAllItems();
		for (int i = 0; i < lista.length; i++) {
			combo.addItem(lista[i]);
		}

	}

	/**
	 * 
	 * @param evt
	 */
	public void mayusculas(KeyEvent evt) {
		char c = evt.getKeyChar();
		if (Character.isLowerCase(c)) {
			String cad = ("" + c).toUpperCase();
			c = cad.charAt(0);
			evt.setKeyChar(c);
		}
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public Date DateADate(java.util.Date date) {
		Date fecha = null;
		java.util.Date fechaActual = new java.util.Date();
		try {
			fecha = new Date(date.getTime());
		} catch (NullPointerException e) {
			fecha = new Date(fechaActual.getTime());
		}
		return fecha;

	}

	public void selectAll(JComboBox<String> combo) {
		combo.getEditor().selectAll();
	}

	public void formatoNumero(JTextField campo, KeyEvent e) {
		Double numero = StringADouble(campo.getText());
		campo.setText(formatearNumero(numero, 0));

	}

	public boolean validarFechas(java.util.Date fechaInicial, java.util.Date fechaFinal) {

		if (fechaFinal.after(fechaInicial))
			return true;

		return false;
	}

	public boolean validarMes(java.util.Date fechaInicial, java.util.Date fechaFinal) {
		Calendar calendarioIncial = Calendar.getInstance();
		Calendar calendarioFinal = Calendar.getInstance();
		calendarioIncial.setTime(fechaInicial);
		calendarioIncial.setTime(fechaFinal);
		if (calendarioIncial.get(Calendar.MONTH) == calendarioFinal.get(Calendar.MONTH)
				&& calendarioIncial.get(Calendar.YEAR) == calendarioFinal.get(Calendar.YEAR)) {
			return true;
		}
		return false;
	}

	public Integer obtenerDiasMes(java.util.Date fecha) {
		Calendar calendario = Calendar.getInstance();
		calendario.setTime(fecha);
		return calendario.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public int obtenerDiasCurso(java.util.Date fechaFinal) {
		Calendar calendarioFinal = Calendar.getInstance();
		calendarioFinal.setTime(fechaFinal);
		return calendarioFinal.get(Calendar.DAY_OF_MONTH);
	}

	public void limpiarTabla(JTable tabla) {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		modelo.setRowCount(0);

	}

	public void eliminarFila(JTable tabla) {
		DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
		modelo.removeRow(tabla.getSelectedRow());
	}

	/**
	 * 
	 * @param datos mensaje de error en el log
	 */
	public void grabarDatos(Datos datos, String nombre) {
		FileWriter writer = null;
		try {
			File archivo = new File(System.getProperty("user.dir")+"/"+nombre);
			
			if (!archivo.exists()) {
				writer = new FileWriter(System.getProperty("user.dir")+"/"+nombre);
				BufferedWriter bfwriter = new BufferedWriter(writer);
				bfwriter = new BufferedWriter(new FileWriter(archivo));
			}
			writer = new FileWriter(System.getProperty("user.dir")+"/"+nombre, true);
			BufferedWriter bfwriter = new BufferedWriter(writer);
			bfwriter.newLine();
			bfwriter.write(datos.getCodigo() + "-" + datos.getDescripcion() + "-" + datos.getInvInicial()
					+ "-" + datos.getInvNuevo() + "-" + datos.getAjusteTotal() + "-" + datos.getCostoUnidad()
					+ "-" + datos.getTotal() + "-" + datos.getCreado() + "-" + datos.getFamilia() + "-"
					+ datos.getGrupo() + "-" + datos.getSubgrupo());
			bfwriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		

	}

	/**
	 * 
	 * @param ruta ruta del archivo a leer
	 * @return datos del archivo
	 */
	public ArrayList<Datos> leerDatos(String nombre) {
		String ruta = System.getProperty("user.dir")+"/"+nombre;
		ArrayList<Datos> cadena = new ArrayList<Datos>();
		String linea = null;
		FileReader archivo;
		try {
			archivo = new FileReader(ruta);
			BufferedReader b = new BufferedReader(archivo);
			try {

				while ((linea = b.readLine()) != null) {
					Datos miDato = new Datos();
					String[] data = linea.split("-");
					if(data.length==11) {
						miDato.setCodigo(data[0]);
						miDato.setDescripcion(data[1]);
						miDato.setInvInicial(miCoordinador.StringDouble(data[2]));
						miDato.setInvNuevo(miCoordinador.StringDouble(data[3]));
						miDato.setAjusteTotal(miCoordinador.StringDouble(data[4]));
						miDato.setCostoUnidad(miCoordinador.StringDouble(data[5]));
						miDato.setTotal(miCoordinador.StringDouble(data[6]));
						miDato.setCreado(Boolean.parseBoolean(data[7]));
						miDato.setFamilia(data[8]);
						miDato.setGrupo(data[9]);
						miDato.setSubgrupo(data[10]);
	
						cadena.add(miDato);
					}
					
				}
				b.close();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return cadena;
	}
	
	public void eliminarContenido(String nombre) throws IOException {
		String ruta = System.getProperty("user.dir")+"/"+nombre;
		FileWriter writer = null;
		writer = new FileWriter(ruta);
		BufferedWriter bfwriter = new BufferedWriter(writer);
		bfwriter.write("");
		bfwriter.close();
			
	}

}