package modelo;

import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.EncryptedDocumentException;

import controladores.Coordinador;

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
	
	public void almacenarArchivo(JProgressBar pEstado, JLabel txt, String ruta, String nombre, JLabel lblRuta, Container container, JTextField txtNombre, JButton btnCargar, JButton btnArchivo) throws EncryptedDocumentException, FileNotFoundException, IOException, SQLException{
		    progressBar barra = new progressBar(pEstado, miCoordinador, ruta, nombre, txt, lblRuta, container, txtNombre, btnCargar, btnArchivo);
		    barra.execute();
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
