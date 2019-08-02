package modelo.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controladores.Coordinador;
import modelo.Vo.InventarioConsolidadoVo;
import modelo.Vo.InventarioNuevoVo;
import modelo.conexiones.Conexion;

public class InventariosNuevosDao {

	private Coordinador miCoordinador;
	
	
	
	
	public Integer guardar(String idInventarioInicial) throws SQLException {
		
		Connection connection = null; 
		Conexion miconexion = miCoordinador.getMiConexion();
		PreparedStatement statement = null;
		ResultSet resultado = null;
		connection = miconexion.getConnection();
		String consulta = "CALL crearInventarioNuevo(null,"+idInventarioInicial+")";
		statement = connection.prepareStatement(consulta);
		resultado = statement.executeQuery();

		if (resultado.next()) {
			return resultado.getInt("id");
		}
		
		return 0;
	}
	
	public void guardar(InventarioNuevoVo invNuevo) throws SQLException {
		Connection connection = null; 
		Conexion miconexion = miCoordinador.getMiConexion();
		PreparedStatement statement = null;
		connection = miconexion.getConnection();
		String consulta = "INSERT INTO inventariosnuevosdetalle(idInventarioNuevo,codigo,descripcion,cantidadInicial,cantidadNueva,cantidadAjustada,costoUnidad)"
				+ "VALUES("+invNuevo.getIdInventarioNuevo()+",'"+invNuevo.getCodigo()+"','"+invNuevo.getDescripcion()+"',"+invNuevo.getCantidadInicial()+","+invNuevo.getCantidadNueva()+","+invNuevo.getCantidadAjustada()+","+invNuevo.getCostoUnidad()+")";
		statement = connection.prepareStatement(consulta);
		statement.execute();
	}
	
	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	public ArrayList<InventarioConsolidadoVo> consultar(String idInventarioInicial) throws SQLException {
		
		Connection connection = null; 
		Conexion miconexion = miCoordinador.getMiConexion();
		PreparedStatement statement = null;
		ResultSet resultado = null;
		connection = miconexion.getConnection();
		ArrayList<InventarioConsolidadoVo> lista = new ArrayList<InventarioConsolidadoVo>();
		InventarioConsolidadoVo miInventario;
		String consulta = "SELECT * FROM inventariosconsolidados WHERE idInventarioInicial = "+idInventarioInicial;
		statement = connection.prepareStatement(consulta);
		resultado = statement.executeQuery();

		while (resultado.next()) {
			miInventario = new InventarioConsolidadoVo();
			miInventario.setCantidadAjustada(resultado.getDouble("cantidadAjustada"));
			miInventario.setCantidadInicial(resultado.getDouble("cantidadInicial"));
			miInventario.setCantidadNueva(resultado.getDouble("cantidadNueva"));
			miInventario.setCodigo(resultado.getString("codigo"));
			miInventario.setCostoUnidad(resultado.getDouble("costoUnidad"));
			miInventario.setDescripcion(resultado.getString("descripcion"));
			miInventario.setId(resultado.getInt("id"));
			miInventario.setIdInventarioInicial(resultado.getInt("idInventarioInicial"));
			lista.add(miInventario);
		}
		
		return lista;
	}

	


}
