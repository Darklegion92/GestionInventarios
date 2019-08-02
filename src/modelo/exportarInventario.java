package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import controladores.Coordinador;
import modelo.Vo.InventarioConsolidadoVo;

public class exportarInventario {
	
	Coordinador miCoordinador;

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	public String exportar(String inventario, int tipo) throws SQLException, IOException {
		
		 JFileChooser ventanaArchivo = new JFileChooser();
		    File archivo = null;
		    
		    if (ventanaArchivo.showDialog(miCoordinador.getMiVentana(), "Exportar") == 0) {
		      archivo = ventanaArchivo.getSelectedFile();
		      archivo = new File(archivo.getAbsolutePath() + ".csv");
		      
		      String[] idInventarioIncial = inventario.split("-");
		      ArrayList<InventarioConsolidadoVo> lista = miCoordinador.consultar(idInventarioIncial[0]);
		      
		      BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
		      if (tipo == 1) {
		        for (InventarioConsolidadoVo item : lista) {
		          bw.write("\"" + item.getCodigo() + "\";" + item.getDescripcion() + ";" + item.getCantidadInicial() + 
		            ";" + item.getCantidadNueva() + ";" + item.getCantidadAjustada() + ";" + item.getCostoUnidad() + ";" + item.getCostoUnidad().doubleValue() * item.getCantidadAjustada().doubleValue() + ";" + 
		            item.getFamilia() + ";" + item.getGrupo() + ";" + item.getSubgrupo() + ";" + item.isCreado() + "\n");
		        }
		      } else if (tipo == 2) {
		        for (InventarioConsolidadoVo item : lista) {
		          bw.write("\"" + item.getCodigo() + "\";;;" + item.getCantidadNueva() + ";" + item.isCreado() + "\n");
		        }
		      }
		      bw.close();
		    }
		    return "<html><font size=6><p align='center'>SE HAN EXPORTADO EL ARCHIVO <span>" + archivo.getName() + "</span></p><p align='center'>EXITOSAMENTE!!</p></font></html>";
		  }
	
}
