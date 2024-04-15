package curso.java.tienda.config;

import java.sql.Connection;
import java.sql.SQLException;

public class Conexion {

	static String bd = "tienda_manuel_chillon_prieto";
	static String login = "root";
	static String password = "";
	static String host = "127.0.0.1"; // Localhost
	static String port = "3307";
	
	static String url = "jdbc:mysql://";
	static Connection conexion;
	
	public static Connection getConexion() {
		
		if (conexion == null ) {
			crearConexion();
		}
		
		return conexion;
	}
	
	public static boolean crearConexion() {
		
		try {
			
			// Cargamos el driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Obtenemos la conexion
			conexion = java.sql.DriverManager.getConnection(url + host + "/" + bd, login, password);
//			conexion = java.sql.DriverManager.getConnection(url + host + ":" + port + "/" + bd, login, password);
			
			conexion.setAutoCommit(false);
			
		} catch (SQLException e) {
			return false;
			
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
}
