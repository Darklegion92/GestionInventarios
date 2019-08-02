package modelo;

import java.sql.SQLException;

import javax.swing.JTable;

import controladores.Coordinador;
import modelo.Vo.InventarioNuevoVo;

public class registrarInventario {
	
	Coordinador miCoordinador;
	
	
	
	public void guardar(JTable tabla, String inventarioInicial) throws SQLException {
		
		String[] idInventarioIncial = inventarioInicial.split("-");
		
		int idInventarioNuevo = miCoordinador.guardarInventarioNuevo(idInventarioIncial[0]);
		
		for(int i = 0; i<tabla.getRowCount();i++) {
			InventarioNuevoVo miInventario = new InventarioNuevoVo();
			miInventario.setCantidadAjustada(miCoordinador.StringDouble(tabla.getValueAt(i, 4).toString()));
			miInventario.setCantidadInicial(miCoordinador.StringDouble(tabla.getValueAt(i, 2).toString()));
			miInventario.setCantidadNueva(miCoordinador.StringDouble(tabla.getValueAt(i, 3).toString()));
			miInventario.setCodigo(tabla.getValueAt(i, 0).toString());
			miInventario.setCostoUnidad(miCoordinador.StringDouble(tabla.getValueAt(i, 5).toString()));
			miInventario.setDescripcion(tabla.getValueAt(i, 1).toString());
			miInventario.setIdInventarioNuevo(idInventarioNuevo);
			miCoordinador.guardarInventario(miInventario);
		}
		
		
	}
	

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	
		
}
