package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controladores.Coordinador;
import modelo.Vo.InventarioConsolidadoVo;
import modelo.Vo.InventarioNuevoVo;

public class registrarInventario {
	
	Coordinador miCoordinador;
	
	
	
	public void guardar(JTable tabla, String inventarioInicial, String nombre) throws SQLException{
	    String[] idInventarioIncial = inventarioInicial.split("-");
	    
	    int idInventarioNuevo = miCoordinador.guardarInventarioNuevo(idInventarioIncial[0], nombre);
	    
	    for (int i = 0; i < tabla.getRowCount(); i++) {
	      InventarioNuevoVo miInventario = new InventarioNuevoVo();
	      miInventario.setCantidadAjustada(miCoordinador.StringDouble(tabla.getValueAt(i, 4).toString()));
	      miInventario.setCantidadInicial(miCoordinador.StringDouble(tabla.getValueAt(i, 2).toString()));
	      miInventario.setCantidadNueva(miCoordinador.StringDouble(tabla.getValueAt(i, 3).toString()));
	      miInventario.setCodigo(tabla.getValueAt(i, 0).toString());
	      miInventario.setCostoUnidad(miCoordinador.StringDouble(tabla.getValueAt(i, 5).toString()));
	      miInventario.setDescripcion(tabla.getValueAt(i, 1).toString());
	      miInventario.setIdInventarioNuevo(Integer.valueOf(idInventarioNuevo));
	      miInventario.setCreado(Boolean.parseBoolean(tabla.getValueAt(i, 7).toString()));
	      miInventario.setFamilia(tabla.getValueAt(i, 8).toString());
	      miInventario.setGrupo(tabla.getValueAt(i, 9).toString());
	      miInventario.setSubgrupo(tabla.getValueAt(i, 10).toString());
	      miCoordinador.guardarInventario(miInventario);
	    }
	  }
	
	public void llenarConsolidado(JTable tabla, String idInventarioIncial)
	  {
	    DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
	    try {
	      ArrayList<InventarioConsolidadoVo> lista = miCoordinador.consultarConsolidado(idInventarioIncial);
	      for (InventarioConsolidadoVo item : lista) {
	        modelo.addRow(new Object[] {
	          item.getCodigo(), 
	          item.getDescripcion(), 
	          item.getCantidadInicial(), 
	          item.getCantidadNueva(), 
	          item.getCantidadAjustada(), 
	          item.getCostoUnidad(), 
	          Double.valueOf(item.getCantidadAjustada().doubleValue() * item.getCostoUnidad().doubleValue()), 
	          Boolean.valueOf(item.isCreado()), 
	          item.getFamilia(), 
	          item.getGrupo(), 
	          item.getSubgrupo() });
	      }
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	    }
	  }
	  
	  public void llenarIndividual(JTable tabla, String idInventarioNuevo){
	    DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
	    try {
	      ArrayList<InventarioNuevoVo> lista = miCoordinador.consultarIndividual(idInventarioNuevo);
	      for (InventarioNuevoVo item : lista) {
	        modelo.addRow(new Object[] {
	          item.getCodigo(), 
	          item.getDescripcion(), 
	          item.getCantidadInicial(), 
	          item.getCantidadNueva(), 
	          item.getCantidadAjustada(), 
	          item.getCostoUnidad(), 
	          Double.valueOf(item.getCantidadAjustada().doubleValue() * item.getCostoUnidad().doubleValue()), 
	          Boolean.valueOf(item.isCreado()), 
	          item.getFamilia(), 
	          item.getGrupo(), 
	          item.getSubgrupo() });
	      }
	    }
	    catch (SQLException e)
	    {
	      e.printStackTrace();
	    }
	  }
	
	
	
	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	
		
}
