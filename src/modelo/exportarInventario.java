package modelo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
		
		if(ventanaArchivo.showDialog(miCoordinador.getMiVentana(), "Exportar")==JFileChooser.APPROVE_OPTION) {
			archivo=ventanaArchivo.getSelectedFile();
			archivo=(new File(archivo.getAbsolutePath()+".xlsx"));
			
			String[] idInventarioIncial = inventario.split("-");
			ArrayList<InventarioConsolidadoVo> lista = miCoordinador.consultar(idInventarioIncial[0]);		
			
			@SuppressWarnings("resource")
			Workbook wb = new XSSFWorkbook();
			
			Sheet hoja = wb.createSheet(idInventarioIncial[1]);
			try {
				for(int i = -1;i<lista.size();i++) {
					Row fila = hoja.createRow(i+1);
					if(tipo==1) {
						for(int j = 0 ; j<7;j++) {
							Cell celda = fila.createCell(j);
							if(i==-1) {
								if(j==0) {
									celda.setCellValue("CODIGO");
								}else if(j==1) {
									celda.setCellValue("DESCRIPCION");
								}else if(j==2) {
									celda.setCellValue("INVENTARIO INCIAL");
								}else if(j==3) {
									celda.setCellValue("INVENTARIO NUEVO");
								}else if(j==4) {
									celda.setCellValue("AJUSTE INVENTARIO");
								}else if(j==5) {
									celda.setCellValue("COSTO UNIDAD");
								}else if(j==6) {
									celda.setCellValue("COSTO AJUSTE");
								}
							}else {
								if(j==0) {
									celda.setCellValue(lista.get(i).getCodigo());
								}else if(j==1) {
									celda.setCellValue(lista.get(i).getDescripcion());
								}else if(j==2) {
									celda.setCellValue(lista.get(i).getCantidadInicial());
								}else if(j==3) {
									celda.setCellValue(lista.get(i).getCantidadNueva());
								}else if(j==4) {
									celda.setCellValue(lista.get(i).getCantidadAjustada());
								}else if(j==5) {
									celda.setCellValue(lista.get(i).getCostoUnidad());
								}else if(j==6) {
									Double dato = lista.get(i).getCantidadAjustada()* lista.get(i).getCostoUnidad();
									celda.setCellValue(dato);
								}
							}
								wb.write(new FileOutputStream(archivo));
						}
					}else if(tipo==2) {
						for(int j = 0 ; j<2;j++) {
							Cell celda = fila.createCell(j);
							if(i==-1) {
								if(j==0) {
									celda.setCellValue("CODIGO");
								}else if(j==1) {
									celda.setCellValue("INVENTARIO NUEVO");
								}
							}else {
								if(j==0) {
									celda.setCellValue(lista.get(i).getCodigo());
								}else if(j==1) {
									celda.setCellValue(lista.get(i).getCantidadNueva());
								}
								
							}
							wb.write(new FileOutputStream(archivo));	
						}	
					}
				}
				wb.close();
			}catch (Exception e) {
				e.printStackTrace();
				wb.close();
				return "<html><font size=10><p align='center'>ERROR AL INTENTAR GUARDAR EL ARCHIVO</p><p align='center'> <span>"+archivo.getName()+"</span></p></font></html>";
			}
		}
		return "<html><font size=10><p align='center'>SE HAN EXPORTADO EL ARCHIVO <span>"+archivo.getName()+"</span></p><p align='center'>EXITOSAMENTE!!</p></font></html>";
	}
	
}
