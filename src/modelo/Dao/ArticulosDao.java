package modelo.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import controladores.Coordinador;
import modelo.conexiones.ConexionFireBird;


public class ArticulosDao {
	
	private Coordinador miCoordinador;
	
	public String consultarArticulo(String codigo) throws SQLException {
		String res = "";
			Connection connection = null;
			PreparedStatement statement = null;
			ResultSet resultado = null;
			ConexionFireBird miConexionFirebird= miCoordinador.getMiConexionFireBird();
			connection = miConexionFirebird.getConnection();
			
			String consulta = "SELECT a.arti_cod AS codigo FROM articulo a LEFT JOIN barras_articulo b on b.arti_cod = a.arti_cod " + 
					"WHERE a.arti_cod = '"+codigo+"' OR b.coba_cod = '"+codigo+"'";
			statement = connection.prepareStatement(consulta);
			resultado = statement.executeQuery();
			if (resultado.next()) {
				res = resultado.getString("codigo");
			}
		return res;	
	}

	public Coordinador getMiCoordinador() {
		return miCoordinador;
	}

	public void setMiCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

}
