package curso.java.tienda.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import curso.java.tienda.config.Conexion;
import curso.java.tienda.model.VO.UsuarioVO;

public class UsuarioDAO {
	
	public static UsuarioVO findById(int id) {
		
		UsuarioVO usuario = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM Usuarios WHERE id = ?");
			
			st.setInt(1, id);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				usuario = new UsuarioVO();
				
				usuario.setId(rs.getInt("id"));
				usuario.setId_rol(rs.getInt("id_rol"));
				usuario.setEmail(rs.getString("email"));
				usuario.setClave(rs.getString("clave"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellido1(rs.getString("apellido1"));
				usuario.setApellido2(rs.getString("apellido2"));
				usuario.setDireccion(rs.getString("direccion"));
				usuario.setProvincia(rs.getString("provincia"));
				usuario.setLocalidad(rs.getString("localidad"));
				usuario.setTelefono(rs.getString("telefono"));
				usuario.setDni(rs.getString("dni"));

			}
			
		} catch (SQLException e) {
			return null;
		}
		
		return usuario;
	}

}
