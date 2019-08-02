package modelo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import controladores.Coordinador;
import modelo.Vo.InventarioInicialVo;

public class cargarInventario {

	private Coordinador miCoordinador;

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	public String cargarArchivo() {
		FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.xlsx", "xlsx");
		JFileChooser fc = new JFileChooser();

		fc.setFileFilter(filtro);

		int seleccion = fc.showOpenDialog(miCoordinador.getMiVentana());

		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File fichero = fc.getSelectedFile();
			return fichero.getAbsolutePath();
		} else {
			return null;
		}

	}

	@SuppressWarnings("resource")
	public void almacenarArchivo(JLabel txt, String ruta,String nombre)
			throws EncryptedDocumentException, FileNotFoundException, IOException, SQLException {

		InputStream excelStream = null;

		excelStream = new FileInputStream(new File(ruta));
		XSSFWorkbook hssfWorkbook = new XSSFWorkbook(excelStream);
		XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		XSSFRow fila;
		
		int rows = hssfSheet.getLastRowNum();

		int contador = 0;

		DataFormatter df = new DataFormatter();
		miCoordinador.iniciarConexion();
		Integer id = miCoordinador.guardarInventario(nombre);
		for (int f = 1; f < rows; f++) {
			fila = hssfSheet.getRow(f);
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
				miCoordinador.guardarInventario(inventario);
				contador ++;
			}
		}
		hssfWorkbook.close();
		txt.setText("<html><font size=10><p align='center'>SE HAN REGISTRADO <span>"+contador+"</span></p><p align='center'>ARTÍCULOS EN ESTE INVENTARIO</p></font></html>"); 	
	}

	public void totalTabla(JTable tabla, JTextField txt) {
		Double total = 0.0;
		if(tabla.getRowCount() >0) {
			for(int i=0;i<tabla.getRowCount();i++) {
				total = total+miCoordinador.StringDouble(tabla.getValueAt(i, 6).toString());
			}
			txt.setText(miCoordinador.formatoNumero(total, 2));
		}
		
	}
	
	public Integer validarCodigo(JTable tabla, String codigo) {
		
		if(tabla.getRowCount() >0) {
			for(int i=0;i<tabla.getRowCount();i++) {
				if(tabla.getValueAt(i, 0).toString().equals(codigo)){
					return i+1;
				}
			}
		}
		return 0;
	}

}
