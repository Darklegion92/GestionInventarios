package modelo.conexiones;

import java.sql.*;

public class Conexion {

	private String nombreBd = "inventariossfc";
	private String usuario = "domicilios";
	private String password = "Domicilios83731";
	private String url = "jdbc:mysql://localhost:3306/" + nombreBd;
	
	 

	Connection conn = null; // creamos un objeto de tipo conexion

	public Conexion() { // generamos el constructor de la clase
			
		 usuario = "root";
		 password = "1234";
		 url = "jdbc:mysql://localhost:3306/" + nombreBd;
		try {
			//obtener el driver
			Class.forName("com.mysql.jdbc.Driver"); // java busca este conector
			
			//obtener la conexion
			conn = DriverManager.getConnection(url, usuario, password);// creamos esta conecion con los datos

			if (conn != null) {
				System.out.println("Conexion Exitosa a la BD: " + nombreBd);
			}

			} catch (ClassNotFoundException e) {
			System.out.println("ocurre una ClassNotFoundExeption : "+e.getMessage());

			} catch (SQLException e) {

			System.out.println("ocurre una SQLException : "+e.getMessage());
			}

	}

	public Connection getConnection() {
		return conn;

	}

	public void desconectar() throws SQLException {
		conn.close();
	}
}