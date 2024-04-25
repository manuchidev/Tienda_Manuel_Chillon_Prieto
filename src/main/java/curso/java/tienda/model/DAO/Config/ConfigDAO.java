package curso.java.tienda.model.DAO.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import curso.java.tienda.config.Conexion;

public class ConfigDAO {
	
	public static void updateFactura() {
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement stSelect = con.prepareStatement("SELECT valor FROM config WHERE id = 1");
			ResultSet rs = stSelect.executeQuery();
	
			String valorActualStr = null;
			int valorActual = 0;
			
			if (rs.next()) {
				valorActualStr = rs.getString("valor");
				valorActual = Integer.parseInt(valorActualStr);
			}
			
			int nuevoValor = valorActual + 1;
			
			PreparedStatement stUpdate = con.prepareStatement("UPDATE config SET valor = ? WHERE id = 1");
			stUpdate.setString(1, String.valueOf(nuevoValor));
			stUpdate.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
