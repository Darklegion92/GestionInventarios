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

public class consolidarExportar {

	
	private Coordinador miCoordinador;
	
	public String exportar(String inventario1, String inventario2, int tipo) throws SQLException, IOException {
		
		JFileChooser ventanaArchivo = new JFileChooser();
		File archivo = null;
		if(!inventario1.equals(inventario2)) {
			if(ventanaArchivo.showDialog(miCoordinador.getMiVentana(), "Exportar")==JFileChooser.APPROVE_OPTION) {
				archivo=ventanaArchivo.getSelectedFile();
				archivo=(new File(archivo.getAbsolutePath()+".csv"));
				
				String[] idInventario1 = inventario1.split("-");
				String[] idInventario2 = inventario2.split("-");
				ArrayList<InventarioConsolidadoVo> lista = miCoordinador.consultarConsolidado(idInventario1[0],idInventario2[0]);		
				BufferedWriter bw;
				
				bw = new BufferedWriter(new FileWriter(archivo));
				if(tipo == 1) {
					for(InventarioConsolidadoVo item:lista) {
						bw.write("\""+item.getCodigo()+"\";"+item.getDescripcion()+";"+item.getCantidadInicial()+
								";"+item.getCantidadNueva()+";"+item.getCantidadAjustada()+";"+item.getCostoUnidad()+";"+item.getCostoUnidad()*item.getCantidadAjustada()+";"+
								item.getFamilia()+";"+item.getGrupo()+";"+item.getSubgrupo()+";"+item.isCreado()+"\n");
					}
				}else if(tipo == 2) {
					for(InventarioConsolidadoVo item:lista) {
						bw.write("\""+item.getCodigo()+"\";;;"+item.getCantidadNueva()+";"+item.isCreado()+"\n");
					}
				}
				bw.close();
			}
		}else {
			return "<html><font size=6><p align='center'>LOS INVENTARIOS <span></span></p><p align='center'>NO PUEDEN SER IGUALES</p></font></html>";
		}
		return "<html><font size=6><p align='center'>SE HAN EXPORTADO EL ARCHIVO <span>"+archivo.getName()+"</span></p><p align='center'>EXITOSAMENTE!!</p></font></html>";
	}

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}
	
}
