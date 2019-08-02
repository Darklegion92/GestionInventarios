package controladores;

import modelo.cargarInventario;
import modelo.consolidarExportar;
import modelo.exportarInventario;
import modelo.registrarInventario;
import modelo.Dao.ArticulosDao;
import modelo.Dao.InventariosInicialesDao;
import modelo.Dao.InventariosNuevosDao;
import modelo.funciones.Funciones;
import vistas.VentanaPrincipal;
import vistas.VentanaSplash;

public class Aplicacion {

	public void iniciarSistema() {
		
		Coordinador miCoordinador = new Coordinador();
		
		VentanaPrincipal miVentana= new VentanaPrincipal();
		VentanaSplash miVentanaSplash = new VentanaSplash(miVentana,true);
		
		cargarInventario miCargarInventario = new cargarInventario();
		registrarInventario miRegistrarInventario = new registrarInventario();
		exportarInventario miExportarInventario = new exportarInventario();
		consolidarExportar miConsolidarExportar = new consolidarExportar();
		Funciones misFunciones = new Funciones();
		InventariosInicialesDao miInventariosInicialesDao = new InventariosInicialesDao();
		InventariosNuevosDao miInventariosNuevosDao = new InventariosNuevosDao();
	    ArticulosDao miArticulosDao = new ArticulosDao();
	    
		
///////////////Se setea el estado al controlador//////////////////////////////////////////
		 	miCoordinador.setMiVentana(miVentana);
		    miCoordinador.setMiVentanaSplash(miVentanaSplash);
		    miCoordinador.setMiCargarInventario(miCargarInventario);
		    miCoordinador.setMisFunciones(misFunciones);
		    miCoordinador.setMiInventariosInicialesDao(miInventariosInicialesDao);
		    miCoordinador.setMiRegistrarInventario(miRegistrarInventario);
		    miCoordinador.setMiInventariosNuevosDao(miInventariosNuevosDao);
		    miCoordinador.setMiExportarInventario(miExportarInventario);
		    miCoordinador.setMiConsolidarExportar(miConsolidarExportar);
		    miCoordinador.setMiArticulosDao(miArticulosDao);
	
////////////Se setea la incializacion de las ventanas al controlador//////////////////////////////////
		miVentana.setMiCoordinador(miCoordinador);
		miVentanaSplash.setMiCoordinador(miCoordinador);
		
////////////Se setea la incializacion de las modelos al controlador//////////////////////////////////
		miCargarInventario.setMiCoordinador(miCoordinador);
		misFunciones.setMiCoordinador(miCoordinador);
		miInventariosInicialesDao.setMiCoordinador(miCoordinador);
		miRegistrarInventario.setMiCoordinador(miCoordinador);
		miInventariosNuevosDao.setMiCoordinador(miCoordinador);
		miExportarInventario.setMiCoordinador(miCoordinador);
		miConsolidarExportar.setMiCoordinador(miCoordinador);
	    miArticulosDao.setMiCoordinador(miCoordinador);
		
//////////////Inicia la Aplicacion////////////////////////////////////////
		
		miVentana.setVisible(true);
		//miVentanaSplash.setVisible(true);
		
	}
}