package modelo.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controladores.Coordinador;
import modelo.Vo.InventarioInicialVo;
import modelo.conexiones.Conexion;

public class InventariosInicialesDao {

	private Coordinador miCoordinador;
	
	
	
	
	public Integer guardar(String nombre) throws SQLException {
		
		Connection connection = null; 
		Conexion miconexion = miCoordinador.getMiConexion();
		PreparedStatement statement = null;
		ResultSet resultado = null;
		connection = miconexion.getConnection();
		String consulta = "CALL crearInventarioInicial(null,'"+nombre+"')";
		statement = connection.prepareStatement(consulta);
		resultado = statement.executeQuery();

		if (resultado.next()) {
			return resultado.getInt("id");
		}
		
		return 0;
	}
	
	public void guardar(InventarioInicialVo invInicial) throws SQLException {
		
		Connection connection = null; 
		Conexion miconexion = miCoordinador.getMiConexion();
		PreparedStatement statement = null;
		connection = miconexion.getConnection();
		String consulta = "INSERT INTO inventariosinicialesdetalle(idInventarioInicial,codigo,barras,familia,grupo,subgrupo,descripcion,cantidad,costo)"
				+ "VALUES("+invInicial.getIdInventarioInicial()+",'"+invInicial.getCodigo()+"','"+invInicial.getBarras()+"','"+invInicial.getFamilia()+"','"+invInicial.getGrupo()+"','"+invInicial.getSubgrupo()+"','"+invInicial.getDescripcion()+"',"+invInicial.getCantidad()+","+invInicial.getCosto()+")";
		statement = connection.prepareStatement(consulta);
		statement.execute();
	}
	

	public String[] consultar() throws SQLException {
		Connection connection = null; 
		Conexion miconexion = miCoordinador.getMiConexion();
		PreparedStatement statement = null;
		ResultSet resultado = null;
		connection = miconexion.getConnection();
		String consulta = "SELECT * FROM inventariosiniciales ORDER BY id DESC";
		statement = connection.prepareStatement(consulta);
		resultado = statement.executeQuery();
		ArrayList<InventarioInicialVo> array = new ArrayList<InventarioInicialVo>(); 
	
		while (resultado.next()) {
			InventarioInicialVo h = new InventarioInicialVo();
			h.setId(resultado.getInt("id"));
			h.setCodigo(resultado.getString("nombre"));
			array.add(h);			
		}
		String[] lista =new String[array.size()];
		
		for(int i =0; i<array.size();i++) {
			
			lista[i] = array.get(i).getId()+"-"+array.get(i).getCodigo();
		}
		return lista;
	}
	
	public InventarioInicialVo consultar(String codigo, String id) throws SQLException {
		Connection connection = null; 
		Conexion miconexion = miCoordinador.getMiConexion();
		PreparedStatement statement = null;
		ResultSet resultado = null;
		connection = miconexion.getConnection();
		InventarioInicialVo inventario = new InventarioInicialVo();
		String consulta = "SELECT * FROM inventariosinicialesdetalle id, inventariosiniciales i " + 
				"WHERE id.idInventarioInicial = i.id AND (id.barras = '"+codigo+"' OR id.codigo = '"+codigo+"') AND i.id = "+id;
		statement = connection.prepareStatement(consulta);
		resultado = statement.executeQuery();
	
		if (resultado.next()) {
			inventario.setBarras(resultado.getString("barras"));
			inventario.setCantidad(resultado.getDouble("cantidad"));	
			inventario.setCodigo(resultado.getString("codigo"));
			inventario.setCosto(resultado.getDouble("costo"));
			inventario.setDescripcion(resultado.getString("descripcion"));
			inventario.setFamilia(resultado.getString("familia"));
			inventario.setGrupo(resultado.getString("grupo"));
			inventario.setSubgrupo(resultado.getString("subgrupo"));
			inventario.setId(resultado.getInt("id"));
			inventario.setIdInventarioInicial(resultado.getInt("idInventarioInicial"));
			System.out.println("Articulo Encontrado localmente");
		}
		return inventario;
	}
	
	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	


}
