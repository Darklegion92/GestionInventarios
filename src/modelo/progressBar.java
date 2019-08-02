package modelo;

import java.awt.Container;
import java.awt.Cursor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import controladores.Coordinador;
import modelo.Vo.InventarioInicialVo;

public class progressBar extends SwingWorker<Integer, String>{
	
	
	private JProgressBar jprogress;
	private Coordinador miCoordinador;
	private String ruta;
	private String nombre;
	private JLabel txt, lblRuta;
	private Container contenedor;
	private JTextField txtNombre;
	private JButton btnCargar;
	private JButton btnArchivo;

	public progressBar(JProgressBar jprogress, Coordinador miCoordinador, String ruta, String nombre, JLabel txt,
			JLabel lblRuta, Container contenedor, JTextField txtNombre, JButton btnCargar, JButton btnArchivo) {
		super();
		this.jprogress = jprogress;
		this.miCoordinador = miCoordinador;
		this.ruta = ruta;
		this.nombre = nombre;
		this.txt = txt;
		this.lblRuta = lblRuta;
		this.contenedor = contenedor;
		this.txtNombre = txtNombre;
		this.btnCargar = btnCargar;
		this.btnArchivo = btnArchivo;
	}

	public JProgressBar getJprogress() {
		return jprogress;
	}

	public void setJprogress(JProgressBar jprogress) {
		this.jprogress = jprogress;
	}

	@Override
	protected Integer doInBackground() {
		contenedor.setCursor(new Cursor(Cursor.WAIT_CURSOR));
		btnCargar.setEnabled(false);
		txtNombre.setEnabled(false);
		btnArchivo.setEnabled(false);
		try {
			//Proceso
			InputStream excelStream = null;
	
			excelStream = new FileInputStream(new File(ruta));
			XSSFWorkbook hssfWorkbook = new XSSFWorkbook(excelStream);
			XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			XSSFRow fila;
			
			int rows = hssfSheet.getLastRowNum();
	
			int  contador = 0;
			getJprogress().setMaximum(rows);
			DataFormatter df = new DataFormatter();
			miCoordinador.iniciarConexion();
			miCoordinador.iniciarTransaccion();
			Integer id = miCoordinador.guardarInventario(nombre);
			for (int f = 1; f < rows; f++) {
				fila = hssfSheet.getRow(f);
				final int c = f+1;
				getJprogress().setValue(c);
				InventarioInicialVo inventario = new InventarioInicialVo();
				if (fila == null) {
					break;
				} else {
					inventario.setBarras(df.formatCellValue(fila.getCell(24)).replaceAll("'", ""));
					inventario.setCantidad(miCoordinador.StringDouble(df.formatCellValue(fila.getCell(3))));
					inventario.setCodigo(df.formatCellValue(fila.getCell(0)).replaceAll("'", ""));
					inventario.setCosto(miCoordinador.StringDouble(df.formatCellValue(fila.getCell(5))));
					inventario.setDescripcion(df.formatCellValue(fila.getCell(1)).replaceAll("'", ""));
					inventario.setIdInventarioInicial(id);
					inventario.setFamilia(df.formatCellValue(fila.getCell(27)).replaceAll("'", ""));
					inventario.setGrupo(df.formatCellValue(fila.getCell(28)).replaceAll("'", ""));
					inventario.setSubgrupo(df.formatCellValue(fila.getCell(29)).replaceAll("'", ""));
					miCoordinador.guardarInventario(inventario);
					contador ++;
				}
				
			}
			miCoordinador.committ();
			hssfWorkbook.close();
			txt.setText("<html><font size=4><p align='center'>SE HAN REGISTRADO <span>"+contador+"</span></p><p align='center'>ARTÍCULOS EN ESTE INVENTARIO</p></font></html>");
			contenedor.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			miCoordinador.AlertaCorrecto("Inventario Inicial", "Subido Correctamente");
			txtNombre.setText("");
			lblRuta.setText("");
			btnCargar.setEnabled(true);
			txtNombre.setEnabled(true);
			btnArchivo.setEnabled(true);
		}catch(IOException | SQLException e) {
			JOptionPane.showMessageDialog(null, "error "+e.getMessage());
			miCoordinador.rollback();
			e.printStackTrace();
		}
		return 0;
	}

}
