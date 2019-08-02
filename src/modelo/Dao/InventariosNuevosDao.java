package modelo.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controladores.Coordinador;
import modelo.Vo.InventarioConsolidadoVo;
import modelo.Vo.InventarioInicialVo;
import modelo.Vo.InventarioNuevoVo;
import modelo.conexiones.Conexion;

public class InventariosNuevosDao {

	private Coordinador miCoordinador;
	
	 public Integer guardar(String idInventarioInicial, String nombre) throws SQLException {
		    Connection connection = null;
		    Conexion miconexion = miCoordinador.getMiConexion();
		    PreparedStatement statement = null;
		    ResultSet resultado = null;
		    connection = miconexion.getConnection();
		    String consulta = "CALL crearInventarioNuevo(null," + idInventarioInicial + ",'" + nombre + "')";
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
		String consulta = "INSERT INTO inventariosnuevosdetalle(idInventarioNuevo,codigo,descripcion,cantidadInicial,cantidadNueva,cantidadAjustada,costoUnidad) "
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

	public ArrayList<InventarioConsolidadoVo> consultar(String idInventarioInicial) throws SQLException
	  {
	    Connection connection = null;
	    Conexion miconexion = miCoordinador.getMiConexion();
	    PreparedStatement statement = null;
	    ResultSet resultado = null;
	    connection = miconexion.getConnection();
	    ArrayList<InventarioConsolidadoVo> lista = new ArrayList<InventarioConsolidadoVo>();
	    
	    String consulta = "SELECT * FROM inventariosconsolidados WHERE idInventarioInicial = " + idInventarioInicial + " ORDER BY creado";
	    statement = connection.prepareStatement(consulta);
	    resultado = statement.executeQuery();
	    
	    while (resultado.next()) {
	      InventarioConsolidadoVo miInventario = new InventarioConsolidadoVo();
	      miInventario.setCantidadAjustada(Double.valueOf(resultado.getDouble("cantidadAjustada")));
	      miInventario.setCantidadInicial(Double.valueOf(resultado.getDouble("cantidadInicial")));
	      miInventario.setCantidadNueva(Double.valueOf(resultado.getDouble("cantidadNueva")));
	      miInventario.setCodigo(resultado.getString("codigo"));
	      miInventario.setCostoUnidad(Double.valueOf(resultado.getDouble("costoUnidad")));
	      miInventario.setDescripcion(resultado.getString("descripcion"));
	      miInventario.setId(Integer.valueOf(resultado.getInt("id")));
	      miInventario.setIdInventarioInicial(Integer.valueOf(resultado.getInt("idInventarioInicial")));
	      miInventario.setCreado(resultado.getBoolean("creado"));
	      miInventario.setFamilia(resultado.getString("familia"));
	      miInventario.setGrupo(resultado.getString("grupo"));
	      miInventario.setSubgrupo(resultado.getString("subgrupo"));
	      lista.add(miInventario);
	    }
	    
	    return lista;
	  }
	  
	  public String[] consultar() throws SQLException {
	    Connection connection = null;
	    Conexion miconexion = miCoordinador.getMiConexion();
	    PreparedStatement statement = null;
	    ResultSet resultado = null;
	    connection = miconexion.getConnection();
	    String consulta = "SELECT * FROM inventariosnuevos ORDER BY id DESC";
	    statement = connection.prepareStatement(consulta);
	    resultado = statement.executeQuery();
	    ArrayList<InventarioInicialVo> array = new ArrayList<InventarioInicialVo>();
	    
	    while (resultado.next()) {
	      InventarioInicialVo h = new InventarioInicialVo();
	      h.setId(Integer.valueOf(resultado.getInt("id")));
	      h.setCodigo(resultado.getString("nombre"));
	      array.add(h);
	    }
	    String[] lista = new String[array.size()];
	    
	    for (int i = 0; i < array.size(); i++)
	    {
	      lista[i] = ((array.get(i)).getId() + "-" + (array.get(i)).getCodigo());
	    }
	    return lista;
	  }
	  
	  public ArrayList<InventarioNuevoVo> consultar(Integer idInventarioNuevo) throws SQLException
	  {
	    Connection connection = null;
	    Conexion miconexion = miCoordinador.getMiConexion();
	    PreparedStatement statement = null;
	    ResultSet resultado = null;
	    connection = miconexion.getConnection();
	    ArrayList<InventarioNuevoVo> lista = new ArrayList<InventarioNuevoVo>();
	    
	    String consulta = "SELECT * FROM inventariosnuevosdetalle WHERE idInventarioNuevo = " + idInventarioNuevo;
	    statement = connection.prepareStatement(consulta);
	    resultado = statement.executeQuery();
	    
	    while (resultado.next()) {
	      InventarioNuevoVo miInventario = new InventarioNuevoVo();
	      miInventario.setCantidadAjustada(Double.valueOf(resultado.getDouble("cantidadAjustada")));
	      miInventario.setCantidadInicial(Double.valueOf(resultado.getDouble("cantidadInicial")));
	      miInventario.setCantidadNueva(Double.valueOf(resultado.getDouble("cantidadNueva")));
	      miInventario.setCodigo(resultado.getString("codigo"));
	      miInventario.setCostoUnidad(Double.valueOf(resultado.getDouble("costoUnidad")));
	      miInventario.setDescripcion(resultado.getString("descripcion"));
	      miInventario.setId(Integer.valueOf(resultado.getInt("id")));
	      miInventario.setIdInventarioNuevo(Integer.valueOf(resultado.getInt("idInventarioNuevo")));
	      miInventario.setCreado(resultado.getBoolean("creado"));
	      miInventario.setFamilia(resultado.getString("familia"));
	      miInventario.setGrupo(resultado.getString("grupo"));
	      miInventario.setSubgrupo(resultado.getString("subgrupo"));
	      lista.add(miInventario);
	    }
	    
	    return lista;
	  }
	  
	  public ArrayList<InventarioConsolidadoVo> consultar(String idInventarioInicial1, String idInventarioInicial2)
	    throws SQLException
	  {
	    Connection connection = null;
	    Conexion miconexion = miCoordinador.getMiConexion();
	    PreparedStatement statement = null;
	    ResultSet resultado = null;
	    connection = miconexion.getConnection();
	    ArrayList<InventarioConsolidadoVo> lista = new ArrayList<InventarioConsolidadoVo>();
	    
	    String consulta = "SELECT codigo, descripcion,familia,grupo,subgrupo,sum(cantidadNueva) as cantidadNueva, MIN(cantidadInicial) AS cantidadInicial, sum(cantidadNueva) - MIN(cantidadInicial) as cantidadAjustada, costoUnidad, creado FROM inventariosconsolidados i WHERE  i.idInventarioInicial = " + 
	      idInventarioInicial1 + "  OR i.idInventarioInicial = " + idInventarioInicial2 + " GROUP BY i.codigo ORDER BY i.creado";
	    statement = connection.prepareStatement(consulta);
	    resultado = statement.executeQuery();
	    
	    while (resultado.next()) {
	      InventarioConsolidadoVo miInventario = new InventarioConsolidadoVo();
	      miInventario.setCantidadAjustada(Double.valueOf(resultado.getDouble("cantidadAjustada")));
	      miInventario.setCantidadInicial(Double.valueOf(resultado.getDouble("cantidadInicial")));
	      miInventario.setCantidadNueva(Double.valueOf(resultado.getDouble("cantidadNueva")));
	      miInventario.setCodigo(resultado.getString("codigo"));
	      miInventario.setCostoUnidad(Double.valueOf(resultado.getDouble("costoUnidad")));
	      miInventario.setDescripcion(resultado.getString("descripcion"));
	      miInventario.setFamilia(resultado.getString("familia"));
	      miInventario.setGrupo(resultado.getString("grupo"));
	      miInventario.setSubgrupo(resultado.getString("subgrupo"));
	      miInventario.setCreado(resultado.getBoolean("creado"));
	      lista.add(miInventario);
	    }
	    
	    return lista;
	  }

	


}
