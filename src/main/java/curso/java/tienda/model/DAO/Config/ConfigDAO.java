package curso.java.tienda.model.DAO.Config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import curso.java.tienda.config.Conexion;
import curso.java.tienda.model.VO.Config.ConfigVO;

public class ConfigDAO {
	
	public static List<ConfigVO> getDatosEmpresa() {

		List<ConfigVO> datos = new ArrayList<>();

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM configuracion WHERE id != 1");
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				ConfigVO dato = new ConfigVO();
				
				dato.setClave(rs.getString("clave"));
				dato.setValor(rs.getString("valor"));
				
				datos.add(dato);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return datos;
	}
	
	public static int getValor() {

		int valor = 0;

		try {

			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT valor FROM configuracion WHERE id = 1");
			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				valor = rs.getInt("valor");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return valor;
	}
	
	
	public static void updateValor() {
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement stSelect = con.prepareStatement("SELECT valor FROM configuracion WHERE id = 1");
			ResultSet rs = stSelect.executeQuery();
	
			String valorActualStr = null;
			int valorActual = 0;
			
			if (rs.next()) {
				valorActualStr = rs.getString("valor");
				valorActual = Integer.parseInt(valorActualStr);
			}
			
			int nuevoValor = valorActual + 1;
			
			PreparedStatement stUpdate = con.prepareStatement("UPDATE configuracion SET valor = ? WHERE id = 1");
			stUpdate.setString(1, String.valueOf(nuevoValor));
			stUpdate.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
