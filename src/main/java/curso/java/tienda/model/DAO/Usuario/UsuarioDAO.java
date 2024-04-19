package curso.java.tienda.model.DAO.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import curso.java.tienda.config.Conexion;
import curso.java.tienda.model.VO.Usuario.UsuarioVO;

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
	
	
	public static UsuarioVO findByEmail(String email) {
		
		UsuarioVO usuario = null;
		
		try {
			
			Connection con = Conexion.getConexion();
			PreparedStatement st = con.prepareStatement("SELECT * FROM Usuarios WHERE email = ?");
			
			st.setString(1, email);
			
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
	
	public static boolean insertarUsuario(UsuarioVO usuario) {
		    
        try {
            
            Connection con = Conexion.getConexion();
            PreparedStatement st = con.prepareStatement("INSERT INTO Usuarios (id_rol, email, clave, nombre, apellido1, apellido2, direccion, provincia, localidad, telefono, dni) VALUES (3, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            st.setInt(1, usuario.getId_rol());
            st.setString(2, usuario.getEmail());
            st.setString(3, usuario.getClave());
            st.setString(4, usuario.getNombre());
            st.setString(5, usuario.getApellido1());
            st.setString(6, usuario.getApellido2());
            st.setString(7, usuario.getDireccion());
            st.setString(8, usuario.getProvincia());
            st.setString(9, usuario.getLocalidad());
            st.setString(10, usuario.getTelefono());
            st.setString(11, usuario.getDni());
            
            st.executeUpdate();
            
        } catch (SQLException e) {
            return false;
        }
        
        return true;
        
	}

}
